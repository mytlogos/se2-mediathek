package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerk;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.startup.Main;

/**
 * Implementation von VormerkService. *
 */
public class VormerkServiceImpl implements VormerkService {

	private final Map<Medium, Queue<Kunde>> _vormerkungen;
	
	public VormerkServiceImpl() {
		_vormerkungen = new HashMap<>();
	}
	
	@Override
	public boolean vormerkenMoeglich(Kunde kunde, Medium medium) {
		assert kunde != null;
		assert medium != null;
		
		Queue<Kunde> vormerker = _vormerkungen.get(medium);
		
		return (vormerker == null || vormerker.size() < 3 ) 
				&& !Main.VERLEIH_SERVICE.istVerliehenAn(kunde, medium) 
				&& !istVorgemerkt(kunde, medium);
	}

	@Override
	public void vormerken(Kunde kunde, Medium medium) {
		assert vormerkenMoeglich(kunde, medium);
		
		Queue<Kunde> vormerker = _vormerkungen.get(medium);
		if(vormerker == null) 
		{
			vormerker = new LinkedList<>();
			_vormerkungen.put(medium, vormerker);
		}
		// FIXME: (DominikB) Ich finde es wäre besser eine Exception zu werfen oder boolean zurückzugeben, als stumm zu versagen
		if (!vormerker.contains(kunde))
		{
			vormerker.add(kunde);	
		}
	}

	@Override
	public void vormerken(Kunde kunde, Collection<Medium> medien) {
		assert medien != null;
		
		for(Medium medium : medien) {
			vormerken(kunde, medium);
		}
	}

	@Override
	public boolean istVorgemerkt(Kunde kunde, Medium medium) {
		Queue<Kunde> vormerker = _vormerkungen.get(medium);
		return vormerker != null && vormerker.contains(kunde);
	}

	@Override
	public boolean entferneVormerkung(Kunde kunde, Medium medium) {
		Queue<Kunde> vormerker = _vormerkungen.get(medium);
		if(vormerker == null) {
			return false;
		}
		return vormerker.remove(kunde);
	}

	@Override
	public boolean darfMediumEntleihen(Kunde kunde, Medium medium) {
		assert kunde != null : "Vorbedingung verletzt: kunde != null";
		assert medium != null : "Vorbedingung verletzt: medium != null";
		Queue<Kunde> kundenSchlange = _vormerkungen.get(medium);
		
		return kundenSchlange == null || kunde.equals(kundenSchlange.peek());
	}

	@Override
	public List<Kunde> getVormerker(Medium medium) {
		assert medium != null : "Vorbedingung verletzt: medium != null";
		
		Queue<Kunde> vormerkungen =_vormerkungen.get(medium);
		
		if (vormerkungen == null || vormerkungen.isEmpty()) {
			return new LinkedList<>();
		}
		
		List<Kunde> vormerker = new LinkedList<>();
		
		for (Kunde kunde : vormerkungen) {
			vormerker.add(kunde);
		}
		// FIXME: das testen auf größe kann unnötig sein, wenn wir das als vorbedingung nehmen wollen
		if (vormerker.size() > 3)
		{
			vormerker = vormerker.subList(0, 3);
		}
		return vormerker;
	}

}
