package model;

import java.util.ArrayList;
import java.util.List;

public class Stanza {
    // la Stanza è una lista di letti, non risulta più utile capienza letti siccome possiamo calcolarlo con size()
    private List <Letto> letti;
    private int codice;
    private Reparto reparto;

    // Costruttore Stanza
    public Stanza(int codice, Reparto reparto){
        this.codice = codice;
        this.reparto = reparto;
        this.letti = new ArrayList<>();
    }

    public Stanza (int codice){
        this.codice = codice;
    }

    public int getCodice(){return codice;}
    public void setCodice(int codice){this.codice = codice;}

    public Reparto getReparto(){return reparto;}
    public void setReparto(Reparto reparto){this.reparto = reparto;}

    public List<Letto> getLetti() {
        return letti;
    }

    public void setLetti(List<Letto> letti) {
        this.letti = letti;
    }

    // Metodo per ottenere la capienza dei letti
    public int getCapienza(){
        return letti.size();
    }
    public void setCapienza(int capienzaLetti){
        this.letti=new ArrayList<>();
    }
}
