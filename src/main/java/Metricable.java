package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe abstraite regroupant des attributs et méthodes en comment pour calculer les métriques des classes et des paquets
 */
abstract public class Metricable {

    //region Attributs
    protected int loc;
    protected int cloc;
    protected double dc;
    protected double bc;
    String root;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe
     * Initialise tous les attributs à 0
     */
    public Metricable() {
        this.loc = 0;
        this.cloc = 0;
        this.dc = 0;
        this.bc = 0;

        try (InputStream config = new FileInputStream("config.properties")) {

            Properties properties = new Properties();
            properties.load(config);

            root = properties.getProperty("root");

        } catch (IOException error) {
            System.out.println("Fichier config.properties non trouvé. Root va être initialisé à src");
            System.out.println("Erreur : " + error);
            root = "src";
        }
    }
    //endregion

    //region Méthodes
    /**
     * Méthode abstraite permettant de calculer toutes les métriques d'une classe ou d'un paquet
     */
    public abstract void computeAllMetric();

    /**
     * Méthode abstraite permettant de calculer la métrique DC d'une classe ou d'une paquet
     */
    protected abstract void computeDc();

    /**
     * Méthode abstraite permettant de calculer la métrique BC d'une classe ou d'une paquet
     */
    protected abstract void computeBc();

    /**
     * Méthode abstraite permettant d'écrire une ligne dans un fichier
     */
    protected abstract void writeInFile();

    /**
     * Méthode permettant d'obtenir un path relatif à une racine en ayant un path absolu
     * @param root La racine des paths reltifs
     * @param filePath Le path absolu
     * @return Le path relatif à root
     */
    protected String getPath(String filePath) {
        File file = new File(filePath);
        String absolutePath = file.getAbsolutePath();
        int srcPosition = absolutePath.lastIndexOf(root);
        String relativePath = absolutePath.substring(srcPosition);
        return relativePath;
    }

    /**
     * Méthode permettant d'obtenir le nom d'une classe ou d'un paquet
     * @return Le nom d'une classe ou d'un paquet
     */
    protected abstract String getName();
    //endregions

}
