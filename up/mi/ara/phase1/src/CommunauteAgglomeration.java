package up.mi.ara.phase1.src;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class CommunauteAgglomeration {
  // private final NOMBRES_VILLES = 26;
  private int nombreVilles;
  private Map<Ville, List<Ville>> mapVilles;
  private Scanner scanner;

  public CommunauteAgglomeration(int nombreVilles) {
    this.nombreVilles = nombreVilles;
    this.mapVilles = new HashMap<Ville, List<Ville>>();
    this.scanner = new Scanner(System.in);
  }

  public void ajouterVille(Ville ville) {
    this.mapVilles.put(ville, new ArrayList<Ville>());
  }

  public void ajouterRoute(Ville ville1, Ville ville2) {
    this.mapVilles.get(ville1).add(ville2);
    this.mapVilles.get(ville2).add(ville1);
  }

  public void supprimerRoute(Ville ville1, Ville ville2) {
    this.mapVilles.get(ville1).remove(ville2);
    this.mapVilles.get(ville2).remove(ville1);
  }

  public List<Ville> getVillesAdjacentes(Ville ville) { // retourne la liste des villes adjacentes à la ville passée en
    return this.mapVilles.get(ville);
  }

  public List<Ville> getVilles() { // retourne la liste des villes de la communauté d'agglomération
    return new ArrayList<Ville>(this.mapVilles.keySet());
  }

  public void afficherVilles() {
    System.out.println("Liste des villes : ");
    for (Ville ville : this.mapVilles.keySet()) {
      System.out.print(ville.getNom() + " ");
    }
  }

  public void afficherRoutes() {
    System.out.println("Liste des routes : ");
    for (Ville ville : this.mapVilles.keySet()) {
      System.out.print(ville.getNom() + " : ");
      for (Ville villeAdjacente : this.mapVilles.get(ville)) {
        System.out.print(villeAdjacente.getNom() + " ");
      }
      System.out.println();
    }
  }

  public void afficherVillesAvecZoneRecharge() {
    System.out.println("Liste des villes avec zone de recharge : ");
    for (Ville ville : this.mapVilles.keySet()) {
      if (ville.aZoneRecharge()) {
        System.out.print(ville.getNom() + " ");
      }
    }
    System.out.println();
  }

  public int getNombreVilles() {
    return this.nombreVilles;
  }

  public void ajouterZoneRecharge(Ville ville) {
    if (!ville.aZoneRecharge()) {
      ville.setZoneRecharge(true);
      System.out.println("La zone de recharge a été ajoutée à la ville " + ville.getNom());
    } else {
      System.out.println("Cette ville posséde déjà une zone de recharge");
    }
  }

  public void supprimerZoneRecharge(Ville ville) {
    if (ville.aZoneRecharge()) {
      // Vérifier si des villes voisines ont encore une zone de recharge
      List<Ville> villesVoisines = getVillesAdjacentes(ville);

      boolean voisinsAvecZone = false;

      for (Ville voisine : villesVoisines) {
        if (voisine.aZoneRecharge()) {
          voisinsAvecZone = true;
          break;
        }
      }

      if (voisinsAvecZone) {
        ville.setZoneRecharge(false);
        System.out.println("La zone de recharge a été supprimée de la ville " + ville.getNom());
      } else {
        System.out.println(
            "Vous ne pouvez pas supprimer une zone de recharge si les villes voisines n'ont pas de zone ou n'ont accès à aucun zone dans les villes voisines.");
      }
    } else {
      System.out.println("Vous ne pouvez supprimer une zone de recharge qui n'existe pas");
    }
  }

  public void menuAjouterSupprimerRoute() {
    int choix;
    do {
      System.out.println("1. Ajouter une route");
      System.out.println("2. Supprimer une route");
      System.out.println("3. Quitter");
      System.out.print("Votre choix : ");
      choix = scanner.nextInt();
      scanner.nextLine();

      if (choix == 1 || choix == 2) {
        afficherVilles();
        System.out.println("Vous allez rajouter ou supprimer une route entre deux villes. ");

        System.out.println("Entrez le nom de la première ville : ");
        String nomVille1 = scanner.nextLine();
        nomVille1 = nomVille1.toUpperCase();
        System.out.println("Entrez le nom de la deuxième ville : ");
        String nomVille2 = scanner.nextLine();
        nomVille2 = nomVille2.toUpperCase();

        Ville v1 = null;
        Ville v2 = null;

        for (Ville ville : getVilles()) {
          if (ville.getNom().equals(nomVille1)) {
            System.out.println("Ville 1 trouvée");
            v1 = ville;
          }
          if (ville.getNom().equals(nomVille2)) {
            System.out.println("Ville 2 trouvée");
            v2 = ville;
          }
        } // une exception a gere si les villes ne sont pas trouvees

        if (choix == 1) {
          ajouterRoute(v1, v2);
          System.out.println("====================================");
          System.out.println("Route ajoutée");
          System.out.println("====================================");
        } else if (choix == 2) {
          supprimerRoute(v1, v2);
          System.out.println("====================================");
          System.out.println("Route supprimée");
          System.out.println("====================================");
        }
      } else if (choix == 3) {
        // sc.close();
        System.out.println("***************************************************");
        System.out.println();
        System.out.println("Passage au menu suivant: ");

        return;
      } else {
        System.out.println("Choix non-valable");
      }

    } while (true);

  }

  public void menuAjouterSupprimerZoneRecharge() {
    int choix;
    do {
      System.out.println("1. Ajouter une zone de recharge");
      System.out.println("2. Supprimer une zone de recharge");
      System.out.println("3. Quitter");
      System.out.print("Entrez votre choix : ");
      choix = scanner.nextInt();
      scanner.nextLine();
      switch (choix) {
        case 1:
          System.out.println("Entrez le nom de la ville : ");
          String nomVille = scanner.nextLine();
          nomVille = nomVille.toUpperCase();

          Ville villeAj = null;

          for (Ville v : getVilles()) {
            if (v.getNom().equals(nomVille)) {
              System.out.println("Ville trouvée");
              villeAj = v;
            }

          } // une exception a gere si les villes ne sont pas trouvees
          ajouterZoneRecharge(villeAj);
          break;
        case 2:
          System.out.println("Entrez le nom de la ville : ");
          nomVille = scanner.nextLine();
          nomVille = nomVille.toUpperCase();

          Ville villeSupp = null;

          for (Ville v : getVilles()) {
            if (v.getNom().equals(nomVille)) {
              System.out.println("Ville trouvée");
              villeSupp = v;
              break; // Sortir de la boucle une fois que la ville est trouvée
            }

          } // une exception a gérer si les villes ne sont pas trouvees
          supprimerZoneRecharge(villeSupp);
          break;
        case 3:
          System.out.println("Le menu a été quitté avec succès ");
          break;
        default:
          System.out.println("Choix invalide");
          break;
      }
    } while (choix != 3);

  }

  public void closeScanner() {
    if (scanner != null) {
      scanner.close();
    }
  }

}
