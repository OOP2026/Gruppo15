package dao;

import model.Prestazione;

import java.sql.SQLException;

public interface PrestazioneDAO {

    boolean salvaPrestazione(Prestazione prestazione) throws SQLException;

     //boolean modificaPrestazione(Prestazione prestazione);
     boolean aggiornaPrestazione(Prestazione prestazione) throws SQLException ;
}

