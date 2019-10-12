package bo;

public class Compte {
    protected int id;
    protected float solde;
    protected int typeCompte;
    protected Agence agence;

    /**
     * Constructeur
     */
    public Compte() {
    }

    public Compte(float solde, int typeCompte, Agence agence) {
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.agence = agence;
    }

    public Compte(int id, float solde, int typeCompte, Agence agence) {
        this.id = id;
        this.solde = solde;
        this.typeCompte = typeCompte;
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

    public int getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(int typeCompte) {
        this.typeCompte = typeCompte;
    }

    /**
     * toString()
     */
    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", solde=" + solde +
                ", typeCompte=" + typeCompte +
                ", agence=" + agence +
                '}';
    }
}
