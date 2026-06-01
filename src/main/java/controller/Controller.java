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

	public SlotOrario medicoMostraAgenda() {

		return agenda.getSlot1();

	}

	public void medicoMostraAlbo() {
		medico.getAlbo();
	}

	
}
