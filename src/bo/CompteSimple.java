package bo;

public class CompteSimple extends Compte {
    private float decouvertAutorise;

    /**
     * Construteur
     */
    public CompteSimple() {
    }

    public CompteSimple(float solde, Agence agence, float decouvertAutorise) {
        super(solde, 1, agence);
        this.decouvertAutorise = decouvertAutorise;
    }

    public CompteSimple(int id, float solde, Agence agence, float decouvertAutorise) {
        super(id, solde, 1, agence);
        this.decouvertAutorise = decouvertAutorise;
    }

    public CompteSimple(int id, float solde, Agence agence) {
        super(id, solde, 1, agence);
    }

    /**
     * Getters and Setters
     */
    public float getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(float decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

    /**
     * toString()
     */
    @Override
    public String toString() {
        return "CompteSimple{" +
                "decouvertAutorise=" + decouvertAutorise +
                ", id=" + id +
                ", solde=" + solde +
                ", agence=" + agence +
                '}';
    }

    @Override
    public int getTypeCompte() {
        return 1;
    }
}
