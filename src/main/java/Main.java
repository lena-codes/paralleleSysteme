import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final int SECONDS = 1000;
    public static final int AUTOMATENANZAHL = 3;

    final static String newLine = System.getProperty("line.separator");

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static ExecutorService pfandsystem = Executors.newFixedThreadPool(AUTOMATENANZAHL);

    public static void main(String[] args) throws InterruptedException {
        Pfandabgabe pfandabgabe = new Pfandabgabe(AUTOMATENANZAHL);

        logger.info("Parallele Systeme - Aufgabe 3: Pfandabgabe" + newLine + "Anna-Lena Schwarzkopf (62265)" + newLine + newLine);

        for (int i = 0; i < 30; i++) {
            Kunde kunde = new Kunde(i + 1);

            logger.info(kunde + " möchte Pfand abgeben!");
            pfandsystem.execute(() -> {
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
                Pfandabgabe pfand = kunde.getPfandabgabe();
                pfand.automatFreigeben();
            });
            pfandabgabe.automatBenutzen(kunde);
            Thread.sleep(5 * SECONDS);
        }

        pfandsystem.shutdown();

    }
}
