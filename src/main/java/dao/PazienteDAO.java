package dao;

import model.Paziente;

import java.sql.SQLException;
import java.util.List;

public interface PazienteDAO
{
    boolean aggiungiPaziente(Paziente p) throws SQLException;
    boolean modificaPaziente(Paziente p) throws SQLException;
    List<Paziente> listaPazienti();
}
