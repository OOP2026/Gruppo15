package dao;

import model.Letto;

import java.sql.SQLException;
import java.util.List;

public interface LettoDAO {
    List<Letto> getLettiByStanza (int idStanza) throws SQLException;

    List<Letto> getLettiDisponibiliByReparto (int idReparto) throws SQLException;
}
