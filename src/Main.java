import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

    public static final int SECONDS = 1000;
    final static String newLine = System.getProperty("line.separator");

    public static void main(String[] args) throws InterruptedException {

        try {
            PrintStream out = new PrintStream(new FileOutputStream("ParalleleSystemePfandAufgabe.txt"));
            System.setOut(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Pfandabgabe pfandabgabe = new Pfandabgabe(3);

        System.out.print("Parallele Systeme - Aufgabe 1: Pfandabgabe" + newLine + "Anna-Lena Schwarzkopf (62265)" + newLine + newLine);

        for (int i = 0; i < 30; i++) {
            Kunde kunde = new Kunde(i + 1);
            Thread thread = new Thread(kunde);
            System.out.println(kunde.toString() + " möchte Pfand abgeben!");
            pfandabgabe.automatBenutzen(kunde);
            Thread.sleep(5 * SECONDS);
        }
    }
}
