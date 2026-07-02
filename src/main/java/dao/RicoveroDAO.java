package dao;
import model.Paziente;
import model.Ricovero;

import java.sql.SQLException;
import java.util.List;

public interface RicoveroDAO {

    boolean salvaRicovero(Ricovero ricovero,Paziente paziente) throws SQLException; //per salvare il ricovero nel database


      boolean aggiornaRicovero(Ricovero ricovero,Paziente paziente) throws SQLException;

//    Ricovero mostraRicovero(String tesseraSanitaria) throws SQLException;
    List<Ricovero> getRicoveriPerPaziente(String tesseraSanitaria) throws SQLException;
}
