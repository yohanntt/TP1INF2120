import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Simulation {
    public static final String  ERREUR_FICHIER = "Erreur dans la lecture du fichier";
    public static final String FICHIER_INTROUVABLE = "Le fichier n'a pas été trouvé";
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

        System.out.println(listeContenuFichier);
        double graviteAcceleration = Double.parseDouble(listeContenuFichier.get(0));



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


    /* try {
            BufferedReader lecteur = new BufferedReader(new InputStreamReader(new FileInputStream(cheminFichier)
                    , "UTF-8"));
            String ligneDuFichier;
            while ((ligneDuFichier = lecteur.readLine()) != null){
                contenuDuFichier.add(ligneDuFichier);
            }
            lecteur.close();
        }catch (FileNotFoundException erreurLecture){
            System.err.println("Erreur dans la lecture du fichier");
        }catch (IOException erreurInputOutput){
            System.err.println("Erreur d'input ou d'output");
        } */

}