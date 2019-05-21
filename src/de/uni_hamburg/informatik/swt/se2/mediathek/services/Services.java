package de.uni_hamburg.informatik.swt.se2.mediathek.services;

import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.persistenz.DateiLeseException;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.persistenz.DatenEinleser;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerk.VormerkService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerk.VormerkServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.startup.Main;

public final class Services {

	
	public static final KundenstammService KUNDENSTAMM;
    public static final MedienbestandService MEDIENBESTAND;
    public static final VerleihService VERLEIH_SERVICE;
    public static final VormerkService VORMERK_SERVICE;
    
    static  {
    	DatenEinleser datenEinleser = new DatenEinleser(Main.MEDIEN_DATEI,
                    Main.KUNDEN_DATEI);
        try {
			datenEinleser.leseDaten();
		} catch (DateiLeseException e) {
			e.printStackTrace();
		}
        MEDIENBESTAND = new MedienbestandServiceImpl(
                datenEinleser.getMedien());
        KUNDENSTAMM = new KundenstammServiceImpl(
                datenEinleser.getKunden());
        VERLEIH_SERVICE = new VerleihServiceImpl(datenEinleser.getVerleihkarten());
        VORMERK_SERVICE = new VormerkServiceImpl();
    }
	
	
	private Services()
	{
	
	}
	
}
