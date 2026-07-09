package implementazionedao;

import dao.LettoDAO;
import dao.StanzaDAO;
import database_connection.ConnessioneDatabase;
import model.Stanza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StanzaPostgresDao implements StanzaDAO {
    @Override
    public List<Stanza> getStanzeByReparto(int idReparto) throws SQLException {
        List<Stanza> listaStanze = new ArrayList<>();

        LettoDAO lettoDAO = new LettoPostgresDao();


        String sql = "SELECT codice FROM stanza WHERE id_reparto = ?";

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

                int codice = rs.getInt("codice");

                Stanza stanza = new Stanza(codice);
                stanza.setLetti(lettoDAO.getLettiByStanza(codice));

                listaStanze.add(stanza);
            }
        }

        return listaStanze;
    }
}
