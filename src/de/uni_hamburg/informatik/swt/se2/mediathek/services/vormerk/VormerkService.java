package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerk;

import java.util.Collection;
import java.util.List;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ObservableService;

public interface VormerkService extends ObservableService {

	/**
	 * Prüft ob der Kunde das Medium vormerken darf.
	 * 
	 * @param kunde Kunde, dessen Vormerkbarberechtigung bezüglich des Medium geprüft wird
	 * @param medium Medium was auf Vormerkbarkeit überprüft wird
	 * @return true, wenn der Kunde das Medium nicht vorgemerkt oder entliehen hat
	 * 
	 * @require kunde != null && medium != null
	 */
	boolean vormerkenMoeglich(Kunde kunde, Medium medium);

	/**
	 * Merkt das Medium für den Kunden vor.
	 *
	 * Schlägt still fehl, wenn der Kunde das Medium schon ausgeliehen oder vorgemerkt hat.
	 * 
	 * @param kunde Kunde, wofür das Medium vorgemerkt werden soll
	 * @param medium Medium, das für den Kunden vorgemerkt werden soll
	 * 
	 * @require kunde != null && medium != null && !vormerkenMoeglich(kunde, medium)
	 * @ensure istVorgemerkt(kunde, medium)
	 * 
	 */
	void vormerken(Kunde kunde, Medium medium);

	/**
	 * Merkt die Medien für den Kunden vor.
	 *
	 * 
	 * @param kunde Kunde, wofür das Medium vorgemerkt werden soll
	 * @param medium Medium, das für den Kunden vorgemerkt werden soll
	 * @return true, falls das Medium vorgemerkt werden konnte
	 * 
	 * @require kunde != null && medien != null &&  medien.forEach(medium -> vormerkenMoeglich(kunde, medium))
	 * @ensure medien.forEach(medium -> istVorgemerkt(kunde, medium))
	 * @see #vormerken(Kunde, Medium)
	 */
	void vormerken(Kunde kunde, Collection<Medium> medien);

	/**
	 * Prüft ob der Kunde das Medium vorgemerkt hat.
	 * 
	 * @param kunde Kunde, dessen Vormerkstatus für das Medium geprüft wird
	 * @param medium Medium, dessen Vormerkstatus für den Kunden geprüft wird
	 * @return true, falls das Medium von dem Kunden vorgemerkt wurde
	 * 
	 * @require kunde != null && medium != null
	 */
	boolean istVorgemerkt(Kunde kunde, Medium medium);

	/**
	 * 
	 * @param kunde Kunde dessen Vormerkung entfernt werden soll, falls vorhanden
	 * @param medium Medium dessen Vormerkung des Kunden entfernt werden soll
	 * @return true, wenn die Vormerkung entfernt wurde
	 * 
	 * @require kunde != null && medium != null
	 * @ensure !istVorgemerkt(kunde, medium)
	 */
	boolean entferneVormerkung(Kunde kunde, Medium medium);

	/**
	 * 
	 * @param kunde Kunde der auf sein Entleihrechte geprüft werden soll
	 * @param medium Medium, dessen Entleihbarkeit geprüft werden soll
	 * @return ob der Kunde das Medium ausleihen darf, falls es vorgemerkt ist
	 * 
	 * @require kunde != null && medium != null
	 */
	boolean darfMediumEntleihen(Kunde kunde, Medium medium);

	/**
	 * Gibt die Vormerker des Parameters zurück.
	 * 
	 * @param medium Medium, dessen Vormerker zurückgegeben werden soll
	 * @return eine Liste, mit kleiner gleich drei Elementen, der momentanen Vormerker
	 * 
	 * @require medium != null
	 * @ensure result != null && result.size() <=3 && !result.contains(null)
	 */
	List<Kunde> getVormerker(Medium medium);

	boolean darfMedienEntleihen(Kunde kunde, List<Medium> medien);
}
