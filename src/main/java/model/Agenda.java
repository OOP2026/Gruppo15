package model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Agenda{
    // vogliamo provare a scrivere un agenda in una classe, proviamo a fare un Arraylist del giorno con LocalTime
    private List<SlotOrario> slots=new ArrayList<SlotOrario>();

    public Agenda(){

        SlotOrario slot1=new SlotOrario(DayOfWeek.MONDAY,LocalTime.now(),LocalTime.now());
        slots.add(slot1);
        SlotOrario slot2=new SlotOrario(DayOfWeek.FRIDAY,LocalTime.now(),LocalTime.now());
        slots.add(slot2);
    }

    // metodo per ottenere tutti gli slots presenti in Agenda
    public List<SlotOrario> getSlots() {
        return slots;
    }









}
