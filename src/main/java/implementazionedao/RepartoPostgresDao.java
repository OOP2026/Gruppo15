package implementazionedao;

import dao.RepartoDAO;
import dao.StanzaDAO;
import database_connection.ConnessioneDatabase;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartoPostgresDao implements RepartoDAO {
    @Override
    public List<Reparto> getReparti() throws SQLException {
        List<Reparto> listaReparti = new ArrayList<>();

        StanzaDAO stanzaDAO = new StanzaPostgresDao();

        String sql = "SELECT id, nome, piano FROM reparto";

        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Leggiamo i campi dal database
                int id = rs.getInt("id");
                String nomeReparto = rs.getString("nome");
                int piano = rs.getInt("piano");

                // Creazione dell'oggetto (Usa il costruttore adatto della tua classe model.Prestazione)
                Reparto r = new Reparto(id, nomeReparto, piano);

                r.setStanze(stanzaDAO.getStanzeByReparto(id));

                listaReparti.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return listaReparti;
    }

    @Override
    public String getNomeReparto(int idReparto) throws SQLException {
        try {
            ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "SELECT id, nome FROM reparto WHERE id = ?";

        // Inizializziamo la variabile per il risultato
        String nomeReparto = null;

        // 1. Nel try metti SOLO l'apertura di Connessione e PreparedStatement
        try (Connection conn = ConnessioneDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 2. I setter vanno presi e messi QUI, dentro le graffe!
            pstmt.setInt(1, idReparto);

            // 3. Esegui la query e gestisci il ResultSet all'interno
            try (ResultSet rs = pstmt.executeQuery()) {
                // 4. USCIRE SE C'È UN RISULTATO (rs.next() sposta il cursore sulla riga trovata)
                if (rs.next()) {
                    nomeReparto = rs.getString("nome");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return nomeReparto; // Ritorna il nome trovato (o null se l'ID non esiste)
    }
    }
