package bo;

public abstract class Compte {
    protected int id;
    protected float solde;
    protected int type;
    protected int agence;

    /**
     * Constructeur
     */
    public Compte() {
    }

    public Compte(int id, float solde, int agence) {
        this.id = id;
        this.solde = solde;
        this.type = type;
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

    public int getAgence() {
        return agence;
    }

    public void setAgence(int agence) {
        this.agence = agence;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
