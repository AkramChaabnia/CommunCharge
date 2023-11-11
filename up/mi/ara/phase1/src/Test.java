package up.mi.ara.phase1.src;

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
    System.out.println();
    communauteA.menuAjouterSupprimerRoute();
    System.out.println();
    communauteA.menuAjouterSupprimerZoneRecharge();
    sc.close();

  }

  

}
