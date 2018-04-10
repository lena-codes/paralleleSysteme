import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static final int SECONDS = 1000;
    final static String newLine = System.getProperty("line.separator");
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        Pfandabgabe pfandabgabe = new Pfandabgabe(1);

        logger.info("Parallele Systeme - Aufgabe 1: Pfandabgabe" + newLine + "Anna-Lena Schwarzkopf (62265)" + newLine + newLine);

        for (int i = 0; i < 30; i++) {
            Kunde kunde = new Kunde(i + 1);
            logger.info(kunde.toString() + " möchte Pfand abgeben!");
            pfandabgabe.automatBenutzen(kunde);
            Thread.sleep(5 * SECONDS);
        }
    }
}
