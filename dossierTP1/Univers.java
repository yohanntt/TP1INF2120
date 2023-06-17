/**
 *
 * It stores information such as name, age, and address.
 *
 * @author Your Name
 * @version 1.0
 */
public class Univers {
    private double gravite;
    private double durerUnTic;
    private int nombreDeTicSimuler;

    public Univers(double gravite, double durerUnTic, int nombreDeTicSimuler) {
        this.gravite = gravite;
        this.durerUnTic = durerUnTic;
        this.nombreDeTicSimuler = nombreDeTicSimuler;
    }
    public Univers(){

    }

    public double getGravite() {
        return gravite;
    }

    public double getDurerUnTic() {
        return durerUnTic;
    }

    public int getNombreDeTicSimuler() {
        return nombreDeTicSimuler;
    }
}
