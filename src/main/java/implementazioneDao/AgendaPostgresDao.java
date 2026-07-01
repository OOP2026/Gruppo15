package implementazioneDao;

import dao.AgendaDAO;
import dao.SlotOrarioDAO;
import database_connection.ConnessioneDatabase;
import model.Agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgendaPostgresDao implements AgendaDAO {
    @Override
    public Agenda trovaDaMedico(int idMedico) throws SQLException {
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connection conn = ConnessioneDatabase.getConnection();

        //select per trovare l'agenda a partire dall'id medico
        String sql = "SELECT id_agenda FROM agenda WHERE id_medico = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idMedico);

        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()){ return null;}

        Agenda agenda = new Agenda();
        agenda.setId_agenda(rs.getInt("id_agenda"));

        SlotOrarioDAO slotOrarioDAO = new SlotOrarioPostgresDao();
        agenda.setSlots(slotOrarioDAO.mostraDaAgenda(agenda.getId_agenda()));

        rs.close();
        pstmt.close();
        conn.close();

        return agenda;
    }

    @Override
    public boolean creaAgenda(Agenda agenda) throws SQLException {
        return false;
    }

    @Override
    public boolean modificaAgenda(Agenda agenda) throws SQLException {
        return false;
    }

    @Override
    public boolean eliminaAgenda(int idAgenda) throws SQLException {
        return false;
    }
}
