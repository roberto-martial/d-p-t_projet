package rencontres;

/**
 * une specialisation de rencontre
 */
public class Rien extends  Rencontre{
    public Rien() {
        super("Rien");
    }

    @Override
    public String rencontrer() {
        return "Un moment pacifique.";
    }
}

