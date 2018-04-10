import java.util.Random;

public class Pfandabgabe {

    private int freieAutomaten;

    public Pfandabgabe(int automatenAnzahl) {
        this.freieAutomaten = automatenAnzahl;
    }

    public synchronized void automatBenutzen(Kunde kunde) throws InterruptedException {
        System.out.println("Freie Automaten: " + freieAutomaten);
        while (freieAutomaten == 0) {
            System.out.println("Gerade sind keine Automaten frei.");
            try {
                wait();
            } catch (InterruptedException ie) {
            }
        }
        freieAutomaten--;
        System.out.println("Es ist ein Automat frei.");
        System.out.println(kunde + " hat " + kunde.getKorbAnzahl() + " Körbe dabei und gibt diese ab.");
        kunde.setPfandabgabe(this);
        Thread pfandAbgabeThread = new Thread(kunde);
        pfandAbgabeThread.start();
    }

    public synchronized void automatFreigeben(Kunde kunde) {
        freieAutomaten++;
        notify();
    }


}
