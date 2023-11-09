package up.mi.ara.phase1;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class CommunauteAgglomeration {
  // private final NOMBRES_VILLES = 26;
  private int nombreVilles;
  private Map<Ville, List<Ville>> mapVilles;

  public CommunauteAgglomeration(int nombreVilles) {
    this.nombreVilles = nombreVilles;
    this.mapVilles = new HashMap<Ville, List<Ville>>();
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
    for (Ville ville : this.mapVilles.keySet()) {
      System.out.print(ville.getNom() + " ");
    }
  }

  public int getNombreVilles() {
    return this.nombreVilles;
  }

  public void ajouterZoneRecharge(Ville ville) {
    ville.setZoneRecharge(true);
  }

  public void supprimerZoneRecharge(Ville ville) {
    ville.setZoneRecharge(false);
  }

}
