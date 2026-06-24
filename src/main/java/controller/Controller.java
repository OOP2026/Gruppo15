package controller;
import dao.UtenteDAO;
import gui.Home;
import implementazioneDao.UtentePostgresDao;
import model.*;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

	Medico medico = new Medico();
	Agenda agenda = new Agenda();
	List<Ricovero> ricoveri = new ArrayList<Ricovero>();

	public Controller() {
		this.utenteDAO = new UtentePostgresDao(); // Colleghiamo Postgres tramite l'interfaccia
	}

	// metodo che richiama l'agenda per ottenere gli slots

	public List<SlotOrario> medicoMostraAgenda() {

		return agenda.getSlots();

	}

	public void salvaRicovero(String nome, String cognome, String tesseraSanitaria, String diagnosi) {

		Paziente pazienteNuovo = new Paziente(tesseraSanitaria, nome, cognome, diagnosi);
		LocalDateTime inizioRicovero = LocalDateTime.now();
		Ricovero ricovero = new Ricovero(inizioRicovero, null, pazienteNuovo, null);
		ricoveri.add(ricovero);

	}

	// testo da restituire
	String testoRicovero = "<html>";

	public String mostraRicovero() {
		for (Ricovero ricovero : ricoveri) {
			testoRicovero += ricovero.toString() + "<br>";

		}
		testoRicovero += "</html>";

		return testoRicovero;
	}

	// nuovi metodi dopo l'aggiunta del DB
	private UtenteDAO utenteDAO;
	private Home finestraLogin;

	public void eseguiLogin(String emailInserita, String passwordInserita) {

		// 1. Chiamata al database tramite il DAO
		Utente utenteLoggato = utenteDAO.login(emailInserita, passwordInserita);

		// 2. Controllo se le credenziali sono errate (oggetto null)
		if (utenteLoggato == null) {
			JOptionPane.showMessageDialog(finestraLogin,
					"Email o Password errate!",
					"Errore di Accesso",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 3. IL CAST E LO SMISTAMENTO DELLE SCHERMATE
		String ruolo = utenteLoggato.getRuolo();

		if ("MEDICO".equalsIgnoreCase(ruolo)) {

			// CAST: Trasformiamo l'Utente in Medico per sbloccare i campi specifici
			Medico medico = (Medico) utenteLoggato;

			// Apriamo la schermata del medico passandogli l'oggetto
			SchermataMedico viewMedico = new SchermataMedico(medico);
			viewMedico.setVisible(true);

			// Chiudiamo la finestra di login
			finestraLogin.dispose();

		} else if ("AMMINISTRATORE".equalsIgnoreCase(ruolo)) {

			// CAST: Trasformiamo l'Utente in Amministratore
			Amministratore admin = (Amministratore) utenteLoggato;

			// Apriamo la schermata dell'amministratore passandogli l'oggetto
			SchermataAdmin viewAdmin = new SchermataAdmin(admin);
			viewAdmin.setVisible(true);

			// Chiudiamo la finestra di login
			finestraLogin.dispose();
		}
	}


	}
}

