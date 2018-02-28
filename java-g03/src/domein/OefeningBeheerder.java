package domein;

import javafx.collections.ObservableList;
import persistentie.OefeningMapper;

import java.io.File;
import java.util.ArrayList;

public class OefeningBeheerder {

	private Oefening oefening;
	private ObservableList<NumerischeOefening> ObservableOefeningen;
    private OefeningMapper oefeningMapper;

	public OefeningBeheerder() {
		oefeningMapper = new OefeningMapper();
	}

	/**
	 *
	 * @param naam
	 */
	public void verwijderOefening(String naam) {
		// TODO - implement OefeningBeheerder.verwijderOefening
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param oefeningNaam
	 * @param naam
	 * @param opgavePath
	 * @param antwoord
	 * @param feedback
	 * @param groepsbewerkingen
	 * @param vak
	 */
	public void wijzigOefening(String oefeningNaam, String naam, String opgavePath, String antwoord, String feedback, ArrayList<String> groepsbewerkingen, String vak) {
		// TODO - implement OefeningBeheerder.wijzigOefening
		throw new UnsupportedOperationException();
	}

	public ObservableList<Vak> geefVakken() {
		// TODO - implement OefeningBeheerder.geefVakken
		throw new UnsupportedOperationException();
	}

	public ObservableList<Groepsbewerking> geefGroepsbewerkingen() {
		// TODO - implement OefeningBeheerder.geefGroepsbewerkingen
		throw new UnsupportedOperationException();
	}

	public ObservableList<NumerischeOefening> geefOefeningen() {
		return ObservableOefeningen;
	}

	/**
	 *
	 * @param naam
	 * @param opgavePath
	 * @param antwoord
	 * @param feedback
	 * @param groepsbewerkingen
	 * @param vak
	 */
	public void createOefening(String naam, String opgavePath, double antwoord, String feedback, ArrayList<String> groepsbewerkingen, String vak) {
		// TODO - implement OefeningBeheerder.createOefening
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param naam
	 */
	public Oefening geefOefening(String naam) {
		// TODO - implement OefeningBeheerder.geefOefening
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param oefeningNaam
	 */
	public void filterOpNaam(String oefeningNaam) {
		// TODO - implement OefeningBeheerder.filterOpNaam
		throw new UnsupportedOperationException();


	}

	/**
	 *
	 * @param vakNaam
	 */
	public void filterOpVak(String vakNaam) {
		// TODO - implement OefeningBeheerder.filterOpVak
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param pathName
	 */
	public File geefOpgave(String pathName) {
		// TODO - implement OefeningBeheerder.geefOpgave
		throw new UnsupportedOperationException();
	}

}