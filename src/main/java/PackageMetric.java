package main.java;

import java.io.File;
import java.util.Arrays;

/**
 * Classe qui calcule les métriques de lignes de code,
 * lignes de code contenant des commentaires, la densité de commentaire,
 * le degré selon lequel une classe est bien commentée, ainsi que le WMC.
 * Le tout pour les paquets.
 */
public class PackageMetric extends Metricable {

    //region Attributs
    private String dir;
    private String packageName;
    private double wcp;
    private Boolean isPackage;
    //endregion

    //region Constructeur
    /**
     *
     * @param dir
     */
    public PackageMetric(String dir, String name) {
        super();
        this.dir = dir;
        this.wcp = 0;
        this.isPackage = false;

        File file = new File(dir);
        packageName = name + "." + file.getName();
    }
    //endregion

    //region Méthodes
    /**
     *
     */
    public void computeAllMetric() {
        computeAllClasses();
        if(isPackage) {
            computeDc();
            computeBc();
        }
        computeSubPackage();
    }

    private void computeAllClasses() {

        File file = new File(dir);
        File[] files = file.listFiles();

        for(int i = 0; i < files.length; i++) {

            //Calcul récursive avec les classes (vérif .java)
            if(files[i].isFile() && files[i].getName().split("\\.")[1].equals("java")) {

                isPackage = true;
                ClassMetric classMetric = new ClassMetric(files[i].getPath());
                classMetric.computeAllMetric();
                classMetric.writeInFile();

                this.loc += classMetric.getLoc();
                this.cloc += classMetric.getCloc();
                this.wcp += classMetric.getWmc();

            }

        }

    }

    /**
     *
     */
    protected void computeDc() {
        dc = (double) cloc/ (double) loc;
    }

    /**
     *
     */
    protected void computeBc() {
        bc = dc/wcp;
    }

    /**
     *
     */
    protected void computeSubPackage() {

        File file = new File(dir);
        File[] files = file.listFiles();

        for(int i = 0; i < files.length; i++) {

            //Calcul récursif avec les sous-paquets
            if(!files[i].isFile()) {

                PackageMetric packageMetric = new PackageMetric(files[i].getPath(),packageName);
                packageMetric.computeAllMetric();
                if(packageMetric.getIsPackage())
                    packageMetric.writeInFile();

                this.wcp += packageMetric.getWcp();

            }
        }

    }

    /**
     * méthode qui écrit une ligne contenant toutes les informations que cette classe calcule au début du fichier
     * Chemin + nom de la classe + loc + cloc + dc + wmc + bc.
     */
    public void writeInFile() {
        PackageFile packageFile = PackageFile.getInstance();
        packageFile.add(getPath("src",dir) + ", " + getName() + ", " + loc + ", " +
                cloc + ", " + dc + ", " + wcp + ", " + bc);
    }

    /**
     * Méthode pour obtenir le nom d'un fichier.
     * @return le nom du fichier.
     */
    protected String getName() {

        String newPackageName;

        if(packageName.contains("src.")) {   //Recherche de src dans le nom du paquet

            int index = packageName.lastIndexOf("src.");
            newPackageName = packageName.substring(index + 4);
            return newPackageName;

        } else if(packageName.charAt(0) == '.') {  //On s'assure de ne pas commencer par '.'

            newPackageName = packageName.substring(1);
            return newPackageName;

        }

        return packageName;

    }
    //endregion

    //region Getter
    public Boolean getIsPackage() {
        return isPackage;
    }

    public double getWcp() {
        return wcp;
    }
    //endregion

}
