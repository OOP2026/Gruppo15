package implementazioneDao;

import Password.PasswordUtils;
import dao.MedicoDAO;
import database_connection.ConnessioneDatabase;
import model.Medico;

import java.sql.*;

public class MedicoPostgresDao implements MedicoDAO {

    @Override
    public boolean aggiungiMedico(Medico medico) {
        String sql ="INSERT INTO utenti_sistema(nome, cognome, email, password_hash, ruolo) " +
                "values (?,?,?,?,?)";


        String passwordHash = PasswordUtils.hashPassword(medico.getPassword());
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // AGGIUNTO: Statement.RETURN_GENERATED_KEYS per abilitare il recupero dell'ID seriale
        try (Connection conn = ConnessioneDatabase.getConnection()){

            try(PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

             pstmt.setString(1, medico.getNome());
             pstmt.setString(2,medico.getCognome());
             pstmt.setString(3, medico.getEmail());
             pstmt.setString(4, passwordHash);
             pstmt.setString(5, "MEDICO");

             int righe = pstmt.executeUpdate();

             try (ResultSet rs = pstmt.getGeneratedKeys()){
             if (rs.next()) {
            int idUtente = rs.getInt(1);

            // Qui facciamo l'INSERT nella tabella medici
                 String sqlMedici = "INSERT INTO medici (id, nome, cognome, email, password_hash, attivo) VALUES (?, ?, ?, ?, ?, ?)";


                 try (PreparedStatement pstmtMedico = conn.prepareStatement(sqlMedici)) {

                     pstmtMedico.setInt(1, idUtente);
                     pstmtMedico.setString(2, medico.getNome());
                     pstmtMedico.setString(3, medico.getCognome());
                     pstmtMedico.setString(4, medico.getEmail());
                     pstmtMedico.setString(5, passwordHash);
                     pstmtMedico.setBoolean(6, true);

                     pstmtMedico.executeUpdate();
                 }

                 return true;

        }
             }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiunta del medico");
            return false;
        }
        return false;
    }
}
