package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerk;

import java.util.Collection;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public interface VormerkService {

	boolean vormerkenMoeglich(Kunde kunde, Medium medium);
	void vormerken(Kunde kunde, Medium medium);
	void vormerken(Kunde kunde, Collection<Medium> medien);
	boolean istVorgemerkt(Kunde kunde, Medium medium);
	boolean entferneVormerkung(Kunde kunde, Medium medium);
	boolean darfMediumEntleihen(Kunde kunde, Medium medium);
	
}
