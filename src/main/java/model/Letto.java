package model;

public class Letto {
    private int id_letto;
    private boolean occupato = false;

    public Letto(int id_letto){
        this.id_letto=id_letto;
    }

    public int getId_letto(){
        return id_letto;
    }

    public void setId_letto(int id_letto){
        this.id_letto=id_letto;
    }
    public boolean isOccupato(){
        return occupato;
    }
    public void setOccupato(boolean occupato){
        this.occupato=occupato;
    }
}
