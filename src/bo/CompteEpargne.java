package bo;

public class CompteEpargne extends Compte {
    private float tauxInteret;

    /**
     * Construteur
     */
    public CompteEpargne() {
    }

    public CompteEpargne(int id, float solde, Agence agence, float tauxInteret) {
        super(id, solde, agence);
        this.tauxInteret = tauxInteret;
    }

    /**
     * Getters and Setters
     */
    public float getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    /**
     * toString()
     */
    @Override
    public String toString() {
        return "CompteEpargne{" +
                "tauxInteret=" + tauxInteret +
                ", id=" + id +
                ", solde=" + solde +
                ", agence=" + agence +
                '}';
    }

    /**
     * Methods
     */
    public float calculInteret() {

    }
}
