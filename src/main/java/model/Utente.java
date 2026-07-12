package model;

public class Utente {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String ruolo;
    private Boolean attivo;

    public Utente(){

    }

    public Utente(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Utente(String nome, String cognome, String ruolo){
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public boolean email(String email, String password) {
        return ( email.equals(this.email) && password.equals(this.password));
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;

    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
    public String getRuolo() {
        return ruolo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Boolean getAttivo() {
        return attivo;
    }
}



