package model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class Agenda{
    // vogliamo provare a scrivere un agenda in una classe, proviamo a fare un Arraylist del giorno con LocalTime
    private List<SlotOrario> slots;
    DayOfWeek day=DayOfWeek.MONDAY;
    LocalTime oraInizio=LocalTime.of(12,0,0);
    LocalTime oraFine=LocalTime.of(18,0,0);
    String n_albo="10203";
    public Agenda(DayOfWeek day,LocalTime oraInizio,LocalTime oraFine){
        this.day=day;
        this.oraInizio=oraInizio;
        this.oraFine=oraFine;
    }




}
