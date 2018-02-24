package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.Comparator;
import java.util.Observable;

public class DomeinController extends Observable {

	private OefeningRepository oefeningRepository;
	private GroepsbewerkingRepository groepsbewerkingRepository;
	private VakRepository vakRepository;

	private Oefening currentOefening;
	private ObservableList<Oefening> oefeningenLijst;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private FilteredList<Oefening> filterOefeningenLijst;
	private SortedList<Oefening> sorteerOefeningenLijst;

	public DomeinController() {
		oefeningRepository = new OefeningRepository();
		groepsbewerkingRepository = new GroepsbewerkingRepository();
		vakRepository = new VakRepository();

		oefeningenLijst = FXCollections.observableList(oefeningRepository.geefOefeningen());
		filterOefeningenLijst = new FilteredList<>(oefeningenLijst, p -> true);
		sorteerOefeningenLijst = new SortedList<>(filterOefeningenLijst, (x,y) -> x.getNaam().compareToIgnoreCase(y.getNaam()));
	}

	public ObservableList<Oefening> GeefOefeningen() {
		return sorteerOefeningenLijst;
	}
	public void setCurrentAuto(Oefening oefening){
		this.currentOefening = oefening;
		setChanged();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		notifyObservers(oefening);
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