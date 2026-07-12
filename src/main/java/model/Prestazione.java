package model;

import java.sql.Timestamp;


public class Prestazione {
    private int id;
    private TipoPrestazione tipo_prestazione;
    private Timestamp data;
    private Esito esito;
    private String descrizione;
    private String tesseraPaziente;
    private int medicoAlbo;
    public  Prestazione() {

    }
    public Prestazione(TipoPrestazione tipoPrestazione, Timestamp data,Esito esito,String descrizione,String tesseraPaziente,int medicoAlbo) {
        this.tipo_prestazione=tipoPrestazione;
        this.data=data;
        this.esito=esito;
        this.descrizione=descrizione;
        this.tesseraPaziente=tesseraPaziente;
        this.medicoAlbo=medicoAlbo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTipo_prestazione(TipoPrestazione tipo_prestazione){
        this.tipo_prestazione=tipo_prestazione;
    }
    public TipoPrestazione getTipo_prestazione(){
        return tipo_prestazione;

    }
    public void setData(Timestamp data){
        this.data=data;

    }

    public Timestamp getData(){
        return data;
    }

    public void setEsito(Esito esito){
        this.esito=esito;
    }

    public Esito getEsito(){
        return esito;
    }

    public void setDescrizione(String descrizione){
        this.descrizione=descrizione;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public void setTesseraPaziente(String tesseraPaziente){
        this.tesseraPaziente=tesseraPaziente;
    }

    public String getTesseraPaziente(){
        return tesseraPaziente;
    }

    public void setMedicoAlbo(int medicoAlbo){
        this.medicoAlbo=medicoAlbo;
    }

    public int getMedicoAlbo(){
        return medicoAlbo;
    }



}
