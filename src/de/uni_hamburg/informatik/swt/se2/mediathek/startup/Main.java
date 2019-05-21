package de.uni_hamburg.informatik.swt.se2.mediathek.startup;

import java.io.File;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.swt.se2.mediathek.services.Services;
import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.hauptwerkzeug.MediathekWerkzeug;

/**
 * Startet die Hauptanwendung mit grafischer Oberfläche.
 * 
 * @author SE2-Team
 * @version SoSe 2019
 */
public class Main
{

    public static final File KUNDEN_DATEI = new File(
            "./bestand/kundenstamm.txt");
    public static final File MEDIEN_DATEI = new File(
            "./bestand/medienbestand.txt");

    

    /**
     * Main-Methode, mit der die Anwendung gestartet wird.
     */
    public static void main(String[] args)
    {
        final MediathekWerkzeug mediathekWerkzeug = new MediathekWerkzeug(
        		Services.MEDIENBESTAND, Services.KUNDENSTAMM, Services.VERLEIH_SERVICE);

        // Dies ist die korrekte Art eine Swing-Anwendnung zu starten.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                mediathekWerkzeug.zeigeFenster();
            }
        });

    }
}
