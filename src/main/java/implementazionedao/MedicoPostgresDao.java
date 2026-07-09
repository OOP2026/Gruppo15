package implementazionedao;

import password.PasswordUtils;
import dao.MedicoDAO;
import database_connection.ConnessioneDatabase;
import model.Medico;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicoPostgresDao implements MedicoDAO {
    private static final Logger logger = Logger.getLogger(MedicoPostgresDao.class.getName());
    @Override
    public boolean aggiungiMedico(Medico medico) {
        String sql ="INSERT INTO utenti_sistema(nome, cognome, email, password_hash, ruolo, attivo) " +
                "values (?,?,?,?,?,?)";


        String passwordHash = PasswordUtils.hashPassword(medico.getPassword());
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'inizializzazione dell'istanza del database", e);
        }
        // AGGIUNTO: Statement.RETURN_GENERATED_KEYS per abilitare il recupero dell'ID seriale
        try (Connection conn = ConnessioneDatabase.getConnection()){

            try(PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

             pstmt.setString(1, medico.getNome());
             pstmt.setString(2,medico.getCognome());
             pstmt.setString(3, medico.getEmail());
             pstmt.setString(4, passwordHash);
             pstmt.setString(5, "MEDICO");
             pstmt.setBoolean(6, true);

             pstmt.executeUpdate();

             try (ResultSet rs = pstmt.getGeneratedKeys()){
             if (rs.next()) {


                 return true;

        }
             }
            }

        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante l'operazione nel database", e);
            return false;
        }
        return false;
    }
}
