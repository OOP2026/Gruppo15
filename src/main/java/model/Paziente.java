package model;

public class Paziente {
    private String nome;
    private String cognome;
    private String diagnosi;
    private String cura;

    public Paziente(String nome,String cognome, String diagnosi){
        this.nome=nome;
        this.cognome=cognome;
        this.diagnosi=diagnosi;
    }
}
