package model;
import java.util.List;

public class Reparto {
    private int id;
    private String nomeReparto;
    private int piano;
    private List<Stanza> stanze;

    public Reparto(int id, String nomeReparto, int piano){
        this.id = id;
        this.nomeReparto = nomeReparto;
        this.piano = piano;
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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPiano() {
        return piano;
    }
}
