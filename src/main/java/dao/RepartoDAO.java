package dao;

import model.Reparto;

import java.sql.SQLException;
import java.util.List;

public interface RepartoDAO {
    List<Reparto> getReparti() throws SQLException;
    String getNomeReparto(int idReparto) throws SQLException;

}
