package up.mi.ara.terminal;

import java.util.Scanner;

/**
 * Cette classe permet de saisir manuellement les informations d'une communauté
 * d'agglomération.
 */
public class SaisieManuelle {

    private boolean depuisFichier;

    /**
     * Constructeur de la classe SaisieManuelle.
     * 
     * @param depuisFichier indique si les informations sont saisies depuis un
     *                      fichier ou non.
     */
    public SaisieManuelle(boolean depuisFichier) {
        this.depuisFichier = depuisFichier;
    }

    /**
     * Constructeur par défaut de la classe SaisieManuelle.
     */
    public SaisieManuelle() {
    }

    /**
     * Méthode pour effectuer la saisie manuelle des informations d'une communauté
     * d'agglomération.
     * 
     * @param communaute la communauté d'agglomération à laquelle ajouter les
     *                   informations.
     */
    public void saisieManuelle(CommunauteAgglomeration communaute) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(communaute);

        int nombreVilles = 0;
        if (!depuisFichier) {
            while (true) {
                System.out.println();
                System.out.print(" █ Entrez le nombre de villes (minimum 1) : ");
                ;
                if (sc.hasNextInt()) {
                    nombreVilles = sc.nextInt();
                    if (nombreVilles >= 1) {
                        break;
                    } else {
                        System.out.println(" /!/ Le nombre de villes doit être au moins de 1.");
                    }
                } else {
                    System.out.println(" /!/ Veuillez saisir un entier valide.");
                    sc.next(); // Pour consommer la valeur non valide
                }
            }

            String reponse;
            do {
                System.out.println();
                System.out.println(" █ Voulez-vous générer les noms des villes automatiquement? (oui/non)");
                reponse = sc.next().toLowerCase();

                if (!reponse.equals("oui") && !reponse.equals("non")) {
                    System.out.println(" /!/ Veuillez répondre par 'oui' ou 'non'.");
                }
            } while (!reponse.equals("oui") && !reponse.equals("non"));

            if (reponse.equals("oui")) {
                ajouterVillesAutomatiquement(communaute, nombreVilles);
            } else {
                ajouterVillesManuellement(communaute, nombreVilles, sc);
            }
        }

        // Affiche les villes de la communauté d'agglomération
        communaute.afficherVilles();
        System.out.println();
        // Affiche le menu pour ajouter ou supprimer une route
        menu.menuAjouterSupprimerRoute();
        System.out.println();
        // Affiche le menu pour ajouter ou supprimer une zone de recharge
        menu.menuAjouterSupprimerZoneRecharge();
        // Affiche les routes de la communauté d'agglomération
        communaute.afficherRoutes();
        // Affiche les villes avec une zone de recharge
        communaute.afficherVillesAvecZoneRecharge();

        System.out.println();
        System.out.println(" ▓ Fin de la saisie manuelle. ▓");
        sc.close(); // Ferme le scanner
    }

    /**
     * Méthode privée pour ajouter les villes automatiquement à la communauté
     * d'agglomération.
     * 
     * @param communaute   la communauté d'agglomération à laquelle ajouter les
     *                     villes.
     * @param nombreVilles le nombre de villes à ajouter.
     */
    private void ajouterVillesAutomatiquement(CommunauteAgglomeration communaute, int nombreVilles) {
        for (int i = 0; i < nombreVilles; i++) {
            char lettre = (char) ('A' + (i % 26));
            int indice = (i / 26) + 1;
            String nomVille = lettre + String.valueOf(indice);
            Ville ville = new Ville(nomVille);
            communaute.ajouterVille(ville);
        }
    }

    /**
     * Méthode privée pour ajouter les villes manuellement à la communauté
     * d'agglomération.
     * 
     * @param communaute   la communauté d'agglomération à laquelle ajouter les
     *                     villes.
     * @param nombreVilles le nombre de villes à ajouter.
     * @param sc           le scanner pour la saisie des noms des villes.
     */
    private void ajouterVillesManuellement(CommunauteAgglomeration communaute, int nombreVilles, Scanner sc) {
        for (int i = 0; i < nombreVilles; i++) {
            boolean villeAjoutee = false;
            while (!villeAjoutee) {
                System.out.println();
                System.out.print(" " + (i + 1) + ") Entrez le nom pour la ville suivante : ");
                String nomVille = sc.next();
                Ville ville = new Ville(nomVille);
                villeAjoutee = communaute.ajouterVille(ville);
            }
        }
    }

}
