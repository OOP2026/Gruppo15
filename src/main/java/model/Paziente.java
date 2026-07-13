package model;

public class Paziente {
    private String tessera_sanitaria;
    private String nome;
    private String cognome;
    private String diagnosi;
    private String cura;

    public Paziente(String tessera_sanitaria, String nome, String cognome, String diagnosi, String cura) {
        this.tessera_sanitaria=tessera_sanitaria;
        this.nome=nome;
        this.cognome=cognome;
        this.diagnosi=diagnosi;
        this.cura=cura;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    public String getCura(){
        return cura;
    }
    public String getDiagnosi(){
        return diagnosi;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome(){
        return cognome;
    }

    public void setTessera(String tessera_sanitaria){
        this.tessera_sanitaria=tessera_sanitaria;
    }

    public String getTessera(){
        return tessera_sanitaria;
    }


}
