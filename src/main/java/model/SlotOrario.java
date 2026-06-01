package model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class SlotOrario {
    private DayOfWeek giorno;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private Medico medico;

    public SlotOrario(DayOfWeek giorno, LocalTime oraInizio,LocalTime oraFine){
        this.giorno=giorno;
        this.oraInizio=oraInizio;
        this.oraFine=oraFine;
    } // costruttore slot

    public void stampaSlot(){
        System.out.println("Giorno: "+ giorno + "\nOra inizio: "+ oraInizio + "\nOra fine turno: "+oraFine);
    } // metodo che stampa l'orario di lavoro insieme al giorno

    public void modificaSlot(DayOfWeek giorno, LocalTime oraInizio,LocalTime oraFine){
        this.giorno=giorno;
        this.oraInizio=oraInizio;
        this.oraFine=oraFine;
    }

} // Creata questa classe per poterla usare con l'agenda


