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
            pstmt.setDate(5, (Date) ricovero.getDataFine());
            pstmt.setInt(6, ricovero.getId_letto());


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



    public List<Ricovero> getRicoveriPerPaziente(String tesseraSanitaria){
        List<Ricovero> listaRicoveri = new ArrayList<>();
        String sql = "SELECT id,paziente_id,medico_id,data_inizio, data_fine, reparto, motivo,id_letto FROM ricoveri WHERE paziente_id = ?";

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
                            rs.getInt("id_letto")
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
