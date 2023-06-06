import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Simulation {
    public static void main(String[] args) {
        ConstructionSimulation("univers.txt");
    }

    public static void ConstructionSimulation(String cheminFichier){
        ArrayList<String> listeContenuFichier = lectureFichier(cheminFichier);

        double graviteAcceleration = Double.parseDouble(listeContenuFichier.get(0));

    }
    public static ArrayList<String> lectureFichier(String cheminFichier){
        ArrayList<String> contenuDuFichier = new ArrayList<>();
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