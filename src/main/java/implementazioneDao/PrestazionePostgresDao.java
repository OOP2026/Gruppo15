package implementazioneDao;

import dao.PrestazioneDAO;
import dao.RicoveroDAO;
import database_connection.ConnessioneDatabase;
import model.Prestazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestazionePostgresDao implements PrestazioneDAO {

    public boolean salvaPrestazione(Prestazione prestazione) throws SQLException {

        String sql ="INSERT INTO prestazioni_mediche(id_paziente, id_medico,tipo_prestazione,descrizione,esito_prestazione,data_inserimento) " +
                "values (?,?,?,?,?,CURRENT_TIMESTAMP)";
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,prestazione.getTesseraPaziente());
            pstmt.setInt(2,prestazione.getMedicoAlbo());
            pstmt.setObject(3,prestazione.getTipo_prestazione(),java.sql.Types.OTHER);
            pstmt.setString(4,prestazione.getDescrizione());
            pstmt.setObject(5,prestazione.getEsito(),java.sql.Types.OTHER);

            // debug
            int righe = pstmt.executeUpdate();

            System.out.println("Righe inserite: " + righe);
            if (righe > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Recuperiamo l'id generato (la prima colonna del set di chiavi generate)
                        int idGenerato = generatedKeys.getInt(1);

                        // Salvi l'ID appena generato dentro l'oggetto, così l'applicazione lo conosce
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

}
