package main.java;

/**
 * Singleton qui permet d'écrire les paquets et leurs métriques dans un fichier .csv
 */
public class PackageFile extends Writer {

    //region Attributs
    //Instance unique de PackageFile
    private static PackageFile singleInstance = null;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe (privé)
     * Initialise le fichier paquets.csv et ajoute la ligne avec les noms de métrique
     */
    private PackageFile() {
        super("paquets.csv");
        super.add("chemin, paquet, paquet_LOC, paquet_CLOL, paquet_DC, WCP, paquet_BC");
    }
    //endregion

    //region Méthodes
    /**
     * Méthode qui permet d'obtenir une instance du singleton
     * @return L'instance unique de la classe
     */
    public static PackageFile getInstance() {
        if(singleInstance == null)
            singleInstance = new PackageFile();

        return singleInstance;
    }
    //endregion

}
