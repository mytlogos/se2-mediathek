package de.uni_hamburg.informatik.swt.se2.mediathek.startup;

import java.io.File;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.persistenz.DateiLeseException;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.persistenz.DatenEinleser;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih.VerleihServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.hauptwerkzeug.MediathekWerkzeug;

/**
 * Startet die Hauptanwendung mit grafischer Oberfläche.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class Main
{

    private static final File KUNDEN_DATEI = new File(
            "./bestand/kundenstamm.txt");
    private static final File MEDIEN_DATEI = new File(
            "./bestand/medienbestand.txt");

    public static final KundenstammService KUNDENSTAMM;
    public static final MedienbestandService MEDIENBESTAND;
    public static final VerleihService VERLEIH_SERVICE;
    
    static  {
    	DatenEinleser datenEinleser = new DatenEinleser(MEDIEN_DATEI,
                    KUNDEN_DATEI);
            try {
				datenEinleser.leseDaten();
			} catch (DateiLeseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            MEDIENBESTAND = new MedienbestandServiceImpl(
                    datenEinleser.getMedien());
            KUNDENSTAMM = new KundenstammServiceImpl(
                    datenEinleser.getKunden());
            VERLEIH_SERVICE = new VerleihServiceImpl(datenEinleser.getVerleihkarten());
        
    }

    /**
     * Main-Methode, mit der die Anwendung gestartet wird.
     */
    public static void main(String[] args)
    {
        erstelleServices();

        final MediathekWerkzeug mediathekWerkzeug = new MediathekWerkzeug(
        		MEDIENBESTAND, KUNDENSTAMM, VERLEIH_SERVICE);

        // Dies ist die korrekte Art eine Swing-Anwendnung zu starten.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                mediathekWerkzeug.zeigeFenster();
            }
        });

    }

    /**
     * Erstellt die Services und lädt die Daten.
     */
    private static void erstelleServices()
    {
        
    }

}
