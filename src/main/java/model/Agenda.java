package model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Agenda{
    // vogliamo provare a scrivere un agenda in una classe, proviamo a fare un Arraylist del giorno con LocalTime
    private int id_agenda;
    private Medico medico;
    private List<SlotOrario> slots=new ArrayList<SlotOrario>();

    public Agenda(){
    }

    public int getId_agenda(){return id_agenda;}
    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public Medico getMedico(){return medico;}
    public void setMedico(Medico medico) {
        this.medico = medico;
    }


    // metodo per ottenere tutti gli slots presenti in Agenda
    public List<SlotOrario> getSlots() {
        return slots;
    }
    public void setSlots(List<SlotOrario> orari){this.slots = orari;}

}
