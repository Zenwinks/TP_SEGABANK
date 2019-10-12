import bo.*;
import dao.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import static bo.Operation.TypeOperation.R;
import static bo.Operation.TypeOperation.V;

public class App {

    private static Agence agence = new Agence();
    private static Compte compte = new Compte();
    private static Scanner sc = new Scanner(System.in);
    private static final String SAVE_OPERATIONS = "./resources/Operations.csv/";


    public static void main(String... args) throws SQLException, IOException, ClassNotFoundException {

        dspMainMenu();
    }

    public static void dspMainMenu() throws SQLException, IOException, ClassNotFoundException {

        int response;
        boolean first = true;
        do {
            if (!first) {
                System.out.println("Choix inexistant, veuillez recommencer.");
            }
            System.out.println("======================================");
            System.out.println("=========== MENU - SEGABANK ==========");
            System.out.println("======================================");
            System.out.println("|       1 - Lister les agences       |");
            System.out.println("|    2 - Ajouter un nouveau compte   |");
            System.out.println("|   3 - Modifier un compte existant  |");
            System.out.println("|  4 - Supprimer un compte existant  |");
            System.out.println("|       5 - Lister les comptes       |");
            System.out.println("|     6 - Effectuer une opération    |");
            System.out.println("|     7 - Exporter vos opérations    |");
            System.out.println("|             8 - Quitter            |");
            System.out.println("======================================");
            System.out.print("Entrez votre choix : ");
            try {
                response = sc.nextInt();
            } catch (InputMismatchException e) {
                response = -1;
            } finally {
                sc.nextLine();
            }
            first = false;
        } while (response < 1 || response > 8);

        switch (response) {
            case 1:
                showAgence(true);
                break;
            case 2:
                createCompte();
                break;
            case 3:
                updateCompte();
                break;
            case 4:
                removeCompte();
                break;
            case 5:
                showCompte(true);
                break;
            case 6:
                addOperation();
                break;
//            case 7:
//                exportOperation();
//                break;
            case 8:
                System.out.println("Fermeture de l'application...");
                break;
        }
    }

    private static void showAgence(boolean dspMenu) throws SQLException, IOException, ClassNotFoundException {
        if (dspMenu) {
            System.out.println("======================================");
            System.out.println("========== LISTE DES AGENCES =========");
            System.out.println("======================================");
        }
        IDAO<Long, Agence> dao = new AgenceDAO();
        int i = 1;
        for (Agence agence : dao.findAll()) {
            System.out.println(i + " - " + agence);
            i++;
        }
        if (dspMenu) {
            dspMainMenu();
        }
    }

    private static void createCompte() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("======================================");
        System.out.println("======== CREATION D'UN COMPTE ========");
        System.out.println("======================================");
        IDAO dao = null;
        System.out.printf("Entrez le solde : ");
        int solde = sc.nextInt();
        int type;
        do {
            System.out.print("Quel type de compte ? 1-Simple, 2-Épargne, 3-Payant");
            type = sc.nextInt();
        } while (type < 1 || type > 3);
        Compte createCompte = new Compte();
        System.out.println("Choisissez votre agence : ");
        Agence agence = getListChoixAgence();
        switch (type) {
            case 1:
                dao = new CompteSimpleDAO();
                System.out.println("Vous avez choisi un compte Simple, quel découvert autorisez-vous ?");
                System.out.print("Découvert autorisé : ");
                float decouvertAutorise = sc.nextFloat();
                createCompte = new CompteSimple(solde, agence, decouvertAutorise);
                break;
            case 2:
                dao = new CompteEpargneDAO();
                System.out.println("Vous avez choisi un compte Epargne, quel est son taux d'intérêts ?");
                System.out.print("Taux d'intérêt (en pourcentage) : ");
                float tauxInteret = sc.nextFloat();
                createCompte = new CompteEpargne(solde, agence, tauxInteret);
                break;
            case 3:
                dao = new ComptePayantDAO();
                createCompte = new ComptePayant(solde, agence);
                break;
        }
        try {
            dao.create(createCompte);
            System.out.println("Compte créé avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du compte.");
        } finally {
            dspMainMenu();
        }
    }

    private static void updateCompte() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("======================================");
        System.out.println("====== MODIFICATION D'UN COMPTE ======");
        System.out.println("======================================");
        IDAO<Long, Compte> dao = new CompteDAO();
        if (dao.findAll().isEmpty()) {
            System.out.println("Aucun compte disponible");
            dspMainMenu();
        } else {
            System.out.println("Choisissez le compte à modifier ...");
            Compte compte = getListChoixCompte();
            System.out.printf("======= MODIFICATION DE (%s) =======", compte.getId());
            System.out.printf("Type du compte(%s) (1-Simple, 2-Epargne, 3-Payant): ", compte.getTypeCompte());
            System.out.println();
            System.out.printf("Entrez le solde (Actuellement : %s): ", compte.getSolde());
            int solde = sc.nextInt();
            System.out.printf("Choisissez votre agence (Actuellement : %s): ", compte.getAgence().getCode());
            System.out.println("");
            Agence agence = getListChoixAgence();
            Compte updateCompte = new Compte();
            switch (compte.getTypeCompte()) {
                case 1:
                    System.out.printf("Découvert autorisé : ");
                    float decouvertAutorise = sc.nextFloat();
                    updateCompte = new CompteSimple(compte.getId(),solde, agence, decouvertAutorise);
                    break;
                case 2:
                    System.out.print("Taux d'intérêt (en pourcentage) : ");
                    float tauxInteret = sc.nextFloat();
                    updateCompte = new CompteEpargne(compte.getId(),solde, agence, tauxInteret);
                    break;
                case 3:
                    updateCompte = new ComptePayant(compte.getId(),solde, agence);
                    break;
            }
            dao = changeDao(updateCompte);
            dao.update(updateCompte);
            System.out.println("Compte modifié avec succès.");
            dspMainMenu();
        }
    }

    private static void removeCompte() throws SQLException, IOException, ClassNotFoundException {

        System.out.println("======================================");
        System.out.println("====== SUPPRESSION D'UN COMPTE  ======");
        System.out.println("======================================");
        IDAO<Long,Compte> dao = new CompteDAO();
        IDAO daobis = null;
        if (dao.findAll().isEmpty()) {
            System.out.println("Aucun compte disponible");
            dspMainMenu();
        } else {
            Compte compte = getListChoixCompte();
            Compte removeCompte;
            if(compte.getTypeCompte() == 1) {
                removeCompte = new CompteSimple(compte.getId(), compte.getSolde(), compte.getAgence(),0);
            }
            else if (compte.getTypeCompte() == 2){
                removeCompte = new CompteEpargne(compte.getId(), compte.getSolde(), compte.getAgence(),0);
            }
            else {
                removeCompte = new ComptePayant(compte.getId(), compte.getSolde(),compte.getAgence());
            }
            daobis = changeDao(compte);
            daobis.remove(removeCompte);
            System.out.println("Compte " + compte.getId() + " supprimé avec succès.");
            dspMainMenu();
        }
    }

    private static void addOperation() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("======================================");
        System.out.println("====== CREATION D'UNE OPERATION ======");
        System.out.println("======================================");
        IDAO<Long, Operation> daoOperation = new OperationDAO();
        IDAO<Long, Compte> daoCompte = new CompteDAO();
        if (daoCompte.findAll().isEmpty()) {
            System.out.println("Aucun compte disponible pour créer un opération");
            dspMainMenu();
        } else {
            Operation.TypeOperation typeOperation = getTypeOperationFromKeyboard(true);
            System.out.println("Choisissez pour quel compte l'opération va s'effectuer : ");
            Compte compte = getListChoixCompte();
            System.out.print("Entrez le montant : ");
            int montant = sc.nextInt();
            Compte updateSoldeCompte = new Compte(compte.getId(), getUpdateSolde(montant, typeOperation, compte.getSolde(), compte.getTypeCompte()), compte.getTypeCompte(), compte.getAgence());
            daoCompte = changeDao(updateSoldeCompte);
            daoCompte.update(updateSoldeCompte);
            Operation createOperation = new Operation(typeOperation, compte.getId(), montant);
            daoOperation.create(createOperation);
            System.out.println(createOperation);
            System.out.println("Opération effectuée avec succès.");
            dspMainMenu();
        }
    }

//    private static void exportOperation() throws SQLException, IOException, ClassNotFoundException {
//        System.out.println("======================================");
//        System.out.println("===== EXPORTER DES OPERATIONS  =====");
//        System.out.println("======================================");
//        IDAO<Long, Operation> daoOperation = new OperationDAO();
//        IDAO<Long, Compte> daoCompte = new CompteDAO();
//        if (daoCompte.findAll().isEmpty()) {
//            System.out.println("Aucun compte disponible pour exporter des opérations");
//            dspMainMenu();
//        } else {
//            System.out.println("Choisissez pour quel compte vous voulez exportez les opérations : ");
//            Compte compte = getListChoixCompte();
//            int i = 1;
//            for (Operation operation : daoOperation.findById(compte.getId())) {
//                System.out.println(i + "-" + operation);
//                i++;
//            }
//            File file = new File("./resources/Operations.csv");
//            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
//            Iterator it = daoOperation.findById(compte.getId()).iterator();
//            while (it.hasNext()) {
//                bw.write(it.next().toString());
//                bw.newLine();
//            }
//            bw.close();
//            System.out.println("Sauvegarde des opérations effectuée.");
//            dspMainMenu();
//        }
//    }

    private static void showCompte(boolean dspMenu) throws SQLException, IOException, ClassNotFoundException {
        if (dspMenu) {
            System.out.println("======================================");
            System.out.println("======== LISTE DE VOS COMPTES ========");
            System.out.println("======================================");
        }
        IDAO<Long, Compte> dao = new CompteDAO();
        if (dao.findAll().isEmpty()) {
            System.out.println("Aucun compte disponible");
            dspMainMenu();
        } else {
            int i = 1;
            for (Compte compte : dao.findAll()) {
                System.out.println(i + " - " + compte);
                i++;
            }
            if (dspMenu) {
                dspMainMenu();
            }
        }
    }

    private static Compte getListChoixCompte() throws SQLException, IOException, ClassNotFoundException {
        IDAO<Long, Compte> dao = new CompteDAO();
        boolean first = true;
        int response, size = dao.findAll().size();
        do {
            if (!first) {
                System.out.println("Choix inexistant, veuillez recommencer.");
            }
            showCompte(false);
            System.out.print("Votre choix : ");
            try {
                response = sc.nextInt();
            } catch (InputMismatchException e) {
                response = -1;
            } finally {
                sc.nextLine();
            }
            first = false;
        } while (response < 1 || response > size);
        Compte compte = dao.findAll().get((response - 1));
        return compte;
    }

    private static Agence getListChoixAgence() throws SQLException, IOException, ClassNotFoundException {
        IDAO<Long, Agence> dao = new AgenceDAO();
        boolean first = true;
        //TODO changer le moyen de récupérer la taille
        int response, size = dao.findAll().size();
        do {
            if (!first) {
                System.out.println("Choix inexistant, veuillez recommencer.");
            }
            showAgence(false);
            System.out.print("Votre choix : ");
            try {
                response = sc.nextInt();
            } catch (InputMismatchException e) {
                response = -1;
            } finally {
                sc.nextLine();
            }
            first = false;
        } while (response < 1 || response > size);
        Agence agence = dao.findAll().get((response - 1));
        return agence;
    }

    private static IDAO changeDao(Compte compte){
        if (compte.getTypeCompte() == 1){
            IDAO<Integer, CompteSimple> dao = new CompteSimpleDAO();
            return dao;
        }
        else if (compte.getTypeCompte() == 2){
            IDAO<Integer, CompteEpargne> dao = new CompteEpargneDAO();
            return dao;
        }
        else{
            IDAO<Integer, ComptePayant> dao = new ComptePayantDAO();
            return dao;
        }
    }

    private static float getUpdateSolde(int montant, Operation.TypeOperation typeOperation, float solde, int typecompte) {
        double pourcentage;
        pourcentage = (1.0 * (montant * 5) / 100);
        if (typeOperation == V && typecompte != 3) {
            solde = solde + montant;
        } else if (typeOperation == R && typecompte != 3) {
            solde = solde - montant;
        } else if (typeOperation == V && typecompte == 3) {
            solde = (int) (solde + (montant - pourcentage));
            System.out.println("Un pourcentage de 5% a été déduit de votre opération soit : " + pourcentage);
        } else if (typeOperation == R && typecompte == 3) {
            solde = (int) (solde - (montant + pourcentage));
            System.out.println("Un pourcentage de 5% a été déduit de votre opération soit : " + pourcentage);
        }
        return solde;
    }

    private static Operation.TypeOperation getTypeOperationFromKeyboard(boolean mandatory) {

        boolean first = true;
        int response;
        do {
            if (!first) {
                System.out.println("Mauvais choix... merci de recommencer !");
            }
            System.out
                    .printf("Sélectionner le type d'opération %s...%n", (!mandatory ? "(Tapez Entrée pour passer)" : ""));
            System.out.println("1 - Versement");
            System.out.println("2 - Retrait");
            try {
                System.out.print("Votre choix : ");
                response = sc.nextInt();
            } catch (InputMismatchException e) {
                response = -1;
            } finally {
                sc.nextLine();
            }
            first = false;
        } while (mandatory && (response < 1 || response > 2));
        if (!mandatory && response != 1 && response != 2) return null;
        else return Operation.TypeOperation.values()[response - 1];
    }
}
