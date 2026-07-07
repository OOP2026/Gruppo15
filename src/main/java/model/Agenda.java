package model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Agenda{
    // vogliamo provare a scrivere un agenda in una classe, proviamo a fare un Arraylist del giorno con LocalTime
    private int id_agenda;
    private int id_medico;
    private List<SlotOrario> slots=new ArrayList<SlotOrario>();
    private Utente utente;


    public Agenda(){
    }
    public Agenda(int id_agenda, int id_medico){
        this.id_agenda = id_agenda;
        this.id_medico = id_medico;
    }

    public int getId_agenda(){
        return id_agenda;
    }
    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public int getId_medico() {
        return id_medico;
    }
    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }


    // metodo per ottenere tutti gli slots presenti in Agenda
    public List<SlotOrario> getSlots() {
        return slots;
    }
    public void setSlots(List<SlotOrario> orari){this.slots = orari;}

    public Utente getMedico() {
        return utente;
    }

    public void setMedico(Utente medico) {
        this.utente = medico;
    }
}
