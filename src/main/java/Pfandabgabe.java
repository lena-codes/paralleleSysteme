import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

public class Pfandabgabe {

    private final Semaphore sem;

    private static Logger logger = LoggerFactory.getLogger(Pfandabgabe.class);

    public Pfandabgabe(int automatenAnzahl) {
        sem = new Semaphore(automatenAnzahl);
    }

    public void automatBenutzen(Kunde kunde) throws InterruptedException {
        logger.info("Freie Automaten: " + sem.availablePermits());
        sem.acquire();
        kunde.setPfandabgabe(this);
        logger.info(kunde + " hat " + kunde.getKorbAnzahl() + " Körbe dabei und gibt diese ab.");
    }

    public void automatFreigeben() {
        sem.release();
    }

}
