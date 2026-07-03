package implementazioneDao;

import dao.PazienteDAO;
import database_connection.ConnessioneDatabase;
import model.Paziente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PazientePostgresDao implements PazienteDAO {
    public boolean aggiungiPaziente(Paziente p) {
        String sql ="INSERT INTO pazienti(tessera_sanitaria, nome, cognome, diagnosi, cura) " +
                "values (?,?,?,?,?)";
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // AGGIUNTO: Statement.RETURN_GENERATED_KEYS per abilitare il recupero dell'ID seriale
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1,p.getTessera());
            pstmt.setString(2,p.getNome());
            pstmt.setString(3,p.getCognome());
            pstmt.setString(4,p.getDiagnosi());
            pstmt.setString(5,p.getCura()); // dovra essere cura, poi si vedrà

            int righe = pstmt.executeUpdate();
            System.out.println("Righe inserite: " + righe);

            return righe > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inserimento");
            return false;
        }
    }
    public boolean modificaPaziente(Paziente p) {
        String sql= "UPDATE pazienti SET nome=? , cognome =?, diagnosi=?, cura=? WHERE tessera_sanitaria=?";
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,p.getNome());
            pstmt.setString(2,p.getCognome());
            pstmt.setString(3,p.getDiagnosi());
            pstmt.setString(4,p.getCura());
            pstmt.setString(5,p.getTessera());
            int righe = pstmt.executeUpdate();
            System.out.println("Righe inserite: " + righe);

            return righe > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inserimento");
            return false;
        }

    }
    public List<Paziente> listaPazienti() {
        System.out.println("Test2");
        List<Paziente> listaPazienti = new ArrayList<>();
        String sql = "SELECT * FROM pazienti";

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Paziente p = new Paziente(
                            rs.getString("tessera_sanitaria"),
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getString("diagnosi"),
                            rs.getString("cura")
                    );
                    listaPazienti.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'errore o mostra un avviso nella GUI
        }
        return listaPazienti;
    }
}
