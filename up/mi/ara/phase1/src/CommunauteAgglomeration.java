package up.mi.ara.phase1.src;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

// Classe représentant une communauté d'agglomération
public class CommunauteAgglomeration {
  // private final NOMBRES_VILLES = 26;
  // Nombre de villes dans la communauté d'agglomération
  private int nombreVilles;
  // Map associant à chaque ville une liste de villes adjacentes
  private Map<Ville, List<Ville>> mapVilles;

  // Constructeur de la classe
  public CommunauteAgglomeration(int nombreVilles) {
    this.nombreVilles = nombreVilles;
    this.mapVilles = new HashMap<Ville, List<Ville>>();
  }

  // Méthode pour ajouter une ville à la communauté d'agglomération
  public void ajouterVille(Ville ville) {
    this.mapVilles.put(ville, new ArrayList<Ville>());
  }

  // Méthode pour ajouter une route entre deux villes
  public void ajouterRoute(Ville ville1, Ville ville2) {
    if (this.mapVilles.get(ville1).contains(ville2) || this.mapVilles.get(ville2).contains(ville1)) {
      System.out.println("Il y a déjà une route entre ces deux villes.");
    } else {
      this.mapVilles.get(ville1).add(ville2);
      this.mapVilles.get(ville2).add(ville1);
      System.out.println("Route ajoutée entre " + ville1.getNom() + " et " + ville2.getNom() + ".");
    }
  }

  // Méthode pour supprimer une route entre deux villes
  public void supprimerRoute(Ville ville1, Ville ville2) {
    if (this.mapVilles.get(ville1).contains(ville2) || this.mapVilles.get(ville2).contains(ville1)) {
      this.mapVilles.get(ville1).remove(ville2);
      this.mapVilles.get(ville2).remove(ville1);
      System.out.println("Route supprimée entre " + ville1.getNom() + " et " + ville2.getNom() + ".");
    } else {
      System.out.println("Il n'y a pas de route entre ces deux villes.");
    }
  }

  // Méthode pour obtenir la liste des villes adjacentes à une ville donnée
  public List<Ville> getVillesAdjacentes(Ville ville) {
    return this.mapVilles.get(ville);
  }

  // Méthode pour obtenir la liste des villes de la communauté d'agglomération
  public List<Ville> getVilles() {
    return new ArrayList<Ville>(this.mapVilles.keySet());
  }

  // Méthode pour afficher la liste des villes
  public void afficherVilles() {
    System.out.println("Liste des villes : ");
    for (Ville ville : this.mapVilles.keySet()) {
      System.out.print(ville.getNom() + " ");
    }
  }

  // Méthode pour afficher la liste des routes
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

  // Méthode pour afficher la liste des villes avec une zone de recharge
  public void afficherVillesAvecZoneRecharge() {
    System.out.println("Liste des villes avec zone de recharge : ");
    for (Ville ville : this.mapVilles.keySet()) {
      if (ville.aZoneRecharge()) {
        System.out.print(ville.getNom() + " ");
      }
    }
    System.out.println();
  }

  // Méthode pour obtenir le nombre de villes
  public int getNombreVilles() {
    return this.nombreVilles;
  }

  // Méthode pour ajouter une zone de recharge à une ville
  public void ajouterZoneRecharge(Ville ville) {
    ville.setZoneRecharge(true);
  }

  // Méthode pour supprimer une zone de recharge d'une ville
  public void supprimerZoneRecharge(Ville ville) {
    ville.setZoneRecharge(false);
  }

  // Méthode pour afficher un menu permettant d'ajouter ou de supprimer une route
  public void menuAjouterSupprimerRoute() {
    Scanner sc = new Scanner(System.in);
    int choix;
    do {
      System.out.println();
      System.out.println("1. Ajouter une route");
      System.out.println("2. Supprimer une route");
      System.out.println("3. Quitter");
      System.out.print("Votre choix : ");
      System.out.println();
      choix = sc.nextInt();
      sc.nextLine();

      if (choix == 1 || choix == 2) {
        afficherVilles();
        System.out.println("Vous allez rajouter ou supprimer une route entre deux villes. ");

        System.out.println("Entrez le nom de la première ville : ");
        String nomVille1 = sc.nextLine();
        nomVille1 = nomVille1.toUpperCase();
        System.out.println("Entrez le nom de la deuxième ville : ");
        String nomVille2 = sc.nextLine();
        nomVille2 = nomVille2.toUpperCase();

        Ville v1 = null;
        Ville v2 = null;

        for (Ville ville : getVilles()) {
          if (ville.getNom().equals(nomVille1)) {
            System.out.println("Ville " + ville.getNom() + " trouvée");
            v1 = ville;
          }
          if (ville.getNom().equals(nomVille2)) {
            System.out.println("Ville " + ville.getNom() + " trouvée");
            v2 = ville;
          }
        } // une exception a gere si les villes ne sont pas trouvees

        if (choix == 1) {
          ajouterRoute(v1, v2);
        } else if (choix == 2) {
          supprimerRoute(v1, v2);
        }
      } else if (choix == 3) {
        // sc.close();
        System.out.println();
        System.out.println("Passage au menu suivant: ");
        return;
      } else {
        System.out.println("Choix non-valable");
      }

    } while (true);

  }

  // Méthode pour afficher un menu permettant d'ajouter ou de supprimer une zone
  // de recharge
  public void menuAjouterSupprimerZoneRecharge() {
    Scanner sc = new Scanner(System.in);
    int choix;
    do {
      System.out.println("1. Ajouter une zone de recharge");
      System.out.println("2. Supprimer une zone de recharge");
      System.out.println("3. Quitter");
      System.out.print("Entrez votre choix : ");
      choix = sc.nextInt();
      sc.nextLine();
      switch (choix) {
        case 1:
          System.out.println("Entrez le nom de la ville : ");
          String nomVille = sc.nextLine();
          nomVille = nomVille.toUpperCase();

          Ville ville = null;

          for (Ville v : getVilles()) {
            if (v.getNom().equals(nomVille)) {
              System.out.println("Ville trouvée");
              ville = v;
            }

          } // une exception a gere si les villes ne sont pas trouvees
          ajouterZoneRecharge(ville);
          System.out.println("====================================");
          System.out.println("La zone de recharge a été ajoutée à la ville " + nomVille);
          System.out.println("====================================");
          break;
        case 2:
          System.out.println("Entrez le nom de la ville : ");
          nomVille = sc.nextLine();
          nomVille = nomVille.toUpperCase();

          ville = null;

          for (Ville v : getVilles()) {
            if (v.getNom().equals(nomVille)) {
              System.out.println("Ville trouvée");
              ville = v;
            }

          } // une exception a gere si les villes ne sont pas trouvees
          System.out.print("Entrez le nom de la ville : ");
          System.out.println();
          nomVille = sc.nextLine();
          nomVille = nomVille.toUpperCase();
          ville = new Ville(nomVille);
          supprimerZoneRecharge(ville);
          System.out.println("====================================");
          System.out.println("La zone de recharge a été supprimée de la ville " + nomVille);
          System.out.println("====================================");
          break;
        case 3:
          System.out.println("Le menu a été quitté avec succès ");
          break;
        default:
          System.out.println("Choix invalide");
          break;
      }
    } while (choix != 3);

    sc.close();
  }

}
