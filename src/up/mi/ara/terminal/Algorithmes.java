package up.mi.ara.terminal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Cette classe contient les algorithmes pour optimiser les zones de recharge
 * dans une communauté d'agglomération.
 */
public class Algorithmes {

    private static List<Ville> solutionOptimale = null;
    private static int nombreMinZonesRecharge = Integer.MAX_VALUE;

    /**
     * Applique l'algorithme naïf pour ajouter une zone de recharge à chaque ville
     * qui n'en a pas.
     * 
     * @param communaute La communauté d'agglomération.
     * @return La communauté d'agglomération avec les zones de recharge ajoutées.
     */
    public static CommunauteAgglomeration algorithmeNaif(CommunauteAgglomeration communaute) {
        System.out.println(" ▓ Utilisation de l'algorithme naïf. ▓");
        for (Ville ville : communaute.getVilles()) {
            if (!ville.aZoneRecharge()) {
                communaute.ajouterZoneRecharge(ville);
            }
        }
        return communaute;
    }

    /**
     * Applique l'algorithme d'optimisation pour trouver la solution optimale de
     * zones de recharge.
     * 
     * @param communaute La communauté d'agglomération.
     */
    public static void algorithmeOptimiser(CommunauteAgglomeration communaute) {

        communaute.effacerZonesRecharge(); // Efface toutes les zones de recharge

        // On récupère la liste des villes de la communauté
        List<Ville> villes = communaute.getVilles();

        // On initialise les variables de la solution optimale
        solutionOptimale = new ArrayList<>();

        // On initialise le nombre de zones de recharge à un nombre très grand
        nombreMinZonesRecharge = Integer.MAX_VALUE;

        // On crée une liste des villes sans zone de recharge
        List<Ville> etatInitial = new ArrayList<>();
        // On ajoute les villes sans zone de recharge à la liste
        for (Ville ville : villes) {
            if (!ville.aZoneRecharge()) {
                etatInitial.add(ville);
            }
        }

        // On appelle la méthode récursive pour trouver la solution optimale
        optimiserRecursivement(communaute, 0, etatInitial, new HashSet<>());

        // On ajoute les zones de recharge de la solution optimale à la communauté
        communaute.effacerZonesRecharge();

        // On ajoute les zones de recharge de la solution optimale à la communauté
        for (Ville ville : solutionOptimale) {
            communaute.ajouterZoneRecharge(ville);
        }
    }

    /**
     * Méthode récursive pour trouver la solution optimale de zones de recharge.
     * 
     * @param communaute     La communauté d'agglomération.
     * @param index          L'index de la ville actuelle.
     * @param villesSansZone La liste des villes sans zone de recharge.
     * @param villesAvecZone La liste des villes avec zone de recharge.
     */
    private static void optimiserRecursivement(CommunauteAgglomeration communaute, int index,
            List<Ville> villesSansZone, Set<Ville> villesAvecZone) {

        // Si on a parcouru toutes les villes sans zone de recharge
        if (index == villesSansZone.size()) {
            // Si la solution actuelle est meilleure que la solution optimale
            if (villesAvecZone.size() < nombreMinZonesRecharge
                    && communaute.respecteContrainteAccessibiliteCommunaute(villesAvecZone)) {

                // On met à jour le nombre de zones de recharge minimum
                nombreMinZonesRecharge = villesAvecZone.size();

                // On copie la liste des villes avec zone de recharge
                solutionOptimale = new ArrayList<>(villesAvecZone);
            }
            return;
        }

        // On récupère la ville actuelle
        Ville villeActuelle = villesSansZone.get(index);

        // On appelle la méthode récursive pour la ville suivante car on ne veut pas
        // ajouter de zone de recharge à la ville actuelle
        optimiserRecursivement(communaute, index + 1, villesSansZone, villesAvecZone);

        // Cas avec l'ajout d'une zone de recharge
        villesAvecZone.add(villeActuelle);

        // On appelle la méthode récursive pour la ville suivante
        optimiserRecursivement(communaute, index + 1, villesSansZone, villesAvecZone);

        // On retire la zone de recharge de la ville actuelle car on a fini de
        // l'utiliser
        villesAvecZone.remove(villeActuelle);
    }
}
