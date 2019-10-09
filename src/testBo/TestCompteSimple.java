package testBo;

import bo.*;

public class TestCompteSimple {
    public static void main(String[] args) {

        CompteSimple compteSimple;

        System.out.printf("Test 1 - Création d'un compte simple%n");
        compteSimple = new CompteSimple(1,1400f, 2, 200f);
        System.out.println(compteSimple.toString());

        System.out.printf("Test 2 - versement de 800.00€%n");
        System.out.println("Pré-versement : " + compteSimple.getSolde() + "€");
        compteSimple.versement(800f);
        System.out.println("Post-versement : " + compteSimple.getSolde() + "€");

        System.out.printf("Test 3 - retrait de 400%n");
        System.out.println("Pré-retrait : " + compteSimple.getSolde() + "€");
        compteSimple.retrait(400f);
        System.out.println("Post-retrait : " + compteSimple.getSolde() + "€");

        System.out.printf("Test 4 - retrait inférieur au découvert%n");
        System.out.println("Pré-retrait : " + compteSimple.getSolde() + "€");
        compteSimple.retrait(1900);
        System.out.println("Post-retrait : " + compteSimple.getSolde() + "€");

        System.out.printf("Test 5 - retrait supérieur au découvert%n");
        System.out.println("Pré-retrait : " + compteSimple.getSolde() + "€");
        compteSimple.retrait(200.00f);
        System.out.println("Post-retrait : " + compteSimple.getSolde() + "€");

    }
}
