package controller;
import model.*;

import java.util.Date;

public class Controller {

	Medico medico=new Medico();
	public Controller() {
	}

	public void medicoMostraAgenda() {

		medico.getAgenda();

	}

	public void medicoMostraAlbo() {
		medico.getAlbo();
	}

	
}
