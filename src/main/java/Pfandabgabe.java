import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Pfandabgabe {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private final Semaphore sem;

    private int freieAutomaten;

    private static Logger logger = LoggerFactory.getLogger(Pfandabgabe.class);

    public Pfandabgabe(int automatenAnzahl) {
        this.freieAutomaten = automatenAnzahl;
        sem = new Semaphore(automatenAnzahl);
    }

    public void automatBenutzen(Kunde kunde) throws InterruptedException {
        logger.info("Freie Automaten: " + freieAutomaten);
        try {
            while (freieAutomaten == 0) {
                logger.info("Gerade sind keine Automaten frei.");
                sem.acquire();
            }
            freieAutomaten--;
            kunde.setPfandabgabe(this);
            logger.info("Es ist ein Automat frei.");
            logger.info(kunde + " hat " + kunde.getKorbAnzahl() + " Körbe dabei und gibt diese ab.");
        } finally {
            sem.release();
        }
    }

    public void automatFreigeben() {
        try {
            freieAutomaten++;
            sem.release();
        } finally {
            sem.release();
        }
    }


}
