package bo;

public class Operation {

    public enum TypeOperation {
        V("Versement") , R("Retrait");

        private String label;

        TypeOperation( String label ) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel( String label ) {
            this.label = label;
        }
    }

    private TypeOperation typeOperation;
    private int id;
    private int codeCompte;
    private int montant;

    public Operation() {
    }

    public Operation(TypeOperation typeOperation, int codeCompte, int montant) {
        this.typeOperation = typeOperation;
        this.codeCompte = codeCompte;
        this.montant = montant;
    }

    public Operation(int id, TypeOperation typeOperation, int codeCompte, int montant) {
        this.id = id;
        this.typeOperation = typeOperation;
        this.codeCompte = codeCompte;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodeCompte() {
        return codeCompte;
    }

    public void setCodeCompte(int codeCompte) {
        this.codeCompte = codeCompte;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Operation{");
        sb.append("id=").append(id);
        sb.append(", typeOperation='").append(typeOperation).append('\'');
        sb.append(", codeCompte=").append(codeCompte);
        sb.append(", montant=").append(montant);
        sb.append('}');
        return sb.toString();
    }
}
