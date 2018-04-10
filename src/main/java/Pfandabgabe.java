import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Pfandabgabe {

    private int freieAutomaten;

    private static Logger logger = LoggerFactory.getLogger(Pfandabgabe.class);

    public Pfandabgabe(int automatenAnzahl) {
        this.freieAutomaten = automatenAnzahl;
    }

    public synchronized void automatBenutzen(Kunde kunde) throws InterruptedException {
        logger.info("Freie Automaten: " + freieAutomaten);
        while (freieAutomaten == 0) {
            logger.info("Gerade sind keine Automaten frei.");
            try {
                wait();
            } catch (InterruptedException ie) {
            }
        }
        freieAutomaten--;
        logger.info("Es ist ein Automat frei.");
        logger.info(kunde + " hat " + kunde.getKorbAnzahl() + " Körbe dabei und gibt diese ab.");
        kunde.setPfandabgabe(this);
        Thread pfandAbgabeThread = new Thread(kunde);
        pfandAbgabeThread.start();
    }

    public synchronized void automatFreigeben(Kunde kunde) {
        freieAutomaten++;
        notify();
    }


}
