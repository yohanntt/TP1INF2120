import java.util.ArrayList;
import java.util.Arrays;

public class Canon  {

    private double pointX;
    private double pointY;
    private double vitesseX;
    private double vitesseY;
    private int vieProjectile;
    private int tempsDeChargement;


    public Canon(){

    }
    public Canon(double pointX, double pointY, double vitesseX,
                 double vitesseY, int tempsDeChargement, int vieProjectile){
        this.pointX = pointX;
        this.pointY = pointY;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.tempsDeChargement = tempsDeChargement;
        this.vieProjectile = vieProjectile;

    }


    public Canon(double pointX, double pointY,double vitesseX,double vitesseY, int vieProjectile){
       this.pointX = pointX;
       this.pointY = pointY;
       this.vitesseX = vitesseX;
       this.vitesseY = vitesseY;
       this.vieProjectile = vieProjectile;

    }

    public double getPointX() {
        return pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public double getVitesseX() {
        return vitesseX;
    }

    public double getVitesseY() {
        return vitesseY;
    }

    public int getTempsDeChargement() {
        return tempsDeChargement;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public void setTempsDeChargement(int tempsDeChargement) {
        this.tempsDeChargement = tempsDeChargement;
    }


    public int getVieProjectile() {
        return vieProjectile;
    }

    /**
     * Cette methode va vérifier le temps de chargement du canon et
     * lancer un nouveau projectile au moment approrié
     *
     *
     *
     * @param  unCanon  est un canon qui va etre vérifier
     * @param  compteurChargement est une valeur a comparer avec le temps de chargement
     * @return un Projectile non null, si le canon est chargé, autrement il renvoie un canon null.
     */
    public static Projectile manipulationCanon(Canon unCanon, int compteurChargement){
        //c'est là que je vais gérer les conditions des canons
        //comme verifier le temps de chargement, lancer de nouveau projectile etc...
        Projectile nouvProjectile = null;
        double pointX = unCanon.getPointX();
        double pointY = unCanon.getPointY();
        double vitesseX = unCanon.getVitesseX();
        double vitesseY = unCanon.getVitesseY();
        int vieProjectile = unCanon.getVieProjectile();


        //changer la condition pour un ticUnivers % tempsCharge == 0
        //créer classe UNIVERS avant
        if (compteurChargement % unCanon.getTempsDeChargement() == 0){
            //creation d'un nouveau projectile
            nouvProjectile = new Projectile();
            nouvProjectile.setPointX(pointX);
            nouvProjectile.setPointY(pointY);
            nouvProjectile.setVitesseX(vitesseX);
            nouvProjectile.setVitesseY(vitesseY);
            nouvProjectile.setVieProjectile(vieProjectile);
        }






        //unCanon.tempsDeChargement = unCanon.tempsDeChargement - 1;
        return nouvProjectile;
    }


    @Override
    public String toString() {
        return "Canon{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                ", vitesseX=" + vitesseX +
                ", vitesseY=" + vitesseY +
                ", vieProjectile=" + vieProjectile +
                ", tempsDeChargement=" + tempsDeChargement +
                ", tempsChargeInitial=" + 
                "} " ;
    }
}
