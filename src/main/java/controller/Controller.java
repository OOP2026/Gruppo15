package controller;
import dao.AgendaDAO;
import dao.UtenteDAO;
import dao.RicoveroDAO;
import gui.AmministratoreGUI;
import gui.Home;
import gui.MedicoGUI;
import implementazioneDao.AgendaPostgresDao;
import implementazioneDao.RicoveroPostgresDao;
import implementazioneDao.UtentePostgresDao;
import model.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

	Medico medico = new Medico();
	Agenda agenda = new Agenda();
	List<Ricovero> ricoveri = new ArrayList<Ricovero>();
	// testo da restituire
	String testoRicovero = "<html>";



	// nuovi metodi dopo l'aggiunta del DB
	private UtenteDAO utenteDAO;
	private Home finestraLogin;
	private RicoveroDAO ricoveroDAO;
	private AgendaDAO agendaDAO;
	private Medico medicoLoggato;

	//assegno home al controller siccome prima non aveva mai un valore
	public Controller(Home home){
		this.finestraLogin = home;
		// assegnamo tutti i valori dei DAO, aggiunto ricoveroDAO
		this.utenteDAO = new UtentePostgresDao();
		this.ricoveroDAO = new RicoveroPostgresDao();
		this.agendaDAO = new AgendaPostgresDao();
	}

	//metodo per eseguire la login, in base al ruolo apre una schermata differente

	public void eseguiLogin(String emailInserita, String passwordInserita) throws SQLException {

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

			//salvo il medico loggato per poterlo riutilizzare negli altri metodi
			this.medicoLoggato = medico;

			// Apriamo la schermata del medico passandogli l'oggetto
			MedicoGUI viewMedico = new MedicoGUI(this, finestraLogin, medico);
			viewMedico.setVisible(true);

			// Chiudiamo la finestra di login
			finestraLogin.dispose();

		} else if ("AMMINISTRATORE".equalsIgnoreCase(ruolo)) {

			// CAST: Trasformiamo l'Utente in Amministratore
			Amministratore admin = (Amministratore) utenteLoggato;

			// Apriamo la schermata dell'amministratore passandogli l'oggetto admin
			AmministratoreGUI viewAdmin = new AmministratoreGUI(this, finestraLogin, admin);
			viewAdmin.setVisible(true);

			// Chiudiamo la finestra di login
			finestraLogin.dispose();
		}
	}

	//metodo che salva il ricovero e restituisce true/false

	public boolean salvaRicovero(Ricovero ricovero,Paziente paziente) throws  SQLException{
		// chiamata al db tramite Dao
		return ricoveroDAO.salvaRicovero(ricovero,paziente);
	}

	public List<Ricovero> mostraRicoveri(String tesseraSanitaria) throws SQLException{
		return ricoveroDAO.getRicoveriPerPaziente(tesseraSanitaria);
	}

	//metodo per mostrare l'agenda
	public Agenda mostraAgenda(int idMedico)throws SQLException{
		return agendaDAO.trovaDaMedico(medicoLoggato.getId());
	}




	}


