package main.java;

import java.io.File;

/**
 * Classe controleur
 */
public class Controller {

    String path;

    /**
     * Constructeur de la classe
     * Associe le path en entrée à la variable path
     * @param path Le path en entrée
     */
    public Controller(String path) {
        this.path = path;
    }

    /**
     * Méthode qui calcule toutes les métriques des paquets et classes dans le path d'entrée
     */
    public void computeAllMetric() {

        File file = new File(path);

        if(file.isFile()) {

            ClassFile classFile = ClassFile.getInstance();
            ClassMetric classMetric = new ClassMetric(path);
            classMetric.computeAllMetric();
            classMetric.writeInFile();
            classFile.closeWriter();

        } else {

            ClassFile classFile = ClassFile.getInstance();
            PackageFile packageFile = PackageFile.getInstance();
            PackageMetric packageMetric = new PackageMetric(path,"");
            packageMetric.computeAllMetric();
            if(packageMetric.getIsPackage())
                packageMetric.writeInFile();
            packageFile.closeWriter();
            classFile.closeWriter();

        }

    }

}
