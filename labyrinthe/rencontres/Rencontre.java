package rencontres;
/**
 * on a une classe rencontre qui definie le type de
 * rencontre que ce soit monstre ou tresor
 */
public abstract  class Rencontre {
    private String description;

    public Rencontre(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String rencontrer();
}

