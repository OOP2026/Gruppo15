package model;

public class Letto {
    private int id_letto;
    private boolean stato = true;
    private Stanza stanza;

    public Letto(int id_letto){
        this.id_letto=id_letto;
    }

    public int getId_letto(){
        return id_letto;
    }

    public void setId_letto(int id_letto){
        this.id_letto=id_letto;
    }
    public boolean getStato(){
        return stato;
    }
    public void setStao(boolean stato){
        this.stato=stato;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }
}
