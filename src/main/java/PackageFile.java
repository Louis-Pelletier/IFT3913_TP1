package main.java;

/**
 *
 */
public class PackageFile extends Writer {

    //region Attributs
    //Instance unique de PackageFile
    private static PackageFile singleInstance = null;
    //endregion

    //region Constructeur
    /**
     *
     */
    private PackageFile() {
        super("paquets.csv");
        super.add("chemin, paquet, paquet_LOC, paquet_CLOL, paquet_DC");
    }
    //endregion

    //region Méthodes
    /**
     *
     * @return
     */
    public static PackageFile getInstance() {
        if(singleInstance == null)
            singleInstance = new PackageFile();

        return singleInstance;
    }
    //endregion

}
