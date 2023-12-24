package up.mi.ara.terminal;

import java.io.*;
import java.util.*;

/**
 * Cette classe gère la création et la sauvegarde d'une communauté
 * d'agglomération à partir d'un fichier.
 */
public class GestionFichier {
    private CommunauteAgglomeration communaute;
    private Scanner scanner;

    /**
     * Constructeur de la classe GestionFichier.
     * 
     * @param communaute La communauté d'agglomération à gérer.
     */
    public GestionFichier(CommunauteAgglomeration communaute) {
        this.communaute = communaute;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Crée une communauté d'agglomération à partir d'un fichier.
     * 
     * @param cheminFichier Le chemin du fichier contenant les informations de la
     *                      communauté.
     */
    public void creerCommunauteDepuisFichier(String cheminFichier) {
        boolean fichierCharge = false;

        while (!fichierCharge) {
            List<String> lignes = lireFichier(cheminFichier);

            if (lignes == null || lignes.isEmpty()) {
                System.out.println(" /!/ Erreur : Le fichier est vide ou n'a pas pu être lu.");
                System.out.print(" █ Veuillez fournir le chemin vers un fichier valide : ");
                cheminFichier = scanner.nextLine(); // Demander à nouveau le chemin du fichier
            } else {
                int nombreVilles = lignes.size();
                CommunauteAgglomeration communaute = new CommunauteAgglomeration(nombreVilles);
                creerCommunaute(communaute, lignes);

                // Menu pour gérer les zones de recharge
                Menu menu = new Menu(communaute, scanner);
                menu.menuAfficherEtGererMenuZonesRecharges();

                fichierCharge = true; // Sortir de la boucle puisque le fichier est chargé avec succès
            }
        }
    }

    /**
     * Lit un fichier et retourne son contenu sous forme de liste de lignes.
     * 
     * @param cheminFichier Le chemin du fichier à lire.
     * @return List<String> Une liste de lignes du fichier, ou null en cas d'erreur
     *         de lecture.
     */
    private List<String> lireFichier(String cheminFichier) {
        List<String> lignes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                lignes.add(ligne);
            }
            return lignes;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Crée une communauté d'agglomération à partir des lignes du fichier.
     * 
     * @param communaute La communauté d'agglomération à créer.
     * @param lignes     Les lignes du fichier contenant les informations de la
     *                   communauté.
     */
    private void creerCommunaute(CommunauteAgglomeration communaute, List<String> lignes) {
        for (String ligne : lignes) {
            ligne = ligne.trim();

            if (ligne.isEmpty()) {
                continue; // Ignorer les lignes vides
            }

            if ((ligne.startsWith("ville(") || ligne.startsWith("VILLE(")) && ligne.endsWith(").")) {
                String nomVille = ligne.substring(6, ligne.length() - 2); // Extraire le nom entre parenthèses
                Ville nouvelleVille = new Ville(nomVille.toUpperCase());
                communaute.ajouterVille(nouvelleVille);
            } else if (ligne.startsWith("route(") || ligne.startsWith("ROUTE(")) {
                String[] parts = ligne.substring(6, ligne.length() - 2).split(",");
                if (parts.length == 2) {
                    Ville ville1 = communaute.getVille(parts[0].toUpperCase());
                    Ville ville2 = communaute.getVille(parts[1].toUpperCase());
                    if (ville1 != null && ville2 != null) {
                        communaute.ajouterRoute(ville1, ville2);
                    }
                }
            } else if (ligne.startsWith("recharge(") || ligne.startsWith("RECHARGE(")) {
                String nomVille = ligne.substring(9, ligne.length() - 2);
                Ville ville = communaute.getVille(nomVille.toUpperCase());
                if (ville != null) {
                    communaute.ajouterZoneRecharge(ville);
                }
            }
        }
    }

    /**
     * Sauvegarde la communauté d'agglomération dans un fichier.
     * 
     * @param nomFichier Le nom du fichier de sauvegarde.
     */
    public void sauvegarderCommunaute(String nomFichier) {
        String directoryPath = "fichiers/";
        String fileName = nomFichier + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + fileName))) {
            // Écrire les informations de la communauté d'agglomération dans le fichier
            for (Ville ville : communaute.getVilles()) {
                String ligne = "ville(" + ville.getNom() + ").";
                writer.write(ligne);
                writer.newLine(); // Ajoute une nouvelle ligne après chaque enregistrement
                System.out.println("Écriture dans le fichier : " + ligne);
            }

            for (Ville ville1 : communaute.getRoutes().keySet()) {
                for (Ville ville2 : communaute.getRoutes().get(ville1)) {
                    String ligne = "route(" + ville1.getNom() + "," + ville2.getNom() + ").";
                    writer.write(ligne);
                    writer.newLine();
                    System.out.println("Écriture dans le fichier : " + ligne);
                }
            }

            for (Ville ville : communaute.getZonesRecharge()) {
                String ligne = "recharge(" + ville.getNom() + ").";
                writer.write(ligne);
                writer.newLine();
                System.out.println("Écriture dans le fichier : " + ligne);
            }

            writer.flush();

            System.out.println(" * Communauté d'agglomération sauvegardée dans " + nomFichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
