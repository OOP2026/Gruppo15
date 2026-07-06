package implementazioneDao;

import dao.AgendaDAO;
import dao.SlotOrarioDAO;
import database_connection.ConnessioneDatabase;
import model.Agenda;
import model.Paziente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public boolean assegnaAgenda(int id_agenda, int id_medico) throws SQLException {
        Agenda agenda = new Agenda();
        String sql ="INSERT INTO agenda(id_agenda, id_medico) " +
                "values (?,?)";
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,id_agenda);
            pstmt.setInt(2,id_medico);

            int righe = pstmt.executeUpdate();
            System.out.println("Righe inserite: " + righe);
            JOptionPane.showMessageDialog(null, "Agenda assegnata con successo!");
            return righe > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inserimento");
            // Gestione dell'errore di chiave per PostgreSQL
            // 23503 = Violazione di Foreign Key (chiave non trovata nella tabella padre)
            // 23505 = Violazione di Unique/Primary Key (chiave già esistente)
            String sqlState = e.getSQLState();

            if ("23503".equals(sqlState)) {
                javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Errore di associazione: L'agenda specificata non esiste.",
                        "Errore Chiave Esterna",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            } else if ("23505".equals(sqlState)) {
                javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Impossibile inserire lo slot: Esiste già uno slot con questi dati.",
                        "Errore Chiave Duplicata",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            } else {
                // Messaggio generico per gli altri errori SQL (es. l'errore del tipo ENUM di prima)
                javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Errore database: " + e.getMessage(),
                        "Errore di Sistema",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
            return false;
        }

    }


    public List<Agenda> listaAgenda() throws SQLException {
        List<Agenda> agendaList = new ArrayList<>();
        String sql = "SELECT * FROM agenda";
        try {
            ConnessioneDatabase.getInstance();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Agenda agenda = new Agenda(
                            rs.getInt("id_agenda"),
                            rs.getInt("id_medico")
                    );
                    agendaList.add(agenda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'errore o mostra un avviso nella GUI
        }
        return agendaList;
        }
    }

