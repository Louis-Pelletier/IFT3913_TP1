package main.java;

/**
 * Singleton qui permet d'écrire les classes et leurs métriques dans un fichier .csv
 */
public class ClassFile extends Writer {

    //region Attributs
    //Instance unique de ClassFile
    private static ClassFile singleInstance = null;
    //endregion

    //region Constructeur
    /**
     * Constructeur de la classe (privé)
     * Initialise le fichier classes.csv et ajoute la ligne avec les noms de métrique
     */
    private ClassFile() {
        super("classes.csv");
        super.add("chemin, class, classes_LOC, classe_CLOL, classe_DC, WMC, classe_BC");
    }
    //endregion

    //region Méthodes
    /**
     * Méthode qui permet d'obtenir une instance du singleton
     * @return L'instance unique de la classe
     */
    public static ClassFile getInstance() {
        if(singleInstance == null)
            singleInstance = new ClassFile();

        return singleInstance;
    }
    //endregion

}
