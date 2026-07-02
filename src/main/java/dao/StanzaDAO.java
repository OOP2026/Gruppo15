package dao;

import model.Stanza;

import java.sql.SQLException;
import java.util.List;

public interface StanzaDAO {
    List<Stanza> getStanzeByReparto (int idReparto) throws SQLException;

}
