package model;

import java.time.LocalTime;

public class Ricovero {
    private LocalTime inizio_ricovero;
    private LocalTime fine_ricovero;
    private Paziente paziente;
    private String id_letto;

    public Ricovero(LocalTime inizio_ricovero, LocalTime fine_ricovero, String tessera, String id_letto) {
        this.inizio_ricovero = inizio_ricovero;
        this.fine_ricovero = fine_ricovero;
        this.paziente.setTessera(tessera);
        this.id_letto=id_letto;
    }
}
