package model;

public class Paziente {
    private String tessera_sanitaria;
    private String nome;
    private String cognome;
    private String diagnosi;
    private String cura;

    public Paziente(String tessera_sanitaria, String nome,String cognome, String diagnosi){
        this.tessera_sanitaria=tessera_sanitaria;
        this.nome=nome;
        this.cognome=cognome;
        this.diagnosi=diagnosi;
    }

    public void setTessera(String tessera_sanitaria){
        this.tessera_sanitaria=tessera_sanitaria;
    }

    public String getTessera(){
        return tessera_sanitaria;
    }
}
