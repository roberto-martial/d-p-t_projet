package rencontres;


/**
 * Classe Boss (spécialisation de Gargouille)
 */
public class Boss extends Gargouille {
    public Boss() {
        super();
        setDescription("Boss Gargouille");
    }

    @Override
    public String rencontrer() {
        return "La bataille finale!";
    }
}
