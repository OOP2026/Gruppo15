package model;

import java.time.LocalTime;
import java.util.Date;

public class Medico {
    private String n_albo;
    private String reparto;
    private String tipoPrestazione;
    private Date data;
    private Esito esito;
    private String descrizione;
    private LocalTime ora_prestazione;

    public void creaPrestazione(String tipoPrestazione, Date data,Esito esito,String descrizione){
        this.tipoPrestazione=tipoPrestazione;
        this.data=data;
        this.esito=esito;
        this.descrizione=descrizione;
        this.ora_prestazione= LocalTime.now();

    }
    public void modificaPrestazione(String tipoPrestazione, Date data,Esito esito,String descrizione){
        this.tipoPrestazione=tipoPrestazione;
        this.data=data;
        this.esito=esito;
        this.descrizione=descrizione;
    }




}


