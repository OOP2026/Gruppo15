package dao;

import model.Utente; // per fare in modo che si possa creare l'interfaccia utente

import java.sql.SQLException;

public interface UtenteDAO {
    Utente login(String email, String password) throws SQLException;
}