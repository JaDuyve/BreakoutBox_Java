package domein;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.*;

public class DomeinController extends Observable implements ObservableList<Oefening> {

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

	public ObservableList<Oefening> geefOefeningen() {
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

	@Override
	public void addListener(ListChangeListener<? super Oefening> listener) {

	}

	@Override
	public void removeListener(ListChangeListener<? super Oefening> listener) {

	}

	@Override
	public boolean addAll(Oefening... elements) {
		return false;
	}

	@Override
	public boolean setAll(Oefening... elements) {
		return false;
	}

	@Override
	public boolean setAll(Collection<? extends Oefening> col) {
		return false;
	}

	@Override
	public boolean removeAll(Oefening... elements) {
		return false;
	}

	@Override
	public boolean retainAll(Oefening... elements) {
		return false;
	}

	@Override
	public void remove(int from, int to) {

	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<Oefening> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(Oefening oefening) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Oefening> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends Oefening> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {

	}

	@Override
	public Oefening get(int index) {
		return null;
	}

	@Override
	public Oefening set(int index, Oefening element) {
		return null;
	}

	@Override
	public void add(int index, Oefening element) {

	}

	@Override
	public Oefening remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<Oefening> listIterator() {
		return null;
	}

	@Override
	public ListIterator<Oefening> listIterator(int index) {
		return null;
	}

	@Override
	public List<Oefening> subList(int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public void addListener(InvalidationListener listener) {

	}

	@Override
	public void removeListener(InvalidationListener listener) {

	}
}