package model;

public class Letto {
    private int id_letto;
    private boolean occupato = false;

    public Letto(int id_letto){
        this.id_letto=id_letto;
    }

    public void modificaStato(){
        occupato = !occupato;
    }

    public boolean statoLetto(){
        return occupato;
    }
}
