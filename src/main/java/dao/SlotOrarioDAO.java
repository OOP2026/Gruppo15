package dao;

import model.SlotOrario;

import java.sql.SQLException;
import java.util.List;

public interface SlotOrarioDAO {
    boolean creaSlot(SlotOrario slotOrario) throws SQLException;

    boolean aggiornaSlot(SlotOrario slotOrario) throws SQLException;

    List<SlotOrario> mostraDaAgenda(int idAgenda) throws SQLException;
    List<SlotOrario> mostraSlotOrari() throws SQLException;
}
