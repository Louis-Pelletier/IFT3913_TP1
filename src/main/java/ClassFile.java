package main.java;

/**
 *
 */
public class ClassFile {

    //Instance unique de ClassFile
    private static ClassFile singleInstance = null;

    /**
     *
     */
    private ClassFile() {

    }

    /**
     *
     * @return
     */
    public static ClassFile getInstance() {
        if(singleInstance == null)
            singleInstance = new ClassFile();

        return singleInstance;
    }

    /**
     *
     * @param s
     */
    public static void add(String s) {

    }

}
