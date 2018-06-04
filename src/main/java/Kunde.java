import java.util.Random;

public class Kunde {

    protected int id;
    protected int korbAnzahl;

    public Kunde(int id) {
        this.korbAnzahl = randomKorbAnzahl();
        this.id = id;
    }

    public int randomKorbAnzahl() {
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

}
