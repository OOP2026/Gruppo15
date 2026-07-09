package implementazionedao;

import dao.PrestazioneDAO;
import database_connection.ConnessioneDatabase;
import model.Esito;
import model.Prestazione;
import model.TipoPrestazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestazionePostgresDao implements PrestazioneDAO {

    public boolean salvaPrestazione(Prestazione prestazione) throws SQLException {

        String sql ="INSERT INTO prestazioni_mediche(id_paziente, id_medico, tipo_prestazione, descrizione, esito_prestazione, data_inserimento) " +
                "values (?,?,?,?,?,CURRENT_TIMESTAMP)";
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // AGGIUNTO: Statement.RETURN_GENERATED_KEYS per abilitare il recupero dell'ID seriale
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1,prestazione.getTesseraPaziente());
            pstmt.setInt(2,prestazione.getMedicoAlbo());
            pstmt.setObject(3,prestazione.getTipo_prestazione(),java.sql.Types.OTHER);
            pstmt.setString(4,prestazione.getDescrizione());
            pstmt.setObject(5,prestazione.getEsito(),java.sql.Types.OTHER);

            int righe = pstmt.executeUpdate();
            System.out.println("Righe inserite: " + righe);

            if (righe > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    // CORRETTO: rimosso il System.out.println(generatedKeys.next()) che "consumava" il primo record
                    if (generatedKeys.next()) {
                        // Recuperiamo l'id generato (la prima colonna del set)
                        int idGenerato = generatedKeys.getInt(1);

                        // Salvi l'ID appena generato dentro l'oggetto prestazione
                        prestazione.setId(idGenerato);

                        System.out.println("Prestazione inserita con successo. ID Seriale assegnato: " + idGenerato);
                    }
                }
            }

            return righe > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inserimento");
            return false;
        }

    }
    public boolean aggiornaPrestazione(Prestazione prestazione) throws SQLException {
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Query SQL per aggiornare i campi della prestazione basandosi sull'ID
        String sql = "UPDATE prestazioni_mediche SET " +
                "id_paziente = ?, " +
                "id_medico = ?, " +
                "tipo_prestazione = ?, " +
                "descrizione = ?, " +
                "esito_prestazione = ? " + // rimosso CURRENT_TIMESTAMP a meno che tu non voglia sovrascrivere la data di inserimento
                "WHERE id_prestazione = ?";

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Mappatura dei parametri seguendo la struttura del tuo salvaPrestazione
            pstmt.setString(1, prestazione.getTesseraPaziente());
            pstmt.setInt(2, prestazione.getMedicoAlbo());
            pstmt.setObject(3, prestazione.getTipo_prestazione(), java.sql.Types.OTHER);
            pstmt.setString(4, prestazione.getDescrizione());
            pstmt.setObject(5, prestazione.getEsito(), java.sql.Types.OTHER);

            // Il sesto parametro è l'ID per la clausola WHERE
            pstmt.setInt(6, prestazione.getId());

            // Esecuzione dell'aggiornamento
            int righe = pstmt.executeUpdate();

            // debug
            System.out.println("Righe modificate: " + righe);

            return righe > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella modifica della prestazione");
            return false;
        }
    }

    public List<Prestazione> getTutteLePrestazioni() throws SQLException {
        List<Prestazione> listaPrestazioni = new ArrayList<>();
        String sql = "SELECT id_prestazione, id_paziente, id_medico, tipo_prestazione, descrizione, esito_prestazione, data_inserimento FROM prestazioni_mediche";

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Leggiamo i campi dal database
                int id = rs.getInt("id_prestazione");
                String tesserePaziente = rs.getString("id_paziente");
                int idMedico = rs.getInt("id_medico");
                String tipoString = rs.getString("tipo_prestazione");
                String descrizione = rs.getString("descrizione");
                String esitoString = rs.getString("esito_prestazione");
                Timestamp dataInserimento = rs.getTimestamp("data_inserimento");

                // Mappatura delle stringhe PostgreSQL negli ENUM Java
                TipoPrestazione tipo = TipoPrestazione.valueOf(tipoString); // o logica personalizzata se differiscono i nomi
                Esito esito = Esito.valueOf(esitoString);

                // Creazione dell'oggetto (Usa il costruttore adatto della tua classe model.Prestazione)
                Prestazione p = new Prestazione(tipo, dataInserimento, esito, descrizione, tesserePaziente, idMedico);
                p.setId(id); // Impostiamo l'ID recuperato dal DB

                listaPrestazioni.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return listaPrestazioni;
    }

}
