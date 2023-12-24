package up.mi.ara.terminal;

/**
 * Cette classe représente une ville.
 */
public class Ville {

  // ---------------- Attributs ----------------

  private String nom; // Nom de la ville
  private boolean aZoneRecharge; // Indicateur si la ville a une zone de recharge ou non

  /**
   * Constructeur de la classe Ville.
   * 
   * @param nom Nom de la ville
   */
  public Ville(String nom) {
    this.nom = nom;
    this.aZoneRecharge = false;
  }

  // ---------------- Méthodes ----------------

  /**
   * Méthode pour obtenir le nom de la ville
   * 
   * @return Le nom de la ville
   */
  public String getNom() {
    return nom;
  }

  /**
   * Méthode pour détecter si la ville a une zone de recharge
   * 
   * @return boolean, vrai si la ville a une zone de recharge, faux sinon
   */
  public boolean aZoneRecharge() {
    return this.aZoneRecharge;
  }

  /**
   * Méthode pour définir le nom de la ville
   * 
   * @param nom Nom de la ville
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Méthode pour définir une zone de recharge à la ville ou non
   * 
   * @param aZoneRecharge Indicateur si la ville a une zone de recharge ou non
   */
  public void setZoneRecharge(boolean aZoneRecharge) {
    this.aZoneRecharge = aZoneRecharge;
  }

  /**
   * Méthode toString pour afficher le nom de la ville
   * 
   * @return un String, Le nom de la ville
   */
  @Override
  public String toString() {
    return nom;
  }
}
