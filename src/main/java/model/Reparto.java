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
}
