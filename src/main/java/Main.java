import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static final int SECONDS = 1000;
    public static final int AUTOMATENANZAHL = 3;

    final static String newLine = System.getProperty("line.separator");
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static ExecutorService pfandsystem = Executors.newFixedThreadPool(AUTOMATENANZAHL);
    //private static ExecutorService pfandsystem = Executors.newCachedThreadPool();
    //private static ExecutorService pfandsystem = Executors.newScheduledThreadPool(AUTOMATENANZAHL);

    public static void main(String[] args) throws InterruptedException {
        Pfandabgabe pfandabgabe = new Pfandabgabe(AUTOMATENANZAHL);

        logger.info("Parallele Systeme - Aufgabe 2: Pfandabgabe" + newLine + "Anna-Lena Schwarzkopf (62265)" + newLine + newLine);

        for (int i = 0; i < 30; i++) {
            Kunde kunde = new Kunde(i + 1);
            logger.info(kunde.toString() + " möchte Pfand abgeben!");
            pfandsystem.execute(kunde);
            pfandabgabe.automatBenutzen(kunde);
            Thread.sleep(5 * SECONDS);
        }

        pfandsystem.shutdown();

    }
}
