package up.mi.ara.phase1.src;

public class Ville {
  private String nom;
  private boolean aZoneRecharge;

  public Ville(String nom) {
    this.nom = nom;
    this.aZoneRecharge = false;
  }

  public String getNom() {
    return nom;
  }

  public boolean aZoneRecharge() {
    return this.aZoneRecharge;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setZoneRecharge(boolean aZoneRecharge) {
    this.aZoneRecharge = aZoneRecharge;
  }
}
