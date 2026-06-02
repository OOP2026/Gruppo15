package controller;
import model.*;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

	Medico medico=new Medico();
	Agenda agenda=new Agenda();
	List<Ricovero> ricoveri=new ArrayList<Ricovero>();
	public Controller() {
	}

	// metodo che richiama l'agenda per ottenere gli slots

	public List<SlotOrario> medicoMostraAgenda() {

		return agenda.getSlots();

	}

	public void medicoMostraAlbo() {
		medico.getAlbo();
	}

	public void salvaRicovero(String nome, String cognome, String tesseraSanitaria,String  diagnosi) {

		Paziente pazienteNuovo=new Paziente(tesseraSanitaria,nome,cognome,diagnosi);
		LocalDateTime inizioRicovero=LocalDateTime.now();
		Ricovero ricovero=new Ricovero(inizioRicovero,null,pazienteNuovo,null);
		ricoveri.add(ricovero);




	}

	public void mostraRicovero(String Tessera) {
		ricoveri.toString();
	}





	
}
