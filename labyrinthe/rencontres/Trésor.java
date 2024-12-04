package rencontres;

/**
 * la classe tresor qui herite de rencontre
 */
public class Trésor extends Rencontre {
    public Trésor(String description) {
        super(description);
    }

    @Override
    public String rencontrer() {
        return getDescription() + "! Quelle chance!";
    }
}

