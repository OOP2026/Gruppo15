package dao;

import model.Agenda;

import java.sql.SQLException;
import java.util.List;

public interface AgendaDAO {
    Agenda trovaDaMedico(int idMedico) throws SQLException;

    boolean creaAgenda(Agenda agenda) throws SQLException;

    boolean modificaAgenda(Agenda agenda) throws SQLException;

    boolean eliminaAgenda(int idAgenda) throws SQLException;
    boolean assegnaAgenda(int id_agenda, int id_medico) throws SQLException;

    List<Agenda> listaAgenda() throws SQLException;
}
