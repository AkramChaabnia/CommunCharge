package up.mi.ara.terminal;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Cette classe représente une communauté d'agglomération.
 */
public class CommunauteAgglomeration {

    private final int nombreVilles;
    private Map<Ville, List<Ville>> mapVilles;
    private Map<String, Ville> nomsVillesMajuscules = new HashMap<>();

    /**
     * Constructeur de la classe CommunauteAgglomeration.
     * 
     * @param nombreVilles le nombre de villes de la communauté d'agglomération.
     */
    public CommunauteAgglomeration(int nombreVilles) {
        this.nombreVilles = nombreVilles;
        this.mapVilles = new HashMap<Ville, List<Ville>>();

    }

    /**
     * Constructeur par défaut de la classe CommunauteAgglomeration.
     */
    public CommunauteAgglomeration() {
        this.nombreVilles = 0;
        this.mapVilles = new HashMap<Ville, List<Ville>>();

    }

    /**
     * Méthode effacerZonesRecharge pour effacer toutes les zones de recharge de la
     * communauté d'agglomération
     */
    public void effacerZonesRecharge() {
        for (Ville ville : this.mapVilles.keySet()) {
            ville.setZoneRecharge(false);
        }
    }

    /**
     * Méthode pour vérifier si une ville existe dans la communauté d'agglomération
     * 
     * @param nomVille de type String : le nom de la ville à vérifier
     * @return boolean true si la ville existe, false sinon
     */
    public boolean trouverVille(String nomVille) {
        for (Ville ville : this.mapVilles.keySet()) {
            if (ville.getNom().toLowerCase().equals(nomVille.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode pour ajouter une ville à la communauté d'agglomération
     * 
     * @param ville de type Ville : la ville à ajouter
     * @return boolean true si la ville a été ajoutée, false sinon
     */
    public boolean ajouterVille(Ville ville) {
        String nomVille = ville.getNom();
        String nomVilleMaj = nomVille.toUpperCase();

        if (!nomsVillesMajuscules.containsKey(nomVilleMaj)) {
            this.mapVilles.put(ville, new ArrayList<>());
            nomsVillesMajuscules.put(nomVilleMaj, ville);
            System.out.println(" * Ville créée : " + nomVille);
            return true;
        } else {
            System.out.println(" /!/ Une ville avec le nom '" + nomVille + "' existe déjà.");
            return false;
        }
    }

    /**
     * Méthode pour ajouter une route entre deux villes
     * 
     * @param ville1 de type Ville : la ville à laquelle on veut ajouter une route
     * @param ville2 de type Ville : la ville à laquelle on veut ajouter une route
     * @return boolean true si la route a été ajoutée, false sinon
     */
    public boolean ajouterRoute(Ville ville1, Ville ville2) {
        if (ville1 == null || ville2 == null) {
            System.out.println(" /!/ Au moins l'une des deux villes est nulle. Impossible d'ajouter une route.");
            return false;
        }

        if (this.mapVilles.containsKey(ville1) && this.mapVilles.containsKey(ville2)) {
            if (!ville1.equals(ville2)) {
                if (!this.mapVilles.get(ville1).contains(ville2) && !this.mapVilles.get(ville2).contains(ville1)) {
                    this.mapVilles.get(ville1).add(ville2);
                    this.mapVilles.get(ville2).add(ville1);
                    System.out.println();
                    System.out.println(" * Route ajoutée entre " + ville1.getNom() + " et " + ville2.getNom() + ".");
                    return true;
                } else {
                    System.out.println(" /!/ Une route existe déjà entre ces deux villes.");
                    return false;
                }
            } else {
                System.out.println(" /!/ Vous ne pouvez pas ajouter de route entre une ville et elle-même.");
                return false;
            }
        } else {
            System.out.println(" /!/ Au moins l'une des deux villes n'existe pas. Impossible d'ajouter une route.");
            System.out.println(
                    " /!/ Il est possible que vous ayez mal écrit les majuscules ou les minuscules dans le nom de la ville.");
            return false;
        }
    }

    /**
     * Méthode pour supprimer une route entre deux villes
     * 
     * @param ville1 de type Ville : la ville à laquelle on veut supprimer une route
     * @param ville2 de type Ville : la ville à laquelle on veut supprimer une route
     * @return boolean true si la route a été supprimée, false sinon
     */
    public boolean supprimerRoute(Ville ville1, Ville ville2) {
        if (ville1 == null || ville2 == null) {
            System.out.println(" /!/ Au moins l'une des deux villes est nulle. Impossible de supprimer une route.");
            return false;
        }

        if (this.mapVilles.containsKey(ville1) && this.mapVilles.containsKey(ville2)) {
            if (!ville1.equals(ville2)) {
                if (this.mapVilles.get(ville1).contains(ville2) && this.mapVilles.get(ville2).contains(ville1)) {
                    this.mapVilles.get(ville1).remove(ville2);
                    this.mapVilles.get(ville2).remove(ville1);
                    System.out.println(" * Route supprimée entre " + ville1.getNom() + " et " + ville2.getNom() + ".");
                    return true;
                } else {
                    System.out.println(" /!/ Impossible de supprimer une route inexistante entre ces deux villes.");
                    return false;
                }
            } else {
                System.out.println(" /!/ Vous ne pouvez pas supprimer une route entre une ville et elle-même.");
                return false;
            }
        } else {
            System.out.println(" /!/ Au moins l'une des deux villes n'existe pas. Impossible de supprimer une route.");
            System.out.println(
                    " /!/ Il est possible que vous ayez mal écrit les majuscules ou les minuscules dans le nom de la ville.");
            return false;
        }
    }

    /**
     * Méthode pour obtenir les villes adjacentes à une ville
     * 
     * @param ville de laquelle on veut obtenir les villes adjacentes
     * @return la liste des villes adjacentes à la ville
     */
    public List<Ville> getVillesAdjacentes(Ville ville) {
        return this.mapVilles.get(ville);
    }

    /**
     * Méthode pour obtenir la map des villes de la communauté d'agglomération
     * 
     * @return la map des villes de la communauté d'agglomération
     */
    public Map<Ville, List<Ville>> getMapVilles() {
        return new HashMap<>(this.mapVilles);
    }

    /**
     * Méthode pour obtenir une ville à partir de son nom
     * 
     * @param nomVille de type String : le nom de la ville à obtenir
     * @return Ville la ville correspondante
     */
    public Ville getVille(String nomVille) {
        for (Ville ville : this.mapVilles.keySet()) {
            if (ville.getNom().equals(nomVille)) {
                return ville;
            }
        }
        return null;
    }

    /**
     * Méthode pour obtenir la liste des villes de la communauté d'agglomération
     * 
     * @return la liste des villes de la communauté d'agglomération
     */
    public List<Ville> getVilles() {
        return new ArrayList<Ville>(this.mapVilles.keySet());
    }

    /**
     * Méthode pour obtenir le nombre de villes de la communauté d'agglomération
     * 
     * @return int le nombre de villes de la communauté d'agglomération
     */
    public int getNombreVilles() {
        return this.nombreVilles;
    }

    /**
     * Méthode pour afficher les villes de la communauté d'agglomération
     */
    public void afficherVilles() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  Affichages des villes                   ║");
        System.out.println("║══════════════════════════════════════════════════════════║");
        System.out.println("");
        System.out.println("  Voici la liste des villes : ");

        int numero = 1;
        for (Ville ville : this.mapVilles.keySet()) {
            System.out.println("  " + numero + ". " + ville.getNom());
            numero++;
        }
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    /**
     * Méthode pour afficher les routes de la communauté d'agglomération
     */
    public void afficherRoutes() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   Affichage des routes                   ║");
        System.out.println("║══════════════════════════════════════════════════════════║");
        System.out.println("");

        for (Ville ville : this.mapVilles.keySet()) {
            System.out.print("  " + ville.getNom() + " reliée à : ");
            List<Ville> villesAdjacentes = this.mapVilles.get(ville);
            if (villesAdjacentes.isEmpty()) {
                System.out.println("Aucune");
                continue;
            }

            for (int i = 0; i < villesAdjacentes.size(); i++) {
                System.out.print(villesAdjacentes.get(i).getNom());
                if (i < villesAdjacentes.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    /**
     * Méthode pour obtenir les villes avec une zone de recharge
     * 
     * @return la liste des villes avec une zone de recharge
     */
    public List<Ville> getZonesRecharge() { // retourne les villes avec une zone de recharge
        List<Ville> villesAvecZoneRecharge = new ArrayList<Ville>();
        for (Ville ville : this.mapVilles.keySet()) {
            if (ville.aZoneRecharge()) {
                villesAvecZoneRecharge.add(ville);
            }
        }
        return villesAvecZoneRecharge;
    }

    /**
     * Méthode pour obtenir les routes de la communauté d'agglomération
     * 
     * @return la map des routes de la communauté d'agglomération
     */
    public Map<Ville, List<Ville>> getRoutes() {
        return this.mapVilles;
    }

    /**
     * Méthode pour les villes respectent la contrainte d'accessibilité
     * 
     * @param villesAvecZone les villes avec une zone de recharge
     * @return boolean true si la contrainte est respectée, false sinon
     */
    public boolean respecteContrainteAccessibiliteCommunaute(Set<Ville> villesAvecZone) {
        for (Ville ville : this.mapVilles.keySet()) {
            if (!ville.aZoneRecharge() && !estAccessible(ville, villesAvecZone)) {
                return false; // Si une ville sans zone de recharge n'est pas accessible, la contrainte n'est
                              // pas respectée
            }
        }
        return true; // Toutes les villes respectent la contrainte d'accessibilité
    }

    /**
     * Méthode pour obtenir si une ville est accessible
     * 
     * @param ville          de type Ville : la ville à laquelle on veut ajouter une
     *                       zone de recharge
     * @param villesAvecZone de type Set<Ville> : les villes avec une zone de
     *                       recharge
     * @return boolean true si la ville est accessible, false sinon
     */
    private boolean estAccessible(Ville ville, Set<Ville> villesAvecZone) {
        if (villesAvecZone.contains(ville)) {
            return true; // La ville a une zone de recharge
        }

        for (Ville villeAdjacente : getVillesAdjacentes(ville)) {
            if (villesAvecZone.contains(villeAdjacente)) {
                return true; // Une ville adjacente a une zone de recharge
            }
        }

        return false; // Aucune des conditions ci-dessus n'a été satisfaite, la ville n'est pas
                      // accessible
    }

    /**
     * Méthode pour afficher les villes avec une zone de recharge
     */
    public void afficherVillesAvecZoneRecharge() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║       Affichage des villes avec zone de recharge         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        boolean trouver = false;
        for (Ville ville : this.mapVilles.keySet()) {
            if (ville.aZoneRecharge()) {
                System.out.println("  - " + ville.getNom());
                trouver = true;
            }
        }

        if (!trouver) {
            System.out.println("  Aucune ville avec une zone de recharge.");
        }

        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    /**
     * Méthode pour ajouter une zone de recharge à une ville
     * 
     * @param ville de type Ville : la ville à laquelle on veut ajouter une zone de
     *              recharge
     * @return boolean true si la zone de recharge a été ajoutée, false sinon
     */
    public boolean ajouterZoneRecharge(Ville ville) {
        if (ville == null) {
            System.out.println(" /!/ Vous ne pouvez pas ajouter une zone de recharge à une ville nulle !");
            return false;
        }

        // si la ville n'existe pas, on ne peut pas supprimer une zone de recharge
        if (!this.mapVilles.containsKey(ville)) {
            System.out.println(" /!/ Vous ne pouvez pas ajouter une zone de recharge à une ville qui n'existe pas !");
            return false;
        }

        System.out.println();
        if (!ville.aZoneRecharge()) {
            ville.setZoneRecharge(true);
            System.out.println(" * Zone de recharge ajoutée à la ville " + ville.getNom());
            return true;
        } else {
            System.out.println(" /!/ La ville " + ville.getNom() + " possède déjà une zone de recharge.");
            return false;
        }
    }

    /**
     * Méthode pour vérifier si les villes respectent la contrainte d'accessibilité,
     * utilisée pour vérifier si la communauté respecte la contrainte
     * d'accessibilité
     * 
     * @return boolean true si la contrainte est respectée, false sinon
     */
    public boolean respecteContrainteAccessibiliteCommunaute() {
        for (Ville ville : mapVilles.keySet()) {
            if (!respecteContrainteAccessibilite(ville)) {
                return false; // Si au moins une ville ne respecte pas la contrainte, la communauté ne la
                              // respecte pas non plus
            }
        }
        return true; // Toutes les villes respectent la contrainte d'accessibilité
    }

    /**
     * Méthode pour vérifier si une ville respecte la contrainte d'accessibilité,
     * utilisée pour vérifier si la communauté respecte la contrainte
     * d'accessibilité
     * 
     * @param ville de type Ville : pour laquelle on veut vérifier si elle respecte
     *              la contrainte d'accessibilité
     * @return boolean true si la contrainte est respectée, false sinon
     */
    public boolean respecteContrainteAccessibilite(Ville ville) {
        if (ville.aZoneRecharge()) {
            return true; // Une ville avec une zone de recharge respecte la contrainte
        }

        for (Ville villeAdjacente : getVillesAdjacentes(ville)) {
            if (villeAdjacente.aZoneRecharge()) {
                return true; // Si une ville adjacente a une zone de recharge, la contrainte est respectée
            }
        }

        return false; // Aucune des conditions ci-dessus n'a été satisfaite, la contrainte n'est pas
                      // respectée
    }

    /**
     * Méthode pour obtenir le nombre de zones de recharge adjacents à une ville,
     * utilisée pour vérifier si on peut supprimer la zone de recharge d'une ville
     * 
     * @param ville de type Ville : la ville à laquelle on veut ajouter une zone de
     *              recharge
     * @return int le nombre de zones de recharge adjacents à une ville
     */
    public int nombreZonesRechargeAdjacents(Ville ville) {
        int nombreZonesRechargeAdjacents = 0;
        for (Ville villeAdjacente : getVillesAdjacentes(ville)) {
            if (villeAdjacente.aZoneRecharge()) {
                nombreZonesRechargeAdjacents++;
            }
        }
        return nombreZonesRechargeAdjacents;
    }

    // ======== Méthodes pour supprimer une zone de recharge d'une ville
    // (auxiliaires + principale) =========

    /**
     * Méthode pour supprimer une zone de recharge d'une ville
     * 
     * @param ville de type Ville : la ville à laquelle on veut supprimer une zone
     *              de recharge
     */
    public void supprimerZoneRecharge(Ville ville) {

        if (ville == null) {
            System.out.println(" /!/ Vous ne pouvez pas supprimer une zone de recharge d'une ville nulle !");
            return;
        }

        // si la ville n'existe pas, on ne peut pas supprimer une zone de recharge
        if (!this.mapVilles.containsKey(ville)) {
            System.out.println(" /!/ Vous ne pouvez pas supprimer une zone de recharge d'une ville qui n'existe pas !");
            return;
        }

        // si la ville n'a pas de zone de recharge, on ne peut pas la supprimer
        if (!ville.aZoneRecharge()) {
            System.out.println(" /!/ Vous ne pouvez pas supprimer une zone de recharge qui n'existe pas !");
            return;
        }

        // si une ville est isolée et qu'elle a une zone de recharge, on ne peut pas la
        // supprimer
        if (getVillesAdjacentes(ville).isEmpty()) {
            System.out.println(" /!/ Vous ne pouvez pas supprimer une zone de recharge d'une ville isolée !");
            return;
        }

        // si tous les voisins de la ville en question ont une zone de recharge, on peut
        // supprimer la zone de recharge
        // et condition1Check devient true
        if (tousVoisinsAvecZoneRecharge(ville)) {
            ville.setZoneRecharge(false);
            System.out.println(" * La zone de recharge a été supprimée de la ville " + ville.getNom());
            restaurerZoneRechargeSiNecessaire(ville);
            return;
        }

        // ===================================
        // maintenant les cas ou au moins un voisin de la ville en question n'a pas de
        // zone de recharge
        // ===================================

        // si un voisin de la ville en question n'a pas une zone de recharge et n'a
        // aucun voisin, donc il dépend
        // forcément de la ville en question, donc on ne peut pas supprimer la zone de
        // recharge de la ville en question
        // cela est pour tous les voisins de la ville en question
        // et dès que cette vérification est faite pour tous les voisins,
        // condition2Check devient true
        if (unVoisinIsoleSansZoneRecharge(ville)) {
            return;
        }

        // si au moins un voisins de la ville en question n'a pas de zone de recharge,
        // on parcourt les voisins de ce voisin pour
        // trouver une ville qui a une zone de recharge. Si on en trouve une, on peut
        // supprimer la zone de recharge de la ville en question
        // et condition2Check devient true dès qu'on vérifie cela pour tous les voisins
        // sans zone de recharge
        // on ne prend pas en compte la ville en question quand on vérifie les voisins
        // des voisins
        if (!tousVoisinsPasDependantsDeVille(ville)) {
            return;
        }

        // si on arrive ici, nous vérifions les deux conditions pour pouvoir supprimer
        // la zone de recharge
        if (!unVoisinIsoleSansZoneRecharge(ville) && tousVoisinsPasDependantsDeVille(ville)) {
            ville.setZoneRecharge(false);
            System.out.println(" * La zone de recharge a été supprimée de la ville " + ville.getNom());

            // Enfin, si après supprésion de la zone de recharge, si la ville en question
            // n'est connéctée à aucune autre ville avec une zone de recharge,
            // la zone de recharge est rétablie
            restaurerZoneRechargeSiNecessaire(ville);
            return;
        } else {
            System.out.println(
                    " /!/ Vous ne pouvez pas supprimer cette zone de recharge car un des voisins de la ville en question dépend de cette ville seulement");
            return;
        }

    }

    /**
     * Méthode auxiliaire pour vérifier si tous les voisins d'une ville ont une zone
     * de recharge, utilisée pour vérifier si on peut supprimer la zone de recharge
     * d'une ville
     * 
     * @param ville de type Ville : pour laquelle on veut vérifier si tous les
     *              voisins ont une zone de recharge
     * @return boolean true si tous les voisins ont une zone de recharge, false
     *         sinon
     */
    private boolean tousVoisinsAvecZoneRecharge(Ville ville) {
        for (Ville v : getVillesAdjacentes(ville)) {
            if (!v.aZoneRecharge()) { // si au moins un voisin n'a pas de zone de recharge, cette condition n'est pas
                                      // vérifiée et on passe au suivant
                return false;
            }
        }

        return true; // si tous les voisins ont une zone de recharge, cette condition est vérifiée
    }

    /**
     * Méthode auxiliaire pour vérifier si un voisin est forcément dépendant de la
     * ville en question, utilisée pour vérifier si on peut supprimer la zone de
     * recharge d'une ville
     * 
     * @param voisin de type Ville : le voisin à vérifier
     * @param ville  de type Ville
     * @return boolean
     */
    private boolean estVoisinDependant(Ville voisin, Ville ville) {
        // Vérifier si le voisin dépend forcément de la ville en question
        return getVillesAdjacentes(voisin).size() == 1 && getVillesAdjacentes(voisin).contains(ville);
    }

    /**
     * Méthode auxiliaire pour vérifier si un voisin est isolé et n'a pas de zone de
     * recharge, utilisée pour vérifier si on peut supprimer la zone de recharge
     * d'une ville
     * 
     * @param ville de type Ville, qu'on veut vérifier si elle est isolée
     * @return boolean
     */
    private boolean unVoisinIsoleSansZoneRecharge(Ville ville) {
        for (Ville v : getVillesAdjacentes(ville)) {
            if (!v.aZoneRecharge() && estVoisinDependant(v, ville)) {
                System.out.println(
                        " /!/ Vous ne pouvez pas supprimer cette zone de recharge car un des voisins de la ville en question dépend de cette ville seulement");
                return true;
            }
        }
        // Si tous les voisins ont été vérifiés et aucun n'est forcément dépendant de la
        // ville en question, on passe à la suite
        return false;
    }

    /**
     * Méthode auxiliaire pour vérifier si tous les voisins d'une ville sont
     * dépendants de la ville en question, utilisée pour vérifier si on peut
     * supprimer la zone de recharge d'une ville
     * 
     * @param ville de type Ville : la ville qu'on veut vérifier
     * @return boolean
     */
    private boolean tousVoisinsPasDependantsDeVille(Ville ville) {
        for (Ville voisin : getVillesAdjacentes(ville)) {
            if (!voisin.aZoneRecharge() && !existeVoisinAvecZoneRecharge(voisin, ville)) {
                System.out.println(
                        " /!/ Vous ne pouvez pas supprimer cette zone de recharge car un des voisins de la ville en question n'est pas connecté à d'autres voisins avec une zone de recharge.");
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode auxiliaire pour vérifier si un voisin a un autre voisin avec une zone
     * de recharge
     * 
     * @param voisin de type Ville : le voisin à vérifier
     * @param ville  de type Ville
     * @return boolean true si le voisin a un autre voisin avec une zone de
     *         recharge, false sinon
     */
    private boolean existeVoisinAvecZoneRecharge(Ville voisin, Ville ville) {
        for (Ville voisinDuVoisin : getVillesAdjacentes(voisin)) {
            if (voisinDuVoisin.aZoneRecharge() && !voisinDuVoisin.getNom().equals(ville.getNom())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode auxiliaire pour rétablir la zone de recharge d'une ville si elle est
     * devenue isolée
     * 
     * @param ville de type Ville : la ville à laquelle on veut ajouter une zone de
     *              recharge
     */
    private void restaurerZoneRechargeSiNecessaire(Ville ville) {
        for (Ville v : getVillesAdjacentes(ville)) {
            if (v.aZoneRecharge()) {
                return;
            }
        }
        ville.setZoneRecharge(true);
        System.out.println(" /!/ La zone de recharge a été rétablie pour la ville " + ville.getNom()
                + " car elle est devenue isolée après la suppression. Donc ce n'est pas possible finalement");
    }

}
