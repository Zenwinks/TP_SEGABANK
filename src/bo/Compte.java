package bo;

public class Compte {
    protected int id;
    protected float solde;
    protected Agence agence;

    /**
     * Constructeur
     */
    public Compte() {
    }

    public Compte(int id, float solde, Agence agence) {
        this.id = id;
        this.solde = solde;
        this.agence = agence;
    }

    /**
     * Getters and Setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    /**
     * toString()
     */
    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", solde=" + solde +
                ", agence=" + agence +
                '}';
    }

    /**
     * Methods
     */
    public float retrait(float montant) {
        this.solde -= montant;
        return this.solde;
    }

    public float versement(float montant) {
        this.solde += montant;
        return this.solde;
    }
}
