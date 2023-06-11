import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Simulation {
    public static final String  ERREUR_FICHIER = "Erreur dans la lecture du fichier";
    public static final String FICHIER_INTROUVABLE = "Le fichier n'a pas été trouvé";
    public static final String ERREUR_FORMAT_FICHIER = "Erreur dans le format du fichier";
    public static void main(String[] args) {
        ConstructionSimulation("univers.txt");
    }

    public static void ConstructionSimulation(String cheminFichier){
        ArrayList<String> listeContenuFichier = null;

        try {
             listeContenuFichier = lectureFichier(cheminFichier);
        }catch (FileNotFoundException fichierNonTrouver){
            System.err.println(FICHIER_INTROUVABLE);
            System.exit(-1);

        }catch (IOException erreurLecture){
            System.err.println(ERREUR_FICHIER);
            System.exit(-1);
        }

        validationDesCanon(listeContenuFichier);





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
            //Parcours chaque ligne du fichier
            while (lecteurFichier.hasNextLine()){
                ligneFichier = lecteurFichier.nextLine();
                contenuDuFichier.add(ligneFichier);
            }


        return contenuDuFichier;
    }


    
    public static ArrayList<String[]>  validationDesCanon(ArrayList<String> listeContenuFichier){
        boolean canonValide = true;
        ArrayList<String[]> valeursChaqueCanon = new ArrayList<>();
        String[] valeursCanon  = new String[6];

        //index ou les valeurs des canons commence.
        int i = 3;

        while (i < listeContenuFichier.size() && canonValide){
          //Separe chaque valeurs des canons et les mets dans un tableau
          valeursCanon = listeContenuFichier.get(i).split("\\s+");
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





}