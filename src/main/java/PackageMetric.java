package main.java;

import java.io.File;

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
    private int wcp;
    private Boolean isPackage;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe ClassMetric.
     * Assigne le directory de la classe au paramètre utilisé, assigne 0 au Wcp de la classe.
     * Assigne le packageName à l'aide du paramètre
     * @param dir nom du directory
     * @param name nom de fichier
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
     * Méthode pour executer le loc, cloc et le wcp de toutes les classes dans le directory.
     * S'il s'agit d'un package, elle calcule le Dc et le Bc.
     */
    public void computeAllMetric() {
        computeAllClasses();
        computeSubPackage();
        if(isPackage) {
            computeDc();
            computeBc();
        }
    }
    /**
     * Méthode qui calcule le loc, cloc et wcp de toutes les classes.
     */
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
     * Méthode simple qui calcule la densité de commentaire en divisant CLOC par LOC.
     */
    protected void computeDc() {
        dc = (double) cloc/ (double) loc;
    }

    /**
     * Méthode simple qui calcule le degré selon lequel une classe est bien commentée en divisant Dc par wcp.
     */
    protected void computeBc() {
        bc = dc/ (double) wcp;
    }

    /**
     * Méthode qui calcule les métriques des sous-packets.
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

    public int getWcp() {
        return wcp;
    }
    //endregion

}
