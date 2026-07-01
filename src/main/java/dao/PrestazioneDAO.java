package dao;

import model.Prestazione;

import java.sql.SQLException;
import java.util.List;

public interface PrestazioneDAO {

    boolean salvaPrestazione(Prestazione prestazione) throws SQLException;


     boolean aggiornaPrestazione(Prestazione prestazione) throws SQLException ;

     List<Prestazione> getTutteLePrestazioni() throws SQLException;
}

