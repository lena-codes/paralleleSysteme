import java.util.Random;

public class Kunde {

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

    public int korbErfassenDauer() {
        Random rand = new Random();
        return rand.nextInt(4) + 3;
    }

    public int getKorbAnzahl() {
        return korbAnzahl;
    }

    public String toString() {
        return "Kunde#" + id;
    }

    public void setPfandabgabe(Pfandabgabe pfandabgabe) {
        this.pfandabgabe = pfandabgabe;
    }

    public Pfandabgabe getPfandabgabe() {
        return pfandabgabe;
    }
}
