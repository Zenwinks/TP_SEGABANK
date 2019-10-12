package bo;

public class ComptePayant extends Compte {

    public static final float frais = 0.05f;

    public ComptePayant(){

    }

    public ComptePayant(float solde, Agence agence) {
        super(solde, 3, agence);
    }

    public ComptePayant(int id, float solde, Agence agence) {
        super(id, solde, 3, agence);
    }

    @Override
    public String toString() {
        return "ComptePayant{" +
                "id=" + id +
                ", solde=" + solde +
                ", agence=" + agence +
                '}';
    }

    @Override
    public int getTypeCompte() {
        return 3;
    }
}
