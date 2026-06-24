package model;

import java.util.ArrayList;
import java.util.List;

public class Reparto {
    private List<Stanza> stanze;
    private String nomeReparto;

    public Reparto(String nomeReparto){
        this.stanze=new ArrayList<>();
        this.nomeReparto=nomeReparto;
    }

    public List<Stanza> getStanze() {
        return stanze;
    }
    public void setStanze(List<Stanza> stanze) {
        this.stanze = stanze;
    }
    public String getNomeReparto() {
        return nomeReparto;
    }
    public void setNomeReparto(String nomeReparto) {
        this.nomeReparto = nomeReparto;
    }

}
