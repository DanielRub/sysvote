/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double cote, som, moyenne;
        int SEUIL = 2;
        som = 0;
        moyenne = 0;
        cote = 0;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < SEUIL; i++) {
            System.out.println("Enter la cote numéro " + (i + 1));
            cote = scanner.nextDouble();
            som = som + cote;
            //scanner.nextLine();
        }
         moyenne =  som / SEUIL;
         System.out.println("la moyenne des "+SEUIL+" cotes est "+ moyenne);
    }

}
