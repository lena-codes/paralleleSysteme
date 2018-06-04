import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static final int SECONDS = 1000;

    final static String newLine = System.getProperty("line.separator");
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static BlockingQueue<Kunde> queue = new PriorityBlockingQueue<>();
    private static final int KUNDENANZAHL = 30;
    private static final int AUTOMATENANZAHL = 3;
    private static final double GOLDKUNDENPROZENT = 0.3;

    public static void main(String[] args) throws InterruptedException {
        logger.info("Parallele Systeme - Aufgabe 4: Pfandabgabe" + newLine + "Anna-Lena Schwarzkopf (62265)" + newLine + newLine);

        for (int i = 0; i < AUTOMATENANZAHL; i++) {
            new Thread(new Pfandabgabe(queue)).start();
        }

        for (int i = 0; i < KUNDENANZAHL; i++) {

            boolean isGoldkunde = new Random().nextDouble() <= GOLDKUNDENPROZENT;
            Kunde kunde;
            if (isGoldkunde) {
                kunde = new Goldkunde(i + 1);
            } else {
                kunde = new Kunde(i + 1);
            }

            logger.info(kunde + " will Pfand abgeben.");
            queue.put(kunde);
            Thread.sleep(5 * SECONDS);
        }

        for (int i = 0; i < AUTOMATENANZAHL; i++) {
            queue.put(new Kunde(true));
        }


    }
}
