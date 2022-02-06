package main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
abstract public class Writer {

    //region Attributs
    protected String fileName;
    protected FileWriter fr;
    protected BufferedWriter bw;
    //endregion

    //region Constructeur
    /**
     *
     * @param fileName
     */
    public Writer(String fileName) {
        try {

            this.fileName = fileName;
            this.fr = new FileWriter(fileName);
            this.bw = new BufferedWriter(fr);

        } catch (IOException error) {

            System.out.println("Impossible de créer " + fileName);
            System.out.println("Erreur : " + error);
            System.out.println();

        }
    }
    //endregion

    //region Méthodes
    /**
     *
     * @param s
     */
    public void add(String s) {
        try{

            bw.append(s);
            bw.newLine();

        } catch (IOException error) {

            System.out.println("Impossible d\'ecrire dans " + fileName);
            System.out.println("Erreur : " + error);
            System.out.println();

        }
    }

    /**
     *
     */
    public void closeWriter() {
        try {
            bw.close();
        } catch (IOException error) {
            System.out.println("Impossible de fermer le fichier" + fileName);
            System.out.println("Erreur : " + error);
            System.out.println();
        }
    }
    //endregion

}
