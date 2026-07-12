package implementazionedao;

import dao.AgendaDAO;
import dao.SlotOrarioDAO;
import database_connection.ConnessioneDatabase;
import model.Agenda;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AgendaPostgresDao implements AgendaDAO {
    private static final Logger logger = Logger.getLogger(AgendaPostgresDao.class.getName());
    @Override
    public Agenda trovaDaMedico(int idMedico) throws SQLException {

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore di connessione al db",e);
        }


        //select per trovare l'agenda a partire dall'id medico
        String sql = "SELECT id_agenda FROM agenda WHERE id_medico = ?";
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idMedico);

            try(ResultSet rs = pstmt.executeQuery()){

                if(!rs.next()){ return null;}

                Agenda agenda = new Agenda();
                agenda.setId_agenda(rs.getInt("id_agenda"));

                SlotOrarioDAO slotOrarioDAO = new SlotOrarioPostgresDao();
                agenda.setSlots(slotOrarioDAO.mostraDaAgenda(agenda.getId_agenda()));

                return agenda;
            }
        }



    }

    @Override
    public boolean creaAgenda(int idAgenda, int idMedico) {

        String sql ="INSERT INTO agenda(id_agenda, id_medico) " +
                "values (?,?)";
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante la connessione al DB", e);
        }


        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,idAgenda);
            pstmt.setInt(2,idMedico);

            int righe = pstmt.executeUpdate();
            logger.log(Level.INFO, "Sono state inserite {0} righe.", righe);
            JOptionPane.showMessageDialog(null, "Agenda assegnata con successo!");
            return righe > 0;
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'assegnazione dell'agenda nel DB", e);
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


    public List<Agenda> listaAgenda() {
        List<Agenda> agendaList = new ArrayList<>();
        String sql = "SELECT * FROM agenda";
        try {
            ConnessioneDatabase.getInstance();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore connessione DB", e);
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
            logger.log(Level.SEVERE, "Errore durante l'assegnazione dell'agenda nel DB,2", e);
            // Gestisci l'errore o mostra un avviso nella GUI
        }
        return agendaList;
        }
    }

