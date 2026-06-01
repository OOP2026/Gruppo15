package controller;
import model.*;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public class Controller {

	Medico medico=new Medico();
	Agenda agenda=new Agenda();
	public Controller() {
	}

	// metodo che richiama l'agenda per ottenere gli slots

	public List<SlotOrario> medicoMostraAgenda() {

		return agenda.getSlots();

	}

	public void medicoMostraAlbo() {
		medico.getAlbo();
	}

	
}
