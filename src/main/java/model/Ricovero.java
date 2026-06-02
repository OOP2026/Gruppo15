package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Ricovero {
    private LocalDateTime inizio_ricovero;
    private LocalDateTime fine_ricovero;
    private Paziente paziente;
    private Letto letto;

    public Ricovero() {

    }

    public Ricovero(LocalDateTime inizio_ricovero, LocalDateTime fine_ricovero, Paziente paziente, Letto letto) {
        this.inizio_ricovero = inizio_ricovero;
        this.fine_ricovero = fine_ricovero;
        this.paziente=paziente;
        this.letto=letto;
    }
// modificato ricovero in modo che possa ottenere tutte le info di paziente e letto
// la parte complessa risulta che ora per creare i metodi per ottenere un ricovero a partire da un paziente o una data
// bisognerebbe avere una lista di ricoveri, non presente qui
// quindi è necessario un sistema che li gestista, che non è l'amministratore, poichè egli esegue e basta
// analizzare il problema e capire se creare la classe GestoreRicoveri

    public LocalDateTime getFineRicoveroPaziente(){
        return fine_ricovero;
    }

    @Override
    public String toString(){
        return "Paziente: " + paziente.getNome() +
                "\nOrario inizio: " + inizio_ricovero.getHour() + inizio_ricovero.getMinute() +
                "\nOrario fine: " + fine_ricovero +
                "\nLetto occupato: " + letto;
    }



}
