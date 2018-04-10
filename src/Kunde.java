import java.util.Random;

public class Kunde implements Runnable {

    private final int id;
    private int korbAnzahl;
    private Pfandabgabe pfandabgabe;

    public Kunde(int id) {
        this.korbAnzahl = randomKorbAnzahl();
        this.id = id;
    }

    private static int randomKorbAnzahl() {
        Random random = new Random();
        return random.nextInt(3) + 2;
    }

    private int korbErfassenDauer() {
        Random rand = new Random();
        return rand.nextInt(4) + 3;
    }

    public int getKorbAnzahl() {
        return korbAnzahl;
    }

    @Override
    public void run() {
        int korbanzahl = this.getKorbAnzahl();
        int korbZeit = 0;

        for (int i = 0; i < korbanzahl; i++) {
            korbZeit = this.korbErfassenDauer();
            System.out.println("Der " + (i + 1) + ". Korb von " + this + " dauert " + korbZeit + " Sekunden.");
            try {
                Thread.sleep(korbZeit * Main.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(this + " ist fertig.");
        pfandabgabe.automatFreigeben(this);
    }

    public String toString() {
        return "Kunde#" + id;
    }

    public void setPfandabgabe(Pfandabgabe pfandabgabe) {
        this.pfandabgabe = pfandabgabe;
    }
}
