package main.java;

/**
 *
 */
public class ClassFile extends Writer {

    //region Attributs
    //Instance unique de ClassFile
    private static ClassFile singleInstance = null;
    //endregion

    //region Constructeur
    /**
     *
     */
    private ClassFile() {
        super("classes.csv");
        super.add("chemin, class, classes_LOC, classe_CLOL, classe_DC, WMC, classe_BC");
    }
    //endregion

    //region Méthodes
    /**
     *
     * @return
     */
    public static ClassFile getInstance() {
        if(singleInstance == null)
            singleInstance = new ClassFile();

        return singleInstance;
    }
    //endregion

}
