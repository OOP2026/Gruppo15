package dao;

import model.Agenda;

import java.sql.SQLException;
import java.util.List;

public interface AgendaDAO {
    Agenda trovaDaMedico(int idMedico) throws SQLException;

    boolean creaAgenda(int idAgenda, int idMedico) throws SQLException;

    List<Agenda> listaAgenda() throws SQLException;
}
