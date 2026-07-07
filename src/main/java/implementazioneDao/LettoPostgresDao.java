package implementazioneDao;

import dao.LettoDAO;
import database_connection.ConnessioneDatabase;
import model.Letto;
import model.Stanza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LettoPostgresDao implements LettoDAO {
    @Override
    public List<Letto> getLettiByStanza(int idStanza) throws SQLException {
        List<Letto> listaLetti = new ArrayList<>();

        String sql = "SELECT codice, occupato FROM letti WHERE codice_stanza = ? ORDER BY codice";

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idStanza);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int codice = rs.getInt("codice");
                boolean occupato = rs.getBoolean("occupato");

                Letto l = new Letto(codice, occupato);

                listaLetti.add(l);
            }
        }

        return listaLetti;
    }

    //metodo che consente di ottenere tutti i letti a partire dall'idReparto

    @Override
    public List<Letto> getLettiDisponibiliByReparto(int idReparto) throws SQLException {
        List<Letto> listaLetti = new ArrayList<>();

        String sql = "SELECT l.codice, l.codice_stanza, l.occupato FROM letti l JOIN stanza s ON l.codice_stanza = s.codice WHERE s.id_reparto = ? AND l.occupato = FALSE";

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idReparto);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int codiceStanza = rs.getInt("codice_stanza");
                Stanza stanza = new Stanza(codiceStanza);

                int codice = rs.getInt("codice");
                boolean occupato = rs.getBoolean("occupato");

                Letto l = new Letto(codice, occupato);
                l.setStanza(stanza);

                listaLetti.add(l);
            }
        }

        return listaLetti;
    }
}
