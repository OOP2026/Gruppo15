package implementazioneDao;

import dao.PazienteDAO;
import database_connection.ConnessioneDatabase;
import model.Paziente;

import javax.swing.*;
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


        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,p.getTessera());
            pstmt.setString(2,p.getNome());
            pstmt.setString(3,p.getCognome());
            pstmt.setString(4,p.getDiagnosi());
            pstmt.setString(5,p.getCura()); // dovra essere cura, poi si vedrà

            int righe = pstmt.executeUpdate();
            System.out.println("Righe inserite: " + righe);
            JOptionPane.showMessageDialog(null, "Paziente aggiunto con successo!");
            return righe > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nell'inserimento: " + e.getMessage());
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

            if(righe<=0){
                JOptionPane.showMessageDialog(null, "Paziente inesistente", "Errore", JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Errore: Impossibile modificare. La tessera sanitaria inserita è inesistente.");

            }

            return righe > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            return false;
        }

    }
    public List<Paziente> listaPazienti() {
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
    public boolean eliminaPaziente(String idPaziente) throws SQLException{
        String sql="DELETE FROM pazienti WHERE tessera_sanitaria=?";
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Imposta l'ID come parametro della query
            pstmt.setString(1, idPaziente);


            int righeCoinvolte = pstmt.executeUpdate();

            // Se righeCoinvolte > 0, significa che l'elemento è stato eliminato
            if(righeCoinvolte<=0){
                JOptionPane.showMessageDialog(null, "Paziente inesistente", "", JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Errore: Impossibile eliminare. La tessera sanitaria inserita è inesistente.");

            }
            return righeCoinvolte > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione o rilanciala alla GUI
            throw e;
        }

    }
}
