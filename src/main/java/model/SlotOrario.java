package model;

import java.time.LocalTime;

public class SlotOrario {
    private int id_slot;
    private String giorno;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private Agenda agenda;

    //aggiunta agenda nel costruttore
    public SlotOrario(String giorno, LocalTime oraInizio, LocalTime oraFine, Agenda agenda){
        this.giorno=giorno;
        this.oraInizio=oraInizio;
        this.oraFine=oraFine;
        this.agenda = agenda;
    }
    public SlotOrario(String giorno, LocalTime oraInizio, LocalTime oraFine, Agenda agenda,int id_slot){
        this.giorno=giorno;
        this.oraInizio=oraInizio;
        this.oraFine=oraFine;
        this.agenda = agenda;
    }

    public int getId_slot(){return id_slot;}

    public void setId_slot(int id_slot) {
        this.id_slot = id_slot;
    }

    public String getGiorno() {
        return giorno;
    }
    public void setGiorno(String giorno) {
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

    public Agenda getAgenda(){return agenda;}

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    // metodo String per rendere i dati leggibili
    @Override
    public String toString(){
        return "Giorno " + giorno +
                "| Inizio: " + oraInizio +
                "| Fine: " + oraFine;
    }

} // Creata questa classe per poterla usare con l'agenda


