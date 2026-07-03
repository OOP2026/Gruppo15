package model;

import java.sql.Timestamp;
import java.util.Date;

public class Ricovero {
    private int id_ricovero;
    private String tessera_sanitaria;
    private int medico_id;
    private Timestamp dataInizio;
    private Timestamp dataFine; // Può essere null se il ricovero è attivo
    private int reparto;
    private String diagnosi;
    private int id_letto;

    public Ricovero() {

    }

    public Ricovero(String tessera_sanitaria, int medico_id, String diagnosi, int reparto, int id_letto,Timestamp dataInizio,Timestamp dataFine,int id_ricovero) {
        this.tessera_sanitaria = tessera_sanitaria;
        this.medico_id = medico_id;
        this.diagnosi = diagnosi;
        this.reparto = reparto;
        this.id_letto = id_letto;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.id_ricovero = id_ricovero;

    }
    //

    public Ricovero(String tessera_sanitaria, int medico_id, String diagnosi, int reparto, int id_letto,Timestamp dataInizio,Timestamp dataFine) {
        this.tessera_sanitaria = tessera_sanitaria;
        this.medico_id = medico_id;
        this.diagnosi = diagnosi;
        this.reparto = reparto;
        this.id_letto = id_letto;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;

    }

    public int getId_ricovero() {
        return id_ricovero;
    }
    public void setId_ricovero(int id_ricovero) {
        this.id_ricovero = id_ricovero;

    }
    public String getTessera_sanitaria() {
        return tessera_sanitaria;

    }
    public void setTessera_sanitaria(String tessera_sanitaria) {
        this.tessera_sanitaria = tessera_sanitaria;
    }
    public int getMedico_id() {
        return medico_id;
    }
    public void setMedico_id(int medico_id) {
        this.medico_id = medico_id;
    }
    public Timestamp getDataInizio() {
        return dataInizio;

    }

    //cambiato get e set reparto in String siccome è VARCHAR in SQL
    public void setDataInizio(Timestamp dataInizio) {
        this.dataInizio = dataInizio;
    }
    public Date getDataFine() {
        return dataFine;
    }
    public void setDataFine(Timestamp dataFine) {
        this.dataFine = dataFine;
    }
    public int getReparto() {
        return reparto;
    }
    public void setReparto(int reparto) {
        this.reparto = reparto;

    }

    //aggiunti set e get diagnosi

    public void setDiagnosi(String diagnosi) {
        this.diagnosi = diagnosi;
    }

    public String getDiagnosi(){ return diagnosi;}

    //ho momentaneamente commentato il codice di set e get id siccome dava problemi essendo inutilizzabile

     public int getId_letto() {
        return id_letto;
    }
     public void setId_letto(int id_letto) {
        this.id_letto = id_letto;
    }






}
