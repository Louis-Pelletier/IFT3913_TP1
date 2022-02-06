package main.java;

/**
 *
 */
public class PackageFile {

    //Instance unique de PackageFile
    private static PackageFile singleInstance = null;

    /**
     *
     */
    private PackageFile() {

    }

    /**
     *
     * @return
     */
    public static PackageFile getInstance() {
        if(singleInstance == null)
            singleInstance = new PackageFile();

        return singleInstance;
    }

    /**
     *
     * @param s
     */
    public static void add(String s) {

    }

}
