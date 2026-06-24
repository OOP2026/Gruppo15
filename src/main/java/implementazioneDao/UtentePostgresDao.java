package implementazioneDao;

import java.sql.*;

import dao.UtenteDAO;
import model.Amministratore;
import model.Medico;
import model.Utente;
import database_connection.ConnessioneDatabase;

public class UtentePostgresDao implements UtenteDAO {
    @Override
    public Utente login(String email, String password) {
        // 1. Definiamo la stringa SQL. Selezioniamo sia i campi comuni che quelli specifici
        String sql = "SELECT id, nome, cognome, email, ruolo, specializzazione, ufficio " +
                "FROM utenti_sistema " +
                "WHERE email = ? AND password_hash = ?";

        // 2. Apriamo la connessione e prepariamo il PreparedStatement
        // Il try-with-resources chiuderà automaticamente tutto alla fine (anche in caso di errore)
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 3. Associano i parametri posizionali ai punti interrogativi (?)
            // 1 corrisponde al primo '?', 2 al secondo '?'
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            // 4. Eseguiamo la query sul database Postgres
            try (ResultSet rs = pstmt.executeQuery()) {

                // 5. Muoviamo il cursore sulla prima riga di risultati con rs.next()
                if (rs.next()) {
                    // Se siamo qui, rs.next() è true: l'utente esiste!
                    // Leggiamo la colonna 'ruolo' per capire chi è
                    String ruoloDalDB = rs.getString("ruolo");

                    // 6. POLIMORFISMO: Creiamo l'oggetto specifico in base al ruolo trovato
                    if ("MEDICO".equalsIgnoreCase(ruoloDalDB)) {

                        Medico medico = new Medico();
                        // Riempiamo i dati ereditati dalla classe madre Utente
                        medico.setId(rs.getInt("id"));
                        medico.setNome(rs.getString("nome"));
                        medico.setCognome(rs.getString("cognome"));
                        medico.setEmail(rs.getString("email"));

                        // Riempiamo il dato specifico della classe figlia Medico


                        return medico; // Restituiamo il medico pronto

                    } else if ("AMMINISTRATORE".equalsIgnoreCase(ruoloDalDB)) {

                        Amministratore admin = new Amministratore();
                        // Riempiamo i dati ereditati dalla classe madre Utente
                        admin.setId(rs.getInt("id"));
                        admin.setNome(rs.getString("nome"));
                        admin.setCognome(rs.getString("cognome"));
                        admin.setEmail(rs.getString("email"));




                        return admin; // Restituiamo l'amministratore pronto
                    }
                }
            }

        } catch (SQLException e) {
            // Gestione dell'errore in caso di problemi con Postgres (es. server spento, tabella inesistente)
            System.err.println("❌ Errore critico durante l'esecuzione del Login nel database:");
            e.printStackTrace();
        }

        // 7. Se il database non trova nessuna riga (credenziali errate) o se c'è un errore, restituisce null
        return null;
    }
}

