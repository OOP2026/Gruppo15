package controller;
import dao.*;
import gui.AmministratoreGUI;
import gui.Home;
import gui.MedicoGUI;
import implementazioneDao.*;
import model.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
	private PrestazioneDAO prestazioneDAO;
	private SlotOrarioDAO slotOrarioDAO;
	private RepartoDAO repartoDAO;
	private PazienteDAO pazienteDAO;
	private StanzaDAO stanzaDAO;
	private MedicoDAO medicoDAO;
	private LettoDAO lettoDAO;

	//assegno home al controller siccome prima non aveva mai un valore
	public Controller(Home home){
		this.finestraLogin = home;
		// assegnamo tutti i valori dei DAO, aggiunto ricoveroDAO
		this.utenteDAO = new UtentePostgresDao();
		this.ricoveroDAO = new RicoveroPostgresDao();
		this.agendaDAO = new AgendaPostgresDao();
		this.prestazioneDAO = new PrestazionePostgresDao();
		this.slotOrarioDAO = new SlotOrarioPostgresDao();
		this.repartoDAO = new RepartoPostgresDao();
		this.pazienteDAO = new PazientePostgresDao();
		this.stanzaDAO = new StanzaPostgresDao();
		this.medicoDAO = new MedicoPostgresDao();
		this.agendaDAO = new AgendaPostgresDao();
		this.lettoDAO = new LettoPostgresDao();
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
		} else if (utenteLoggato.getAttivo() == false){
			JOptionPane.showMessageDialog(finestraLogin,
					"Utente disattivato!",
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

	public boolean salvaRicovero(Ricovero ricovero) throws  SQLException{
		// chiamata al db tramite Dao
		return ricoveroDAO.salvaRicovero(ricovero);
	}
	public boolean modificaRicovero(Ricovero ricovero,boolean fineRicovero) throws SQLException{
		return ricoveroDAO.aggiornaRicovero(ricovero,fineRicovero);
	}



	public List<Ricovero> mostraRicoveri(String tesseraSanitaria) throws SQLException{
		return ricoveroDAO.getRicoveriPerPaziente(tesseraSanitaria);
	}
	public List<Ricovero> mostraRicoveriDataDimissione(Date dataDimissioniPrevista) throws SQLException{
		return ricoveroDAO.getRicoveriPerDataDimissione(dataDimissioniPrevista);
	}

	//metodo per mostrare l'agenda
	public Agenda mostraAgenda(int idMedico)throws SQLException{
		return agendaDAO.trovaDaMedico(medicoLoggato.getId());
	}

	public boolean salvaPrestazione(Prestazione prestazione) throws SQLException{
		return prestazioneDAO.salvaPrestazione(prestazione);
	}
	public boolean modificaPrestazione(Prestazione prestazione) throws SQLException{
		return prestazioneDAO.aggiornaPrestazione(prestazione);
	}

	//metodo per salvare gli slot orari
	public boolean salvaSlotOrario(SlotOrario slotOrario) throws SQLException{
		return slotOrarioDAO.creaSlot(slotOrario);
	}
	public boolean modificaSlotOrario(SlotOrario slotOrario) throws SQLException{
		return slotOrarioDAO.aggiornaSlot(slotOrario);
	}

	public List<Prestazione> mostraTutteLePrestazioni() throws SQLException {
		return prestazioneDAO.getTutteLePrestazioni();
	}

	public List<Reparto> mostraReparti()throws SQLException{
		return repartoDAO.getReparti();
	}
	public boolean aggiungiPaziente(Paziente paziente) throws SQLException{
		return pazienteDAO.aggiungiPaziente(paziente);
	}
	public boolean modificaPaziente(Paziente paziente) throws SQLException{
		return pazienteDAO.modificaPaziente(paziente);
	}
	public List<Paziente> mostraPazienti() throws SQLException{
		System.out.println("Test");
		return pazienteDAO.listaPazienti();
	}

	public List<Stanza> mostraStanze(int idReparto) throws SQLException{
		return stanzaDAO.getStanzeByReparto(idReparto);
	}

	public String getNomeReparto(int idReparto) throws SQLException{
		return repartoDAO.getNomeReparto(idReparto);
	}

	public boolean aggiungiMedico(Medico medico) throws SQLException{
		return medicoDAO.aggiungiMedico(medico);
	}
	public boolean assegnaAgenda(int id_agenda, int id_medico) throws SQLException{
		return agendaDAO.assegnaAgenda(id_agenda, id_medico);
	}

	public List<SlotOrario>  mostraSlotOrari() throws SQLException{
		return slotOrarioDAO.mostraSlotOrari();
	}
	public List<Agenda> mostraTutteLeAgende() throws SQLException{
		return agendaDAO.listaAgenda();

	}
	public boolean eliminaPaziente(String idPaziente) throws SQLException{
		return pazienteDAO.eliminaPaziente(idPaziente);
	}

	public List<Letto> visualizzaLettiDisponibili (int idReparto) throws SQLException{
		return lettoDAO.getLettiDisponibiliByReparto(idReparto);
	}


	}


