package database_connection;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
public class ConnessioneDatabase {
    private static final Logger logger = Logger.getLogger(ConnessioneDatabase.class.getName());
    private static ConnessioneDatabase instance;
    private static Connection connection = null;
    private String nome = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String driver = "org.postgresql.Driver";
    String failed = "Connessione al Database fallita.";
    String success = "Connessione al Database effettuata con successo.";

    // COSTRUTTORE
    public ConnessioneDatabase() throws SQLException {
        try {
            Class.forName(getDriver());
            String dbKey = "postgres";
            setConnection(DriverManager.getConnection(getUrl(), getNome(), dbKey));

        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, ex, () -> "Connessione al Database fallita: " + ex.getMessage());
        }

    }

    public static Connection getConnection() {
        return connection;
    }

    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null || getConnection().isClosed()) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static void setConnection(Connection connection) {
        ConnessioneDatabase.connection = connection;
    }

        }