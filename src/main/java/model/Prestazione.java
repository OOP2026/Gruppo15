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

    public void stampaInfo(){
        System.out.println("Tipo prestazione: "+ tipo_prestazione + "\nData: " + data +"Esito: "+ esito);
    }
}
