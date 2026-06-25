package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Ricovero {
    private int id_ricovero;
    private String tessera_sanitaria;
    private int medico_id;
    private Timestamp dataInizio;
    private Timestamp dataFine; // Può essere null se il ricovero è attivo
    private int id_reparto;
    private int id_letto;

    public Ricovero(String tessera_sanitaria, int medico_id, int id_reparto, int id_letto) {
        this.tessera_sanitaria = tessera_sanitaria;
        this.medico_id = medico_id;
        this.id_reparto = id_reparto;
        this.id_letto = id_letto;

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
    public void setDataInizio(Timestamp dataInizio) {
        this.dataInizio = dataInizio;
    }
    public Timestamp getDataFine() {
        return dataFine;
    }
    public void setDataFine(Timestamp dataFine) {
        this.dataFine = dataFine;
    }
    public int getId_reparto() {
        return id_reparto;
    }
    public void setId_reparto(int id_reparto) {
        this.id_reparto = id_reparto;

    }
    public int getId_letto() {
        return id_letto;
    }
    public void setId_letto(int id_letto) {
        this.id_letto = id_letto;
    }






}
