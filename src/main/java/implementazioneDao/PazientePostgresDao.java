package implementazioneDao;

import dao.PazienteDAO;
import database_connection.ConnessioneDatabase;
import model.Paziente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            pstmt.setObject(5,p.getDiagnosi()); // dovra essere cura, poi si vedrà

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
}
