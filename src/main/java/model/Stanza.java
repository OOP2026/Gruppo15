package model;

import java.util.ArrayList;
import java.util.List;

public class Stanza {
    // la Stanza è una lista di letti, non risulta più utile capienza letti siccome possiamo calcolarlo con size()
    private List <Letto> letti;

    // Costruttore Stanza
    public Stanza(int capienzaLetti){
        this.letti=new ArrayList<>();

        // Questa parte di codice commentata crea la lista di letti a partire da un numero che diamo noi come capienza, ma deve essere sistemato idLetto

        /* for(int i = 0; i < capienzaLetti; i++){
            letti.add(new Letto(// sarebbe da correggere l'id in Letto, siccome non deve essere passato da fuori ma aggiornato a ogni creazione di un nuovo letto));
        }; */
    }

    // Metodo per ottenere la capienza dei letti
    public int getCapienza(){
        return letti.size();
    }
    public void setCapienza(int capienzaLetti){
        this.letti=new ArrayList<>();
    }
}
