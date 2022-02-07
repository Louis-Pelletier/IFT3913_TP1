package main.java;

import java.io.File;
import java.util.Arrays;

/**
 *
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
    public void calculateAllMetric() {
        calculateAllClasses();
        calculateDc();
        calculateWcp();
        calculateBc();
        calculateSubPackage();
    }

    private void calculateAllClasses() {

        File file = new File(dir);
        File[] files = file.listFiles();

        for(int i = 0; i < files.length; i++) {

            //Calcul récursive avec les classes (vérif .java)
            if(files[i].isFile() && files[i].getName().split("\\.")[1].equals("java")) {

                isPackage = true;
                ClassMetric classMetric = new ClassMetric(files[i].getPath());
                classMetric.calculateAllMetric();
                classMetric.writeInFile();

                this.loc += classMetric.getLoc();
                this.cloc += classMetric.getCloc();

            }

        }

    }

    /**
     *
     */
    protected void calculateDc() {

    }

    /**
     *
     */
    protected void calculateWcp() {

    }

    /**
     *
     */
    protected void calculateBc() {

    }

    /**
     *
     */
    protected void calculateSubPackage() {

        File file = new File(dir);
        File[] files = file.listFiles();

        for(int i = 0; i < files.length; i++) {

            //Calcul récursif avec les sous-paquets
            if(!files[i].isFile()) {

                PackageMetric packageMetric = new PackageMetric(files[i].getPath(),packageName);
                packageMetric.calculateAllMetric();
                if(packageMetric.getIsPackage())
                    packageMetric.writeInFile();

            }
        }

    }

    /**
     *
     */
    public void writeInFile() {
        PackageFile packageFile = PackageFile.getInstance();
        packageFile.add(dir + ", " + getName() + ", " + loc + ", " + cloc + ", " + dc);
    }

    /**
     *
     * @return
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
    //endregion

}
