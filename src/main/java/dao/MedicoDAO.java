package dao;

import model.Medico;

import java.sql.SQLException;

public interface MedicoDAO {

    boolean aggiungiMedico(Medico medico) throws SQLException;

}