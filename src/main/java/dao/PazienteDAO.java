package dao;

import model.Paziente;

import java.sql.SQLException;
import java.util.List;

public interface PazienteDAO
{
    boolean aggiungiPaziente(Paziente p);
    boolean modificaPaziente(Paziente p);
    boolean eliminaPaziente(String idPaziente) throws SQLException;
    List<Paziente> listaPazienti();
}
