public class Projectile extends Canon{
    private double pointX;
    private double pointY;
    private double vitesseX;
    private double vitesseY;
    private int vieProjectile;

    public Projectile(){

    }

    public Projectile(double pointX, double pointY,double vitesseX,double vitesseY, int vieProjectile){
        super(pointX,pointY,vitesseX,vitesseY,vieProjectile);
    }

    @Override
    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    @Override
    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    @Override
    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    @Override
    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public void setVieProjectile(int vieProjectile) {
        this.vieProjectile = vieProjectile;
    }

    @Override
    public double getPointX() {
        return pointX;
    }

    @Override
    public double getPointY() {
        return pointY;
    }

    @Override
    public double getVitesseX() {
        return vitesseX;
    }

    @Override
    public double getVitesseY() {
        return vitesseY;
    }

    @Override
    public int getVieProjectile() {
        return vieProjectile;
    }

    @Override
    public String toString() {
        return "Projectile{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                ", vitesseX=" + vitesseX +
                ", vitesseY=" + vitesseY +
                ", vieProjectile=" + vieProjectile +
                "} ";
    }
}
