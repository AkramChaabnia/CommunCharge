package up.mi.ara.terminal;

import java.util.Scanner;

/**
 * Classe principale du programme.
 */
public class Main {
    /**
     * Méthode principale du programme.
     * 
     * @param args les arguments de ligne de commande
     */
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        System.out.println(path);

        Scanner scanner = new Scanner(System.in);
        CommunauteAgglomeration communaute = new CommunauteAgglomeration();

        // On crée un menu pour la communauté d'agglomération
        Menu menu = new Menu(communaute, scanner);
        // On affiche le menu
        menu.afficherMenu();

        // On ferme le scanner
        scanner.close();
    }
}
