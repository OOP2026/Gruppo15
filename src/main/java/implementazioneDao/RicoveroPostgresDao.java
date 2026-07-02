package implementazioneDao;

import dao.RicoveroDAO;
import dao.UtenteDAO;
import database_connection.ConnessioneDatabase;
import model.Paziente;
import model.Ricovero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RicoveroPostgresDao implements RicoveroDAO {
    @Override
    public boolean salvaRicovero(Ricovero ricovero,Paziente paziente) {

        //modificato leggermente la query per aggiungere tutti i valori, manca data fine nella GUI
        //per ora letto id non è inserito siccome manca come attributo SQL
        String sqlPaziente="INSERT INTO pazienti (tessera_sanitaria,nome,cognome,diagnosi)" +
                "VALUES (?,?,?,?)";
        String sql = "INSERT INTO ricoveri (paziente_id,medico_id,motivo, data_inizio ,reparto, data_fine,id_letto) " +
                "VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?,?)"; // da aggiungere letto_id siccome non è inserito in SQL

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             PreparedStatement pstmtPaziente = conn.prepareStatement(sqlPaziente);

             // paziente
            try {
                pstmtPaziente.setString(1, paziente.getTessera());
                pstmtPaziente.setString(2, paziente.getNome());
                pstmtPaziente.setString(3, paziente.getCognome());
                pstmtPaziente.setString(4, paziente.getDiagnosi());
                pstmtPaziente.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println("Esiste già il paziente");
            }
             // Ricovero
            pstmt.setString(1, ricovero.getTessera_sanitaria());
            pstmt.setInt(2, ricovero.getMedico_id());
            pstmt.setString(3, ricovero.getDiagnosi());
            pstmt.setString(4, ricovero.getReparto());
            pstmt.setTimestamp(5, (Timestamp) ricovero.getDataFine());
            pstmt.setInt(6, ricovero.getId_letto());


            //debug
            int righe = pstmt.executeUpdate();

            System.out.println("Righe inserite: " + righe);
            if (righe > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    // CORRETTO: rimosso il System.out.println(generatedKeys.next()) che "consumava" il primo record
                    if (generatedKeys.next()) {
                        // Recuperiamo l'id generato (la prima colonna del set)
                        int idGenerato = generatedKeys.getInt(1);

                        // Salvi l'ID appena generato dentro l'oggetto prestazione
                        ricovero.setId_ricovero(idGenerato);

                        System.out.println("Ricovero inserito con successo. ID Seriale assegnato: " + idGenerato);
                    }
                }
            }
            return righe > 0;
        } catch (SQLException e) {
            System.err.println("❌ Errore durante il salvataggio del ricovero:");
            e.printStackTrace();
            return false;
        }
    }
    public boolean aggiornaRicovero(Ricovero ricovero, Paziente paziente) {
        // Query per aggiornare il paziente usando la tessera sanitaria come chiave
        String sqlPaziente = "UPDATE pazienti SET nome = ?, cognome = ?, diagnosi = ? WHERE tessera_sanitaria = ?";

        // Query per aggiornare il ricovero usando l'id_ricovero come chiave
        String sqlRicovero = "UPDATE ricoveri SET paziente_id = ?, medico_id = ?, motivo = ?, reparto = ?, data_fine = ?, id_letto = ? WHERE id = ?";

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmtPaziente = conn.prepareStatement(sqlPaziente);
             PreparedStatement pstmtRicovero = conn.prepareStatement(sqlRicovero)) {

            // Disabilitiamo l'autocommit per gestire i due UPDATE come un'unica TRANSAZIONE atomica
            conn.setAutoCommit(false);

            try {
                // 1. Aggiornamento Paziente
                pstmtPaziente.setString(1, paziente.getNome());
                pstmtPaziente.setString(2, paziente.getCognome());
                pstmtPaziente.setString(3, paziente.getDiagnosi());
                pstmtPaziente.setString(4, paziente.getTessera()); // Il WHERE prende la tessera
                pstmtPaziente.executeUpdate();

                // 2. Aggiornamento Ricovero
                pstmtRicovero.setString(1, ricovero.getTessera_sanitaria());
                pstmtRicovero.setInt(2, ricovero.getMedico_id());
                pstmtRicovero.setString(3, ricovero.getDiagnosi());
                pstmtRicovero.setString(4, ricovero.getReparto());

                // Gestione sicura del cast/conversione della data
                if (ricovero.getDataFine() != null) {
                    pstmtRicovero.setTimestamp(5, new java.sql.Timestamp(ricovero.getDataFine().getTime()));
                } else {
                    pstmtRicovero.setNull(5, java.sql.Types.TIMESTAMP);
                }

                pstmtRicovero.setInt(6, ricovero.getId_letto());
                pstmtRicovero.setInt(7, ricovero.getId_ricovero()); // Il WHERE prende l'ID del ricovero

                int righeModificate = pstmtRicovero.executeUpdate();

                // Se tutto è andato a buon fine, salviamo nel DB
                conn.commit();
                System.out.println("Righe ricovero aggiornate: " + righeModificate);

                return righeModificate > 0;

            } catch (SQLException e) {
                // In caso di errore in una delle due tabelle, facciamo il rollback per non lasciare dati inconsistenti
                conn.rollback();
                System.err.println("❌ Errore durante l'esecuzione delle query. Rollback eseguito.");
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            System.err.println("❌ Errore di connessione durante l'aggiornamento del ricovero:");
            e.printStackTrace();
            return false;
        }
    }

//    @Override
//    public boolean aggiornaRicovero(Ricovero ricovero, Paziente paziente) throws SQLException {
//        try {
//            ConnessioneDatabase.getInstance();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        // Query SQL per aggiornare i campi
//        String sql = "UPDATE ricoveri SET " +
//                "paziente_id = ?, " +
//                "medico_id = ?, " +
//                "data_inizio = ?, " +
//                "data_fine = ?, " +
//                "motivo = ? " +
//                "reparto = ? " +
//                "id_letto = ? " +
//                "WHERE id_ricovero = ?";
//
//        try (Connection conn = ConnessioneDatabase.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            // Mappatura dei parametri
//            pstmt.setString(1, ricovero.getTessera_sanitaria());
//            pstmt.setInt(2, ricovero.getMedico_id());
//            pstmt.setObject(3, ricovero.getDataInizio());
//            pstmt.setObject(4, ricovero.getDataFine());
//            pstmt.setString(5, ricovero.getDiagnosi());
//            pstmt.setString(6, ricovero.getReparto());
//            pstmt.setInt(7, ricovero.getId_letto());
//            pstmt.setInt(8, ricovero.getId_ricovero());
//
//            // Esecuzione dell'aggiornamento
//            int righe = pstmt.executeUpdate();
//
//            // debug
//            System.out.println("Righe modificate: " + righe);
//
//            return righe > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Errore nella modifica della prestazione");
//            return false;
//        }
//    }

    public List<Ricovero> getRicoveriPerPaziente(String tesseraSanitaria){
        List<Ricovero> listaRicoveri = new ArrayList<>();
        String sql = "SELECT paziente_id,id,medico_id,data_inizio, data_fine, reparto, motivo,id_letto FROM ricoveri WHERE paziente_id = ?";

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tesseraSanitaria);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Ricovero r = new Ricovero(
                            rs.getString("paziente_id"),
                            rs.getInt("medico_id"),
                            rs.getString("motivo"),
                            rs.getString("reparto"),
                            rs.getInt("id_letto"),
                            rs.getTimestamp("data_inizio"),
                            rs.getTimestamp("data_fine"),
                            rs.getInt("id")
                    );
                    listaRicoveri.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'errore o mostra un avviso nella GUI
        }
        return listaRicoveri;

    }




}
