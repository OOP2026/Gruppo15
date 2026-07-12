package implementazionedao;

import dao.SlotOrarioDAO;
import database_connection.ConnessioneDatabase;
import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SlotOrarioPostgresDao implements SlotOrarioDAO{
    @Override
    public boolean creaSlot(SlotOrario slotOrario) throws SQLException {
        String sql = "INSERT INTO slot_orario (giorno, ora_inizio, ora_fine, id_agenda) " +
                "VALUES (?::giorno_settimana, ?, ?, ?)"; // da aggiungere letto_id siccome non è inserito in SQL

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, slotOrario.getGiorno());
            pstmt.setTime(2, java.sql.Time.valueOf(slotOrario.getOraInizio()));
            pstmt.setTime(3, java.sql.Time.valueOf((slotOrario.getOraFine())));
            pstmt.setInt(4, slotOrario.getAgenda().getId_agenda());

            //debug
            int righe = pstmt.executeUpdate();
            if (righe > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Recuperiamo l'id generato (la prima colonna del set)
                        int idGenerato = generatedKeys.getInt(1);

                        // Salvi l'ID appena generato dentro l'oggetto prestazione
                        slotOrario.setId_slot(idGenerato);

                        System.out.println("Prestazione inserita con successo. ID Seriale assegnato: " + idGenerato);
                    }
                }
            }

            System.out.println("Righe inserite: " + righe);

            return righe > 0;
        } catch (SQLException e) {
            System.err.println("❌ Errore durante il salvataggio dello slot:");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean aggiornaSlot(SlotOrario slotOrario) {
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "UPDATE slot_orario SET giorno = ?::giorno_settimana, ora_inizio = ?, ora_fine = ?, id_agenda = ? " +
                "WHERE id_slot = ?";

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 1. Impostiamo i nuovi valori
            pstmt.setObject(1, slotOrario.getGiorno()); // da rivedere siccome il giorno non è string
            pstmt.setTime(2, java.sql.Time.valueOf(slotOrario.getOraInizio()));
            pstmt.setTime(3, java.sql.Time.valueOf(slotOrario.getOraFine()));
            pstmt.setInt(4, slotOrario.getAgenda().getId_agenda());

            // 2. Identifichiamo LO SLOT SPECIFICO da aggiornare tramite il suo ID nella clausola WHERE
            // Assicurati che l'oggetto slotOrario che passi a questa funzione contenga l'ID recuperato dal DB
            pstmt.setInt(5, slotOrario.getId_slot());

            int righeModificate = pstmt.executeUpdate();

            System.out.println("Righe slot aggiornate: " + righeModificate);

            return righeModificate > 0;

        } catch (SQLException e) {
            System.err.println("❌ Errore durante l'aggiornamento dello slot:");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SlotOrario> mostraDaAgenda(int idAgenda) throws SQLException {

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<SlotOrario> slots = new ArrayList<>();



        String sql = "SELECT giorno, ora_inizio, ora_fine,id_agenda FROM slot_orario WHERE id_agenda = ? ORDER BY giorno, ora_inizio";

        try(Connection conn = ConnessioneDatabase.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, idAgenda);
            try (ResultSet rs = pstmt.executeQuery()){




                while (rs.next()) {
                    //creiamo prima un agenda siccome nel costruttore non prendiamo solo l'id ma tutto l'oggetto
                    Agenda agenda = new Agenda();
                    agenda.setId_agenda(rs.getInt("id_agenda"));

                    SlotOrario slot = new SlotOrario(
                            rs.getString("giorno"),
                            rs.getTime("ora_inizio").toLocalTime(),
                            rs.getTime("ora_fine").toLocalTime(),
                            agenda
                    );

                    slots.add(slot);
                }

                return slots;
            }
        }

    }
    public List<SlotOrario> mostraSlotOrari() throws SQLException {
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<SlotOrario> slots = new ArrayList<>();



        //per ottenere nome e cognome dei medici si fa una doppia join per raggiungere la tabella degli utenti, in modo da ottenere nome e cognome
        String sql = "SELECT s.id_slot, s.giorno, s.ora_inizio, s.ora_fine, s.id_agenda, u.nome, u.cognome FROM slot_orario s " +
                "JOIN agenda a ON s.id_agenda = a.id_agenda " +
                "JOIN utenti_sistema u ON a.id_medico = u.id ORDER BY s.id_agenda, s.giorno, s.ora_inizio";

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()) {
                //creiamo prima un agenda siccome nel costruttore non prendiamo solo l'id ma tutto l'oggetto
                Agenda agenda = new Agenda();
                agenda.setId_agenda(rs.getInt("id_agenda"));

                Utente utente = new Utente();
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setRuolo("MEDICO");

                agenda.setMedico(utente);

                SlotOrario slot = new SlotOrario(
                        rs.getString("giorno"),
                        rs.getTime("ora_inizio").toLocalTime(),
                        rs.getTime("ora_fine").toLocalTime(),
                        agenda

                );
                slot.setId_slot(rs.getInt("id_slot"));
                slots.add(slot);
            }

            return slots;
        }





    }
}
