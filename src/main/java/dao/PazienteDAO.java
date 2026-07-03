package dao;

import model.Paziente;

import java.util.List;

public interface PazienteDAO
{
    boolean aggiungiPaziente(Paziente p);
    boolean modificaPaziente(Paziente p);

    List<Paziente> listaPazienti();
}
