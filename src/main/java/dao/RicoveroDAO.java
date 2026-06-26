package dao;
import model.Ricovero;

import java.sql.SQLException;
import java.util.List;

public interface RicoveroDAO {
    boolean salvaRicovero(Ricovero ricovero) throws SQLException; //per salvare il ricovero nel database

    // vedere tutti i ricoveri di un reparto
    //List<Ricovero> trovaRicoveriAttiviPerReparto(String reparto);

}