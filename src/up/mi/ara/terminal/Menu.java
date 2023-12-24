package up.mi.ara.terminal;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Classe représentant le menu principal du programme.
 */
public class Menu {
    private CommunauteAgglomeration communaute;
    private Scanner scanner;
    private boolean estEntrerDansMenuGererZoneRecharge = false;

    /**
     * Constructeur de la classe Menu.
     * 
     * @param communaute L'objet CommunauteAgglomeration utilisé dans le programme.
     */
    public Menu(CommunauteAgglomeration communaute) {
        this.communaute = communaute;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructeur de la classe Menu.
     * 
     * @param communaute L'objet CommunauteAgglomeration utilisé dans le programme.
     * @param scanner    L'objet Scanner utilisé pour la saisie utilisateur.
     */
    public Menu(CommunauteAgglomeration communaute, Scanner scanner) {
        this.communaute = communaute;
        this.scanner = scanner;
    }

    /**
     * Méthode pour afficher le menu principal.
     */
    public void afficherMenu() {
        boolean fichierResolu = false;

        while (true) {
            // Affichage du menu principal
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                    Menu Principal                        ║");
            System.out.println("║══════════════════════════════════════════════════════════║");
            if (!fichierResolu) {
                System.out.println("║  1) Commencer manuellement                               ║");
                System.out.println("║  2) Commencer depuis un fichier                          ║");
                System.out.println("║  3) Quitter                                              ║");
            }
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println();

            // Demander à l'utilisateur de saisir un entier
            int choix;
            while (true) {
                System.out.print(" █ Votre choix : ");
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                    scanner.nextLine(); // Consommez le caractère de nouvelle ligne
                    break;
                } else {
                    System.out.println(" /!/ Veuillez saisir un entier valide.");
                    scanner.nextLine(); // Pour consommer la valeur non valide
                }
            }

            switch (choix) {
                case 1:
                    System.out.println();
                    System.out.println("════════════════════════════════════════════════════════");
                    System.out.println();
                    // Résoudre manuellement
                    System.out.println(" - Mode résolution manuelle :");
                    SaisieManuelle saisieManuelle = new SaisieManuelle(fichierResolu);
                    saisieManuelle.saisieManuelle(communaute);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("════════════════════════════════════════════════════════");
                    System.out.println();
                    // Résoudre à partir d'un fichier
                    if (!fichierResolu) {
                        System.out.println(" - Mode résolution à partir d'un fichier :");
                        System.out.println("    /!/ format accepté pour ajouter une ville : ville(nom_ville). ");
                        System.out.println(
                                "    /!/ format accepté pour ajouter une route : route(nom_ville_1, nom_ville_2). ");
                        System.out.println(
                                "    /!/ format accepté pour ajouter une zone de recharge : recharge(nom_ville). ");
                        System.out.println(
                                "    /!/ Attention : Ne pas oublier les paranthèses et le point à la fin de chaque ligne.");
                        System.out.println();
                        GestionFichier gestionnaire = new GestionFichier(communaute);
                        System.out.print(" █ Veuillez fournir le chemin vers le fichier : ");
                        String cheminFichier = scanner.next();
                        gestionnaire.creerCommunauteDepuisFichier(cheminFichier);
                        fichierResolu = true;
                        System.out.println();
                    } else {
                        System.out.println(" /!/ Fichier déjà résolu. Choisissez une autre option.");
                    }
                    break;
                case 3:
                    System.out.println();
                    // Fin du programme
                    System.out.println("  Fin du programme.");
                    return;
                default:
                    System.out.println(" /!/ Choix invalide. Veuillez sélectionner une option valide.");
                    break;
            }
        }
    }

    /**
     * Méthode pour afficher le menu permettant d'ajouter ou de supprimer une route
     * entre deux villes.
     */
    public void menuAjouterSupprimerRoute() {
        do {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                    Menu des Routes                       ║");
            System.out.println("║══════════════════════════════════════════════════════════║");
            System.out.println("║  1. Ajouter une route                                    ║");
            System.out.println("║  2. Supprimer une route                                  ║");
            System.out.println("║  3. Menu suivant                                         ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");

            int choixMenuRoute;
            while (true) {
                System.out.println();
                System.out.print(" █ Votre choix : ");
                if (scanner.hasNextInt()) {
                    choixMenuRoute = scanner.nextInt();
                    scanner.nextLine(); // Consommez le caractère de nouvelle ligne
                    break;
                } else {
                    System.out.println(" /!/ Veuillez saisir un entier valide.");
                    scanner.next(); // Pour consommer la valeur non valide
                }
            }

            if (choixMenuRoute == 1 || choixMenuRoute == 2) {
                communaute.afficherVilles();
                System.out.println();
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║ Vous allez ajouter ou supprimer une route entre deux villes. ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.println();

                Ville v1 = null;
                Ville v2 = null;

                System.out.println(" █ Entrez le nom de la première ville : ");
                String nomVille1 = scanner.nextLine();
                nomVille1 = nomVille1.toUpperCase();
                System.out.println(" █ Entrez le nom de la deuxième ville : ");
                String nomVille2 = scanner.nextLine();
                nomVille2 = nomVille2.toUpperCase();
                System.out.println();

                for (Ville ville : communaute.getVilles()) {
                    if (ville.getNom().toUpperCase().equals(nomVille1)) {
                        System.out.println(" * Ville " + ville.getNom() + " trouvée");
                        v1 = ville;
                    }
                    if (ville.getNom().toUpperCase().equals(nomVille2)) {
                        System.out.println(" * Ville " + ville.getNom() + " trouvée");
                        v2 = ville;
                    }
                }

                if (choixMenuRoute == 1) {
                    communaute.ajouterRoute(v1, v2);
                } else if (choixMenuRoute == 2) {
                    communaute.supprimerRoute(v1, v2);
                }
            } else if (choixMenuRoute == 3) {

                if (estEntrerDansMenuGererZoneRecharge) {
                    System.out.println();
                    System.out.println(" - Passage au *Menu Zones de Recharge Manuellement* : ");
                    System.out.println();
                    menuAjouterSupprimerZoneRecharge();
                    return;
                } else {
                    System.out.println();
                    System.out.println(" - Passage au *Menu résoudre Zones de Recharge* : ");
                    System.out.println();
                    menuAfficherEtGererMenuZonesRecharges();
                    return;
                }
            } else {
                System.out.println(" /!/ Choix non-valable");
            }

            communaute.afficherRoutes();

        } while (true);
    }

    /**
     * Méthode pour afficher le menu permettant d'ajouter ou de supprimer une zone
     * de recharge entre deux villes.
     */
    public void menuAjouterSupprimerZoneRecharge() {
        int choix;
        do {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║            Menu Zones de Recharge Manuellement           ║");
            System.out.println("║══════════════════════════════════════════════════════════║");
            System.out.println("║  1. Ajouter une zone de recharge                         ║");
            System.out.println("║  2. Supprimer une zone de recharge                       ║");
            System.out.println("║  3. Retourner au menu précédent                          ║");
            System.out.println("║  4. Retourner au menu des Routes                         ║");
            System.out.println("║  5. Sauvegarder la configuration actuelle                ║");
            System.out.println("║  6. Quitter                                              ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");

            while (true) {
                System.out.println();
                System.out.print(" █ Entrez votre choix : ");
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                    break;
                } else {
                    System.out.println(" /!/ Veuillez saisir un entier valide.");
                    scanner.next(); // Pour consommer la valeur non valide
                }
            }

            scanner.nextLine(); // Pour consommer le caractère de nouvelle ligne

            if (choix == 1 || choix == 2) {
                communaute.afficherVilles();
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════════╗");
                System.out.println("║ Vous allez ajouter ou supprimer une zone de recharge d'une villes. ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════╝");
                System.out.println();

                System.out.println(" █ Entrez le nom de la ville : ");
                String nomVille = scanner.nextLine();
                nomVille = nomVille.toUpperCase();
                System.out.println();
                Ville v = null;

                for (Ville ville : communaute.getVilles()) {
                    if (ville.getNom().toUpperCase().equals(nomVille)) {
                        System.out.println(" * Ville " + ville.getNom() + " trouvée");
                        v = ville;
                    }
                }

                if (choix == 1) {
                    communaute.ajouterZoneRecharge(v);
                } else if (choix == 2) {
                    communaute.supprimerZoneRecharge(v);
                }
            } else if (choix == 3) {
                System.out.println();
                System.out.println(" - Passage au *Menu pour Résoudre les zones de recharge* : ");
                System.out.println();
                menuAfficherEtGererMenuZonesRecharges();
                return;
            } else if (choix == 4) {
                System.out.println();
                System.out.println(" - Passage au *Menu des Routes* : ");
                System.out.println();
                menuAjouterSupprimerRoute();
            } else if (choix == 5) {
                System.out.println();
                System.out.print(
                        " - Sauvegarde de la communauté d'agglomération (entrez juste le nom du fichier sans l'extension) : ");
                String nomFichierSauvegarde = scanner.nextLine();
                GestionFichier gestionnaire = new GestionFichier(communaute);
                gestionnaire.sauvegarderCommunaute(nomFichierSauvegarde);
            } else if (choix == 6) {
                System.out.println();
                menuAffichageRecapitulatifCommunaute();
                System.out.println();
                System.out.println(" ▓ Fin du programme. ▓");
                System.exit(0);
            } else {
                System.out.println(" /!/ Choix non-valable");
            }

            communaute.afficherVillesAvecZoneRecharge();

        } while (true);
    }

    /**
     * Méthode pour afficher le menu permettant de résoudre manuellement ou
     * automatiquement la communauté d'agglomération.
     */
    public void menuAfficherEtGererMenuZonesRecharges() {
        estEntrerDansMenuGererZoneRecharge = true;
        while (true) {
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║            Menu résoudre Zones de Recharge :             ║");
            System.out.println("║══════════════════════════════════════════════════════════║");
            System.out.println("║  1) Résoudre manuellement                                ║");
            System.out.println("║  2) Résoudre automatiquement                             ║");
            System.out.println("║  3) Sauvegarder                                          ║");
            System.out.println("║  4) Fin                                                  ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println();

            int choix;
            while (true) {
                System.out.print(" █ Votre choix : ");
                System.out.println();
                String input = scanner.nextLine(); // Lire une ligne de l'entrée utilisateur
                try {
                    choix = Integer.parseInt(input); // Tente de convertir la chaîne en entier Si la conversion réussit,
                                                     // sortir de la boucle interne
                    break;
                } catch (NumberFormatException e) {
                    // Si la conversion échoue, on affiche un message d'erreur et demander à nouveau
                    // l'entrée
                    System.out.println(" /!/ Choix invalide. Veuillez sélectionner une option valide.");
                }
            }

            switch (choix) {
                case 1:
                    // Résoudre manuellement
                    System.out.println(" - Mode résolution manuelle :");
                    System.out.println();
                    System.out.println("    - Passage au *Menu Zones de Recharge Manuellement* :");
                    System.out.println();
                    menuAjouterSupprimerZoneRecharge();
                    break;
                case 2:
                    // Résoudre automatiquement
                    System.out.println(" - Mode résolution automatique :");
                    System.out.println();
                    if (communaute.getZonesRecharge().isEmpty()
                            || !communaute.respecteContrainteAccessibiliteCommunaute()) {
                        System.out.println(
                                " Aucune ville n'a de zone de recharge ou la contrainte d'accessibilité n'est pas respectée.");
                        System.out.println();
                        Algorithmes.algorithmeNaif(communaute);
                        // afficher la liste des villes avec une zone de recharge
                        communaute.afficherVillesAvecZoneRecharge();
                    } else {
                        System.out.println(" ▓ Utilisation de l'algorithme optimisé. ▓");
                        Algorithmes.algorithmeOptimiser(communaute);
                        // afficher la liste des villes avec une zone de recharge
                        communaute.afficherVillesAvecZoneRecharge();
                    }
                    break;
                case 3:
                    // Sauvegarder
                    System.out.print(" - Sauvegarde de la communauté d'agglomération (entrez le nom du fichier) : ");
                    String nomFichierSauvegarde = scanner.nextLine();
                    GestionFichier gestionnaire = new GestionFichier(communaute);
                    gestionnaire.sauvegarderCommunaute(nomFichierSauvegarde);
                    break;
                case 4:
                    System.out.println();
                    menuAffichageRecapitulatifCommunaute();
                    // Fin
                    System.out.println(" ▓ Fin du programme. ▓");
                    System.exit(0); // Quitter le programme
                    return;
                default:
                    System.out.println(" /!/ Choix invalide. Veuillez sélectionner une option valide.");
            }
        }
    }

    /**
     * Méthode pour afficher le récapitulatif de la communauté d'agglomération.
     */
    public void menuAffichageRecapitulatifCommunaute() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║            Récapitulatif de la Communauté d'Agglomération            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");

        afficherVillesAvecDetails();
        afficherRoutesEntreVilles();
        afficherVillesAvecZoneRecharge();

        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Méthode pour afficher les villes avec leurs détails, utilisée dans le
     * récapitulatif.
     */
    private void afficherVillesAvecDetails() {
        // Pour obtenir la liste des villes avec leurs villes adjacentes
        Map<Ville, List<Ville>> mapVilles = communaute.getMapVilles();

        System.out.println();
        System.out.println(" * Villes dans la communauté :");
        System.out.println("");
        System.out.println(String.format("%-15s%-40s", "Ville", "Villes Adjacentes"));

        for (Map.Entry<Ville, List<Ville>> entry : mapVilles.entrySet()) {
            Ville ville = entry.getKey();
            List<Ville> villesAdjacentes = entry.getValue();

            String villesAdj = villesAdjacentes.isEmpty() ? "Aucune"
                    : String.join(", ", villesAdjacentes.stream().map(Ville::getNom).collect(Collectors.toList()));
            System.out.println(String.format("%-15s%-40s", ville.getNom(), villesAdj));
        }
    }

    /**
     * Méthode pour afficher les routes entre les villes, utilisée dans le
     * récapitulatif.
     */
    private void afficherRoutesEntreVilles() {
        Map<Ville, List<Ville>> mapVilles = communaute.getMapVilles();
        boolean trouveRoute = false;

        System.out.println();
        System.out.println(" * Routes entre les villes :");
        System.out.println();

        for (Ville ville : mapVilles.keySet()) {
            for (Ville villeAdjacente : mapVilles.get(ville)) {
                System.out.println(ville.getNom() + " <--> " + villeAdjacente.getNom());
                trouveRoute = true;
            }
        }

        if (!trouveRoute) {
            System.out.println(" Aucune route entre les villes.");
        }
    }

    /**
     * Méthode pour afficher les villes avec une zone de recharge, utilisée dans le
     * récapitulatif.
     */
    private void afficherVillesAvecZoneRecharge() {
        Map<Ville, List<Ville>> mapVilles = communaute.getMapVilles();
        boolean trouveZoneRecharge = false;

        System.out.println();
        System.out.println(" * Villes avec zones de recharge :");
        System.out.println();

        for (Ville ville : mapVilles.keySet()) {
            if (ville.aZoneRecharge()) {
                System.out.println(" - " + ville.getNom());
                trouveZoneRecharge = true;
            }
        }

        if (!trouveZoneRecharge) {
            System.out.println(" Aucune zone de recharge");
        }
    }

}
