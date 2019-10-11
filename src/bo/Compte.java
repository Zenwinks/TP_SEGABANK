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

    /**
     * Methods
     */
    public void retrait(float montant) {
        if (montant > 0) {
            if (getSolde() - montant >= 0) {
                solde -= montant;
            } else {
                System.out.println("Pas assez d'argent");
            }
        } else {
            System.out.println("Un retrait ne peut être négatif");
        }
    }

    public void versement(float montant) {
        if(montant>0) {
            this.solde += montant;
        }
        else{
            System.out.println("Un virement ne peut être négatif");
        }
    }
}
