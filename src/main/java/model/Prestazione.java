package model;

import java.util.Date;

public class Prestazione {
    private String tipo_prestazione;
    private Date data;
    private String esito;

    public Prestazione(String tipo_prestazione, Date data,String esito ){
        this.tipo_prestazione=tipo_prestazione;
        this.data=data;
        this.esito=esito;
    }

    public void setTipo_prestazione(String tipo_prestazione){
        this.tipo_prestazione=tipo_prestazione;
    }
    public String getTipo_prestazione(){
        return tipo_prestazione;

    }
    public void setData(Date data){
        this.data=data;

    }
    public Date getData(){
        return data;
    }
    public void setEsito(String esito){
        this.esito=esito;
    }
    public String getEsito(){
        return esito;
    }


}
