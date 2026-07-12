package model;

public class Medico extends Utente {
    public String n_albo;
    private String reparto;
    private Agenda agenda;

    public Medico(){

        super.setRuolo("MEDICO");
    }
    public void setN_albo(String n_albo){
        this.n_albo=n_albo;
    }

    public String getN_albo(){
        return this.n_albo;
    }
    public void setReparto(String reparto){
        this.reparto=reparto;

    }
    public String getReparto(){
        return this.reparto;
    }

    public String getAgenda(){
        return agenda.toString();
    }
    public void setAgenda(Agenda agenda){
        this.agenda = agenda;
    }}


