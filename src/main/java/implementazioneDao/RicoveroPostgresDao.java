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

        // debug
        System.out.println("TS: " + ricovero.getTessera_sanitaria());
        System.out.println("Medico: " + ricovero.getMedico_id());
        System.out.println("Diagnosi: " + ricovero.getDiagnosi());
        System.out.println("Reparto: " + ricovero.getReparto());
        System.out.println("Data fine: " + ricovero.getDataFine());

        //modificato leggermente la query per aggiungere tutti i valori, manca data fine nella GUI
        //per ora letto id non è inserito siccome manca come attributo SQL
        String sqlPaziente="INSERT INTO pazienti (tessera_sanitaria,nome,cognome,diagnosi)" +
                "VALUES (?,?,?,?)";
        String sql = "INSERT INTO ricoveri (paziente_id,medico_id,motivo, data_inizio ,reparto, data_fine) " +
                "VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?)"; // da aggiungere letto_id siccome non è inserito in SQL

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
           // pstmt.setInt(4, ricovero.getId_letto());
            pstmt.setDate(5, (Date) ricovero.getDataFine());


            //debug
            int righe = pstmt.executeUpdate();

            System.out.println("Righe inserite: " + righe);

            return righe > 0;
        } catch (SQLException e) {
            System.err.println("❌ Errore durante il salvataggio del ricovero:");
            e.printStackTrace();
            return false;
        }
    }

    public Ricovero mostraRicovero(String tesseraSanitaria) throws SQLException {

        System.out.println("TS: " + tesseraSanitaria);
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql="SELECT paziente_id,medico_id,data_inizio,data_fine,reparto,motivo FROM ricoveri where paziente_id=?";
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, tesseraSanitaria);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) { // Entra qui solo se trova il record
                    Ricovero r = new Ricovero();
                    r.setTessera_sanitaria(rs.getString("paziente_id"));
                    r.setDiagnosi(rs.getString("motivo"));
                    r.setDataInizio(rs.getTimestamp("data_inizio"));
                    r.setMedico_id(rs.getInt("medico_id"));
                    r.setReparto(rs.getString("reparto"));

                    return r;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return null;

        }
    }




}
