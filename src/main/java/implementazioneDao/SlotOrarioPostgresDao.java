package implementazioneDao;

import dao.RicoveroDAO;
import dao.SlotOrarioDAO;
import database_connection.ConnessioneDatabase;
import model.Agenda;
import model.Paziente;
import model.Ricovero;
import model.SlotOrario;

import java.sql.*;
import java.time.DayOfWeek;
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
            // Ricovero
            pstmt.setString(1, slotOrario.getGiorno());
            pstmt.setTime(2, java.sql.Time.valueOf(slotOrario.getOraInizio()));
            pstmt.setTime(3, java.sql.Time.valueOf((slotOrario.getOraFine())));
            pstmt.setInt(4, slotOrario.getAgenda().getId_agenda());

            //debug
            int righe = pstmt.executeUpdate();

            System.out.println("Righe inserite: " + righe);

            return righe > 0;
        } catch (SQLException e) {
            System.err.println("❌ Errore durante il salvataggio dello slot:");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificaSlot(SlotOrario slotOrario) throws SQLException {
        return false;
    }

    @Override
    public List<SlotOrario> mostraDaAgenda(int idAgenda) throws SQLException {
        List<SlotOrario> slots = new ArrayList<>();

        Connection conn = ConnessioneDatabase.getConnection();

        String sql = "SELECT giorno, ora_inizio, ora_fine,id_agenda FROM slot_orario WHERE id_agenda = ? ORDER BY giorno, ora_inizio";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idAgenda);

        ResultSet rs = pstmt.executeQuery();

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

        rs.close();
        pstmt.close();
        conn.close();

        return slots;    }
}
