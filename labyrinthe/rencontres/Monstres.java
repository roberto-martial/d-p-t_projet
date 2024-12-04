package rencontres;
/**
 * on a une sous classe de Rencontre
 */
public class Monstres extends Rencontre {
    public Monstres(String description) {
        super(description);
    }

    @Override
    public String rencontrer() {
        return "Un " + getDescription() + " affreux!";
    }
}

