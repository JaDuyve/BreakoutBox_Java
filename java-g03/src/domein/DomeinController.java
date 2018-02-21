package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class DomeinController {

	private OefeningRepository oefeningRepository;
	private GroepsbewerkingRepository groepsbewerkingRepository;
	private VakRepository vakRepository;

	public DomeinController() {
		oefeningRepository = new OefeningRepository();
		groepsbewerkingRepository = new GroepsbewerkingRepository();
		vakRepository = new VakRepository();
	}

	public ObservableMap GeefOefeningen() {
		return FXCollections.unmodifiableObservableMap(FXCollections.observableMap(oefeningRepository.geefOefeningen()));
	}

	/**
	 *
	 * @param oefening
	 */
	public void VerwijderOefening(Oefening oefening) {
		oefeningRepository.verwijderOefening(oefening);
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
		oefeningRepository.wijzigOefening(oefeningNaam, naam, feedback, antwoord, opgave);
	}

	public ObservableMap geefGroepsbewerkingen() {
        return FXCollections.unmodifiableObservableMap(FXCollections.observableMap(groepsbewerkingRepository.geefGroepsBewerkingen()));
	}

	/**
	 *
	 * @param naam
	 * @param feedback
	 * @param antwoord
	 * @param opgave
	 */
	public void voegOefeningToe(String naam, String feedback, String antwoord, String opgave) {
		oefeningRepository.voegOefeningToe(naam, feedback, antwoord, opgave);
	}

	/**
	 *
	 * @param naam
	 */
	public Oefening geefOefening(String naam) {
		return oefeningRepository.geefOefening(naam);
	}

	public ObservableMap getVakken() {
        return FXCollections.unmodifiableObservableMap(FXCollections.observableMap(vakRepository.geefVakken()));
	}

}