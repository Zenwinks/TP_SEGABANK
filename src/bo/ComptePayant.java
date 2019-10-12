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
    public void versement(float montant) {
        if (montant > 0) {
            setSolde(getSolde() + (montant - (montant * frais)));
        } else {
            System.out.println("Un virement ne peut être négatif");
        }
    }

    @Override
    public void retrait(float montant) {
        if (montant > 0) {
            float montantAvecFrais = (montant + (montant * frais));
            if (getSolde() - montantAvecFrais >= 0) {
                setSolde(getSolde() - montantAvecFrais);
            } else {
                System.out.println("Pas assez d'argent");
            }
        } else {
            System.out.println("Un retrait ne peut être négatif");
        }
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
