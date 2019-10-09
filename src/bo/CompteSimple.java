package bo;

public class CompteSimple extends Compte {
    private float decouvertAutorise;

    /**
     * Construteur
     */
    public CompteSimple() {
    }

    public CompteSimple(int id, float solde, Agence agence, float decouvertAutorise) {
        super(id, solde, agence);
        this.decouvertAutorise = decouvertAutorise;
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

    @Override
    public void retrait(float montant) {
        if (montant > 0) {
            if (getSolde() - montant > (-decouvertAutorise)) {
                setSolde(getSolde() - montant);
            } else {
                System.out.println("Découvert dépassé, retrait impossible");
            }
        } else {
            System.out.println("Un retrait ne peut être négatif");
        }
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
}
