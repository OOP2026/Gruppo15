package implementazionedao;

import java.sql.*;

import password.PasswordUtils;
import dao.UtenteDAO;
import model.Amministratore;
import model.Medico;
import model.Utente;
import database_connection.ConnessioneDatabase;

public class UtentePostgresDao implements UtenteDAO {
    @Override
    public Utente login(String email, String password) throws SQLException {

        // 1. Definiamo la stringa SQL. Selezioniamo sia i campi comuni che quelli specifici
        //aggiunta la tabella attivo, se un utente non è attivo non può effettuare la login
        String sql = "SELECT id, nome, cognome,password_hash, email, ruolo, attivo " +
                "FROM utenti_sistema " +
                "WHERE email = ?";
        //creiamo l'istanza con il database per ottenere e verificare i suoi dati
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2. Apriamo la connessione e prepariamo il PreparedStatement
        // Il try-with-resources chiuderà automaticamente tutto alla fine (anche in caso di errore)
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 3. Associano i parametri posizionali ai punti interrogativi (?)
            // 1 corrisponde al primo '?', 2 al secondo '?'
            pstmt.setString(1, email);

            // 4. Eseguiamo la query sul database Postgres
            try (ResultSet rs = pstmt.executeQuery()) {

                // 5. Muoviamo il cursore sulla prima riga di risultati con rs.next()
                if (rs.next()) {
                    System.out.println("Utente loggato: " + email);
                    String hashDalDB = rs.getString("password_hash");

                    if (PasswordUtils.verificaPassword(password, hashDalDB)) {


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
                            medico.setAttivo(rs.getBoolean("attivo"));

                            //aggiungo il set del ruolo altrimenti non salviamo questo dato
                            medico.setRuolo(ruoloDalDB);
                            // Riempiamo il dato specifico della classe figlia Medico
                            return medico; // Restituiamo il medico pronto

                        } else if ("AMMINISTRATORE".equalsIgnoreCase(ruoloDalDB)) {

                            Amministratore admin = new Amministratore();
                            // Riempiamo i dati ereditati dalla classe madre Utente
                            admin.setId(rs.getInt("id"));
                            admin.setNome(rs.getString("nome"));
                            admin.setCognome(rs.getString("cognome"));
                            admin.setEmail(rs.getString("email"));
                            admin.setAttivo(rs.getBoolean("attivo"));

                            //aggiungo il set del ruolo altrimenti non salviamo questo dato
                            admin.setRuolo(ruoloDalDB);

                            return admin; // Restituiamo l'amministratore pronto
                        }
                    }
                }

            } catch (SQLException e) {
                // Gestione dell'errore in caso di problemi con Postgres (es. Server spento, tabella inesistente)
                System.err.println("❌ Errore critico durante l'esecuzione del Login nel database:");
                e.printStackTrace();
            }

            // 7. Se il database non trova nessuna riga (credenziali errate) o se c'è un errore, restituisce null
            return null;
        }
    }
}

