package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe controleur
 */
public class Controller {

    String path;
    String root;

    /**
     * Constructeur de la classe
     * Associe le path en entrée à la variable path
     * @param path Le path en entrée
     */
    public Controller(String path) {
        this.path = path;

        try (InputStream config = new FileInputStream("config.properties")) {

            Properties properties = new Properties();
            properties.load(config);

            root = properties.getProperty("root");

        } catch (IOException error) {
            System.out.println("Fichier config.properties non trouvé. Les chemins affichés seront absolu");
            System.out.println("Erreur : " + error);
            root = "";
        }

    }

    /**
     * Méthode qui calcule toutes les métriques des paquets et classes dans le path d'entrée
     */
    public void computeAllMetric() {



        File file = new File(path);

        if(file.isFile()) {

            ClassFile classFile = ClassFile.getInstance();
            ClassMetric classMetric = new ClassMetric(path, root);
            classMetric.computeAllMetric();
            classMetric.writeInFile();
            classFile.closeWriter();

        } else {

            ClassFile classFile = ClassFile.getInstance();
            PackageFile packageFile = PackageFile.getInstance();
            PackageMetric packageMetric = new PackageMetric(path, root, "");
            packageMetric.computeAllMetric();
            if(packageMetric.getIsPackage())
                packageMetric.writeInFile();
            packageFile.closeWriter();
            classFile.closeWriter();

        }

    }

}
