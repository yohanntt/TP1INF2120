import java.util.ArrayList;

public  class Vecteur {
    protected double pointX;
    protected double pointY;

    public Vecteur(){

    }
    public Vecteur(double pointX, double pointY){
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public double x(){
        return pointX;
    }

    public double y(){
        return pointY;
    }

   /** public static ArrayList<Projectile> calculeMouvementProjectile(ArrayList<Projectile> projectilesABouger){

    } **/

    public static Projectile additionParVitesse(Projectile projectileACalculerPosition,Univers univers){
        double pointX = projectileACalculerPosition.getPointX();
        double pointY = projectileACalculerPosition.getPointY();
        double vitesseX = projectileACalculerPosition.getVitesseX();
        double vitesseY = projectileACalculerPosition.getVitesseY();


        pointX = pointX + (vitesseX * univers.getDurerUnTic());
        pointY = pointY + (vitesseY * univers.getDurerUnTic());

        projectileACalculerPosition.setPointX(pointX);
        projectileACalculerPosition.setPointY(pointY);

        return projectileACalculerPosition;

    }

    public static Projectile additionVitesseGravite(Projectile projectileAvecVitesse,
                                                        Univers universAvecGravite){
        double vitesseX = projectileAvecVitesse.getVitesseX();
        double vitesseY = projectileAvecVitesse.getVitesseY();

        //changement de la vitesse avec leffet gravite
        vitesseY = vitesseY + (universAvecGravite.getGravite() * universAvecGravite.getDurerUnTic());

        projectileAvecVitesse.setVitesseY(vitesseY);

        return projectileAvecVitesse;

    }

    @Override
    public String toString() {
        return "Vecteur{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                '}';
    }
}
