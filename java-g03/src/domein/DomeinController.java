package domein;

import javafx.collections.ObservableList;

public class DomeinController {

	private OefeningRepository oefeningRepository;
	private GroepsbewerkingRepository groepsbewerkingRepository;
	private VakRepository vakRepository;

	public DomeinController() {
		// TODO - implement DomeinController.DomeinController
		throw new UnsupportedOperationException();
	}

	public ObservableList<Oefening> GeefOefeningen() {
		// TODO - implement DomeinController.GeefOefeningen
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param oefening
	 */
	public void VerwijderOefening(Oefening oefening) {
		// TODO - implement DomeinController.VerwijderOefening
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param oefeningNaam
	 * @param naam
	 * @param feedback
	 * @param antwoord
	 * @param opgave
	 */
	public void WijzigOefening(String oefeningNaam, String naam, String feedback, String antwoord, String opgave) {
		// TODO - implement DomeinController.WijzigOefening
		throw new UnsupportedOperationException();
	}

	public ObservableList<Groepsbewerking> geefGroepsbewerkingen() {
		// TODO - implement DomeinController.geefGroepsbewerkingen
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param naam
	 * @param feedback
	 * @param antwoord
	 * @param opgave
	 */
	public void voegOefeningToe(String naam, String feedback, String antwoord, String opgave) {
		// TODO - implement DomeinController.voegOefeningToe
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param naam
	 */
	public Oefening getOefening(String naam) {
		// TODO - implement DomeinController.getOefening
		throw new UnsupportedOperationException();
	}

	public ObservableList<Vak> getVakken() {
		// TODO - implement DomeinController.getVakken
		throw new UnsupportedOperationException();
	}

}