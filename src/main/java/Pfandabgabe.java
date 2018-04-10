import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Pfandabgabe {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private int freieAutomaten;

    private static Logger logger = LoggerFactory.getLogger(Pfandabgabe.class);

    public Pfandabgabe(int automatenAnzahl) {
        this.freieAutomaten = automatenAnzahl;
    }

    public void automatBenutzen(Kunde kunde) {
        lock.lock();
        logger.info("Freie Automaten: " + freieAutomaten);
        try {
            while (freieAutomaten == 0) {
                logger.info("Gerade sind keine Automaten frei.");
                try {
                    condition.await();
                } catch (InterruptedException ie) {
                }
            }
            freieAutomaten--;
            kunde.setPfandabgabe(this);
            logger.info("Es ist ein Automat frei.");
            logger.info(kunde + " hat " + kunde.getKorbAnzahl() + " Körbe dabei und gibt diese ab.");
        } finally {
            lock.unlock();
        }
    }

    public void automatFreigeben() {
        lock.lock();
        try {
            freieAutomaten++;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }


}
