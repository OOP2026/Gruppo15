package model;

import java.time.LocalTime;
import java.util.Date;

public class Medico {
    public String n_albo;
    private String reparto;
    private String tipoPrestazione;
    private Date data;
    private Esito esito;
    private String descrizione;
    private LocalTime ora_prestazione;
    private Agenda agenda;

    public Medico(){

    }

    public void creaPrestazione(String tipoPrestazione, Date data,Esito esito,String descrizione){
        this.tipoPrestazione=tipoPrestazione;
        this.data=data;
        this.esito=esito.positivo;
        this.descrizione=descrizione;
        this.ora_prestazione= LocalTime.now();

    }
    public void modificaPrestazione(String tipoPrestazione, Date data,Esito esito,String descrizione){
        this.tipoPrestazione=tipoPrestazione;
        this.data=data;
        this.esito=esito;
        this.descrizione=descrizione;
    }

    public String getAlbo(){
        return n_albo;
    }
    public void setAlbo(String n_albo){
        this.n_albo=n_albo;
    }




}


