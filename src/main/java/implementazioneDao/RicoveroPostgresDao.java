package implementazioneDao;

import dao.RicoveroDAO;
import dao.UtenteDAO;
import database_connection.ConnessioneDatabase;
import model.Ricovero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RicoveroPostgresDao implements RicoveroDAO {
    @Override
    public boolean salvaRicovero(Ricovero ricovero) {
        String sql = "INSERT INTO ricoveri (paziente_id,medico_id, inizio_ricovero , id_reparto, id_letto) " +
                "VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?)";

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ricovero.getTessera_sanitaria());
            pstmt.setInt(2, ricovero.getMedico_id());
            pstmt.setInt(3, ricovero.getId_reparto());
            pstmt.setInt(4, ricovero.getId_letto());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Errore durante il salvataggio del ricovero:");
            e.printStackTrace();
            return false;
        }
    }




}
