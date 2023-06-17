import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Cette classe effectue la simualation complète d'un Univers
 *
 * @author Yohann Thoby-Timothée - Code permanent : THOY22099901
 * @author Mialy Anderson Rakotondradano - Code permanent : RAKM80300506
 * @version 1.0
 */
public class Simulation {
    public static final String  ERREUR_FICHIER = "Erreur dans la lecture du fichier";
    public static final String FICHIER_INTROUVABLE = "Le fichier n'a pas été trouvé";
    public static final String ERREUR_FORMAT_FICHIER = "Erreur dans le format du fichier";
    public static final String ERR_VALEUR_CANON = "Erreur dans le type de donné des valeurs de canon";
    public static final String ERR_VALEUR_UNI = "Erreur dans le type de donné des valeurs de l'univers";


    public static ArrayList<ArrayList<Vecteur>>  simuler(String cheminFichier){
        ArrayList<String> listeContenuFichier = null;
        ArrayList<Double> valeursUnivers = new ArrayList<>();
        ArrayList<Projectile> listeProjectileOfficiel = new ArrayList<>();
        ArrayList<Vecteur> rapportpourUnTic = null;
        double gravite = 0.0;
        int nombreTicASimuler = 0;
        double durerUnTic = 0 ;
        int compteurChargement = 1;



       /** try {
            listeContenuFichier = lectureFichier(cheminFichier);
        }catch (FileNotFoundException fichierNonTrouver){
            System.err.println(FICHIER_INTROUVABLE);
            System.exit(-1);

        }catch (IOException erreurLecture){
            System.err.println(ERREUR_FICHIER);
            System.exit(-1);
        }
        valeursUnivers = affectationValeurUniversDouble(listeContenuFichier);
        //validation de l'accélération de la gravité
        String [] tabGravite = listeContenuFichier.get(0).split(" ");
        if (tabGravite.length == 2) {
            gravite = Double.parseDouble(tabGravite[1]);
        } else {
            gravite = Double.parseDouble(tabGravite[0]);
        }

//validation de la durée d'un tic
        try{
            durerUnTic = valeursUnivers.get(1);
            if (durerUnTic < 0) {
                System.err.println(ERR_VALEUR_UNI);
                System.exit(-1);
            }
        } catch (NumberFormatException erreurNombre) {
            System.err.println(ERR_VALEUR_UNI);
            System.exit(-1);
        }
        //aucune validation effectuée
        try{
            nombreTicASimuler = affectationDurerTicUnivers(listeContenuFichier);
            if (nombreTicASimuler < 0) {
                System.err.println(ERR_VALEUR_UNI);
                System.exit(-1);
            }
        } catch (NumberFormatException erreurNombre) {
            System.err.println(ERR_VALEUR_UNI);
            System.exit(-1);
        } **/

        try {
            listeContenuFichier = lectureFichier(cheminFichier);
        }catch (FileNotFoundException fichierNonTrouver){
            System.err.println(FICHIER_INTROUVABLE);
            System.exit(-1);

        }catch (IOException erreurLecture){
            System.err.println(ERREUR_FICHIER);
            System.exit(-1);
        }
        valeursUnivers = affectationValeurUniversDouble(listeContenuFichier);
        //validation de l'accélération de la gravité
        String [] tabGravite = listeContenuFichier.get(0).split(" ");
        gravite = valeursUnivers.get(0);

//validation de la durée d'un tic
        try{
            durerUnTic = valeursUnivers.get(1);
            if (durerUnTic < 0) {
                System.err.println(ERR_VALEUR_UNI);
                System.exit(-1);
            }
        } catch (NumberFormatException erreurNombre) {
            System.err.println(ERR_VALEUR_UNI);
            System.exit(-1);
        }
        //aucune validation effectuée
        try{
            nombreTicASimuler = affectationDurerTicUnivers(listeContenuFichier);
            if (nombreTicASimuler < 0) {
                System.err.println(ERR_VALEUR_UNI);
                System.exit(-1);
            }
        } catch (NumberFormatException erreurNombre) {
            System.err.println(ERR_VALEUR_UNI);
            System.exit(-1);
        }


        ArrayList<String[]> canonValider = validationDesCanon(listeContenuFichier);
        //creation de tous les canons du fichier d'entrer converti en double/int
        ArrayList<Canon> valeurCanonConverti = constructionCanon(canonValider);
        Univers univers = new Univers(gravite,durerUnTic,nombreTicASimuler);
        ArrayList<Projectile> lesProjectilesMisAJour = new ArrayList<>();
        ArrayList<ArrayList<Vecteur>> rapportDesVecteurs = new ArrayList<>();


        while (compteurChargement <= univers.getNombreDeTicSimuler()){
            //va parcourir les canons pour verifier s'ils peuvent lancer de nouveau projectile
            ArrayList<Projectile> projectilesAAjouter = traverserObjetCanon(valeurCanonConverti, compteurChargement);
            lesProjectilesMisAJour = ajoutDesProjectiles(projectilesAAjouter,lesProjectilesMisAJour);
            lesProjectilesMisAJour = bougerLesProjectiles(lesProjectilesMisAJour, univers);
            lesProjectilesMisAJour = modificationDeLaVitesseDesProjectiles(lesProjectilesMisAJour,univers);

            rapportpourUnTic = creationDesVecteurs(lesProjectilesMisAJour);
            rapportDesVecteurs.add(rapportpourUnTic);





            compteurChargement++;
        }




        return rapportDesVecteurs;
    }
    /**
     * Cette methode lit le contenu du fichier
     *
     * La methode lit le contenu du fichier et met sont contenu dans un
     * ArrayList
     *
     * @param cheminFichier est le nom/chemin du document en question.
     * @return un ArrayList qui contient le contenu du fichier
     */
    public static ArrayList<String> lectureFichier (String cheminFichier)throws IOException{
        //utilisation d'un ArrayList car la grosseur du fichier est inconnu
        ArrayList<String> contenuDuFichier = new ArrayList<>();
        String ligneFichier = null;

            Scanner lecteurFichier = new Scanner(new File(cheminFichier));
            lecteurFichier.useLocale(Locale.CANADA);
            //Parcours chaque ligne du fichier
            while (lecteurFichier.hasNextLine()){
                ligneFichier = lecteurFichier.nextLine();
                contenuDuFichier.add(ligneFichier);
            }


        return contenuDuFichier;
    }


    /**
     * Cette methode valide les valeurs de chaque canon
     *
     * La methode valide qu'il y a bien 6 valeurs par caanons.
     *
     * @param listeContenuFichier est le ArrayList qui contient chaque ligne de l'univers
     * @return un ArrayList qui contient les valeurs de chaque canons du fichier.
     */
    public static ArrayList<String[]>  validationDesCanon(ArrayList<String> listeContenuFichier){
        boolean canonValide = true;
        ArrayList<String[]> valeursChaqueCanon = new ArrayList<>();
        String[] valeursCanon  = new String[6];

        //index ou les valeurs des canons commence.
        int i = 3;

        while (i < listeContenuFichier.size() && canonValide){
          //Separe chaques valeurs des canons et les mets dans un tableau
          valeursCanon = listeContenuFichier.get(i).split("\\s+");
          //changer code qui examine la longeur de la ligne canon
          if(valeursCanon.length % 6 != 0){
              canonValide = false;
              System.err.println(ERREUR_FORMAT_FICHIER);
              System.exit(-1);
          }else {
              valeursChaqueCanon.add(valeursCanon);
          }

          i++;
        }

        return valeursChaqueCanon;
    }

    /**
     * Cette methode construis chaque objet canon du document
     *
     * La methode instancie chauqe canon et le met dans un ArrayList
     *
     * @param  valeursChaqueCanons est un ArrayList qui contient les valeurs de chaque canon
     * @return un ArrayList qui contient les valeurs de chaques canons convertis en double.
     */
    public static ArrayList<Canon> constructionCanon(ArrayList <String[]> valeursChaqueCanons){
        Canon unCanon = null;
        double pointX = 0.0;
        double pointY = 0.0;
        double vitesseX = 0.0;
        double vitesseY = 0.0;
        int viePorjectile = 0;
        ArrayList<Canon> chaqueCanon = new ArrayList<>();
        int tempsChargement = 0;
        for (int i = 0; i < valeursChaqueCanons.size(); i++){
            String[] tempTableau = valeursChaqueCanons.get(i);
            try {
                pointX = Double.parseDouble(tempTableau[0]);
                pointY = Double.parseDouble(tempTableau[1]);
                vitesseX = Double.parseDouble(tempTableau[2]);
                vitesseY = Double.parseDouble(tempTableau[3]);
            } catch (NumberFormatException erreurNombre) {
                System.err.println(ERR_VALEUR_CANON);
                System.exit(-1);
            }

            try {
                tempsChargement = Integer.parseInt(tempTableau[4]);
                if (tempsChargement < 0) {
                    System.err.println(ERR_VALEUR_CANON);
                    System.exit(-1);
                }
            } catch (NumberFormatException erreurNombre) {
                System.err.println(ERR_VALEUR_CANON);
                System.exit(-1);
            }
            try {
                viePorjectile = Integer.parseInt(tempTableau[5]);
                if (viePorjectile < 0) {
                    System.err.println(ERR_VALEUR_CANON);
                    System.exit(-1);
                }
            } catch (NumberFormatException erreurNombre) {
                System.err.println(ERR_VALEUR_CANON);
                System.exit(-1);
            }
            unCanon = new Canon(pointX,pointY,vitesseX,vitesseY,tempsChargement,viePorjectile);

            chaqueCanon.add(unCanon);
        }
        return chaqueCanon;
    }

    /**
     * Cette methode affecte les valeurs de l'univers

    /**
     * Cette methode affecte les valeurs de l'univers
     *
     *
     * @param  listeContenuFichier est un ArrayList qui contient les valeurs de l'univers
     * @return un ArrayList qui contient les valeurs de l'univers en double
     */
    public static ArrayList<Double> affectationValeurUniversDouble(ArrayList<String> listeContenuFichier){
        double gravite = 0.0;
        double durerUnTic = 0.0;
        ArrayList<Double> valeursUnivers = new ArrayList<>();
        try {
            gravite = Double.parseDouble(listeContenuFichier.get(0));
            durerUnTic = Double.parseDouble(listeContenuFichier.get(1));
        }catch (NumberFormatException erreurFormatNombre){
            System.err.println(ERR_VALEUR_UNI);
            System.exit(-1);
        }

        valeursUnivers.add(gravite);
        valeursUnivers.add(durerUnTic);



        return valeursUnivers;
    }

    /**
     * Cette methode affecte le nombre de tic d'un univers.
     *
     *
     * @param  listeContenuFichier est un ArrayList qui contient les valeurs de l'univers
     * @return un ArrayList qui contient les valeurs de l'univers en int
     */

    public static int affectationDurerTicUnivers(ArrayList<String> listeContenuFichier){
        int nombreTicUnivers = 0;

        try{
            nombreTicUnivers = Integer.parseInt(listeContenuFichier.get(2));
        }catch (NumberFormatException erreurFormatNombre){
            System.out.println(ERR_VALEUR_UNI);
            System.exit(-1);
        }

        return nombreTicUnivers;
    }

    /**
     * Cette methode parcours chaque canon et verifie si un nouveau projectile peut
     * etre lancer
     *
     *
     * @param  listeDeCanon est un ArrayList qui contient chaque objet canon
     * @return un ArrayList qui contient des objets projectiles a etre ajouter
     */

    public static ArrayList<Projectile> traverserObjetCanon(ArrayList<Canon> listeDeCanon, int compteurChargement){
            ArrayList<Projectile> listeProjectile = new ArrayList<>();
            for (int i = 0; i < listeDeCanon.size(); i++){
                Projectile tempProjectile = Canon.manipulationCanon(listeDeCanon.get(i), compteurChargement);
                if (!(tempProjectile == null)){
                    listeProjectile.add(tempProjectile);
                }
            }


        return listeProjectile;
    }

    /**
     * Cette methode va ajouter les nouveaux projectiles lancés dans
     * la liste des projectiles mis A jour
     *
     * @param  lesProjectilesAAjouter  est un ArrayList qui contient les nouveaux projectiles lancés
     * @param lesProjectilesMisAJour   est un ArrayList qui contient tous les projectiles de l'univers
     * @return un ArrayList qui contient des objets projectiles a etre ajouter
     */
    public static ArrayList<Projectile> ajoutDesProjectiles(ArrayList<Projectile> lesProjectilesAAjouter
            , ArrayList<Projectile> lesProjectilesMisAJour){

        if (lesProjectilesAAjouter.size() > 0){
            for (int i = 0; i < lesProjectilesAAjouter.size(); i++){
                lesProjectilesMisAJour.add(lesProjectilesAAjouter.get(i));
            }
        }
        return lesProjectilesMisAJour;
    }
    //faire une methode qui envoie les projectiles un par un, ce faire bouger (additionner la position par la vitesse)
    public static ArrayList<Vecteur> creationDesVecteurs(ArrayList<Projectile> projectileAMettreEnVecteur){
        ArrayList<Vecteur> vecteursCree = new ArrayList<>();
        Vecteur aMettreDansRapport = new Vecteur();

        for (int i = 0; i < projectileAMettreEnVecteur.size(); i++){
            Projectile temporaire = projectileAMettreEnVecteur.get(i);
            aMettreDansRapport = new Vecteur(temporaire.getPointX(), temporaire.getPointY());
            vecteursCree.add(aMettreDansRapport);
        }

        return vecteursCree;
    }

    /**
     * Cette methode va modifier la position de chaque projectile
     *
     * Cette methode s'assure aussi de gérer la vie de chaque projectile.
     * Donc s'assurer que le projectile en question a encore de la vie.
     * Sinon la méthode s'assure d'enlever le projectile de la méthode.
     *
     *
     * @param  projectileABouger  est un ArrayList qui contient les projectiles a changé
     * @param univers   est un objet Uinvers qui contient des valeurs pour la formule de changement de position
     * @return un ArrayList qui contient des objets projectiles avec des projectiles avec leur position modifier
     */
    public static ArrayList<Projectile> bougerLesProjectiles(ArrayList<Projectile> projectileABouger, Univers univers){
        for (int i = 0; i < projectileABouger.size(); i++){
            Projectile temporaire = projectileABouger.get(i);
            int vieProjectile = temporaire.getVieProjectile();

            if (vieProjectile > 0){
                temporaire = Vecteur.additionParVitesse(projectileABouger.get(i), univers);
                projectileABouger.set(i, temporaire);
                projectileABouger.get(i).setVieProjectile(vieProjectile - 1);
            }else {
                projectileABouger.remove(i);
                i--;
            }
        }
        return projectileABouger;
    }

    /**
     * Cette methode va modifier le vecteur de vitesse
     *
     *Le vecteur de vitesse est modifié en additionant la vitesse(X,Y) avec la gravité
     *
     * @param  projectilesVitessAModifier  est un ArrayList qui contient les vecteurs de vitesse a changé
     * @param univers   est un objet Uinvers qui contient des valeurs pour la formule de changement de vitesse
     * @return un ArrayList qui contient des objets projectiles avec des projectiles avec leur position modifier
     */
    public static ArrayList<Projectile> modificationDeLaVitesseDesProjectiles(ArrayList<Projectile>
                                                                                      projectilesVitessAModifier,
                                                                                      Univers univers){
        for (int i = 0; i < projectilesVitessAModifier.size(); i++){
            Projectile temporaire = Vecteur.additionVitesseGravite(projectilesVitessAModifier.get(i), univers);
            //remplace le projectile du ArrayList à la position i par le projectile modifier
            projectilesVitessAModifier.set(i, temporaire);

        }
        return projectilesVitessAModifier;
    }



}