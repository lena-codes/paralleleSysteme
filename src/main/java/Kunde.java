import java.util.Random;

public class Kunde implements Comparable<Kunde> {

    protected int id;
    protected int korbAnzahl;
    protected final boolean poisonPill;

    public Kunde(int id) {
        this.korbAnzahl = randomKorbAnzahl();
        this.id = id;
        this.poisonPill = false;
    }

    public Kunde(boolean poisonPill) {
        this.poisonPill = poisonPill;
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

    public boolean isPoisonPill() {
        return poisonPill;
    }

    public String toString() {
        return "Kunde#" + id;
    }

    @Override
    public int compareTo(Kunde o) {
        if (this instanceof Goldkunde) {
            if (o instanceof Kunde) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (o instanceof Goldkunde) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
