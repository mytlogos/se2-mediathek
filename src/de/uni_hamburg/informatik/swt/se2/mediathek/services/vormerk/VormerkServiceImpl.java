package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerk;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.startup.Main;

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
		if(vormerker == null) {
			vormerker = new LinkedList<>();
			_vormerkungen.put(medium, vormerker);
		}
		vormerker.add(kunde);
		
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
		// TODO Auto-generated method stub
		return false;
	}

}
