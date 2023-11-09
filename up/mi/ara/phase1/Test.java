package up.mi.ara.phase1;

import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    int nombreVilles;
    Scanner sc = new Scanner(System.in);

    do {
      System.out.print("Entrez le nombre de villes : ");
      nombreVilles = sc.nextInt();
    } while (nombreVilles < 1 || nombreVilles > 26);

    CommunauteAgglomeration communauteA = new CommunauteAgglomeration(nombreVilles);

    for (int i = 0; i < nombreVilles; i++) {
      Ville ville = new Ville("" + (char) ('A' + i));
      communauteA.ajouterVille(ville);
    }

    communauteA.afficherVilles();
    menuAjouterRoute(communauteA);
    sc.close();

  }

  public static void menuAjouterRoute(CommunauteAgglomeration ca) {
    Scanner sc = new Scanner(System.in);
    int choix;
    do {
      System.out.println("1. Ajouter une route");
      System.out.println("2. Supprimer une route");
      System.out.println("3. Quitter");
      System.out.print("Votre choix : ");

      choix = sc.nextInt();
    } while (choix < 1 || choix > 3);

    ca.afficherVilles();
    System.out.println("Vous allez rajouter ou supprimer une route entre deux villes. ");

    Scanner sc2 = new Scanner(System.in);
    System.out.println("Entrez le nom de la première ville : ");
    String nomVille1 = sc2.nextLine();
    System.out.println("Entrez le nom de la deuxième ville : ");
    String nomVille2 = sc2.nextLine();

    Ville v1 = null;
    Ville v2 = null;

    for (Ville ville : ca.getVilles()) {
      if (ville.getNom().equals(nomVille1)) {
        System.out.println("Ville 1 trouvée");
        v1 = ville;
      }
      if (ville.getNom().equals(nomVille2)) {
        System.out.println("Ville 2 trouvée");
        v2 = ville;
      }
    } // une exception a gere si les villes ne sont pas trouvees

    switch (choix) {
      case 1:
        ca.ajouterRoute(v1, v2);
        break;
      case 2:
        ca.supprimerRoute(v1, v2);
        break;
      case 3:
        System.exit(0);
        break;
    }

    sc.close();
    sc2.close();

  }

}
