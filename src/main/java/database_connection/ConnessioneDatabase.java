package database_connection;

import java.sql.*;
public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    private static Connection connection = null;
    private String nome = "postgres";
    private String password = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String driver = "org.postgresql.Driver";
    String failed = "Connessione al Database fallita.";
    String success = "Connessione al Database effettuata con successo.";

    // COSTRUTTORE
    public ConnessioneDatabase() throws SQLException {
        try {
            Class.forName(getDriver());
            setConnection(DriverManager.getConnection(getUrl(), getNome(), password));

        } catch (ClassNotFoundException ex) {
            System.out.println("Connessione al Database fallita: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }

    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnessioneDatabase();
        } else if (instance.getConnection().isClosed()) {
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

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

        }