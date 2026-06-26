package implementazioneDao;

import dao.RicoveroDAO;
import dao.UtenteDAO;
import database_connection.ConnessioneDatabase;
import model.Ricovero;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RicoveroPostgresDao implements RicoveroDAO {
    @Override
    public boolean salvaRicovero(Ricovero ricovero) {

        // debug
        System.out.println("TS: " + ricovero.getTessera_sanitaria());
        System.out.println("Medico: " + ricovero.getMedico_id());
        System.out.println("Diagnosi: " + ricovero.getDiagnosi());
        System.out.println("Reparto: " + ricovero.getReparto());
        System.out.println("Data fine: " + ricovero.getDataFine());

        //modificato leggermente la query per aggiungere tutti i valori, manca data fine nella GUI
        //per ora letto id non è inserito siccome manca come attributo SQL
        String sql = "INSERT INTO ricoveri (paziente_id,medico_id,motivo, data_inizio ,reparto, data_fine) " +
                "VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?)"; // da aggiungere letto_id siccome non è inserito in SQL

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

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




}
