import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Pfandabgabe implements Runnable {


    private static Logger logger = LoggerFactory.getLogger(Pfandabgabe.class);

    private BlockingQueue<Kunde> queue;

    public Pfandabgabe(BlockingQueue<Kunde> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Kunde kunde = queue.take();
                logger.info(kunde + " hat " + kunde.getKorbAnzahl() + " Körbe dabei und gibt diese ab.");
                int korbanzahl = kunde.getKorbAnzahl();
                int korbZeit;

                for (int y = 0; y < korbanzahl; y++) {
                    korbZeit = kunde.korbErfassenDauer();
                    logger.info("Der " + (y + 1) + ". Korb von " + kunde + " dauert " + korbZeit + " Sekunden.");
                    try {
                        Thread.sleep(korbZeit * Main.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                logger.info(kunde + " ist fertig.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
