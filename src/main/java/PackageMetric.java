package main.java;

import java.io.File;

/**
 *
 */
public class PackageMetric extends Metricable {

    //region Attributs
    private String dir;
    private double wcp;
    private Boolean isPackage;
    //endregion

    //region Constructeur
    /**
     *
     * @param dir
     */
    public PackageMetric(String dir) {
        super();
        this.dir = dir;
        this.wcp = 0;
        this.isPackage = false;
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

                PackageMetric packageMetric = new PackageMetric(files[i].getPath());
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

        File file = new File(dir);
        String name = file.getName();
        return name;

    }
    //endregion

    //region Getter
    public Boolean getIsPackage() {
        return isPackage;
    }
    //endregion

}
