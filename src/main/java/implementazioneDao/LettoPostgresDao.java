package implementazioneDao;

import dao.LettoDAO;
import database_connection.ConnessioneDatabase;
import model.Letto;

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

        String sql = "SELECT codice, occupato FROM letti WHERE codice_stanza = ?";

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
}
