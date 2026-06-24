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

    public void modificaSlot(DayOfWeek giorno, LocalTime oraInizio,LocalTime oraFine){
        this.giorno=giorno;
        this.oraInizio=oraInizio;
        this.oraFine=oraFine;
    }
    public DayOfWeek getGiorno() {
        return giorno;
    }
    public void setGiorno(DayOfWeek giorno) {
        this.giorno = giorno;
    }
    public LocalTime getOraInizio() {
        return oraInizio;
    }
    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }
    public LocalTime getOraFine() {
        return oraFine;
    }
    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }


    // metodo String per rendere i dati leggibili
    @Override
    public String toString(){
        return "Giorno " + giorno +
                "| Inizio: " + oraInizio +
                "| Fine: " + oraFine;
    }

} // Creata questa classe per poterla usare con l'agenda


