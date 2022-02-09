package main.java;

import java.io.File;

/**
 *
 */
abstract public class Metricable {

    //region Attributs
    protected int loc;
    protected int cloc;
    protected double dc;
    protected double bc;
    //endregion

    //region Constructeur
    /**
     *
     */
    public Metricable() {
        this.loc = 0;
        this.cloc = 0;
        this.dc = 0;
        this.bc = 0;
    }
    //endregion

    //region Méthodes
    /**
     *
     */
    public abstract void computeAllMetric();

    /**
     *
     */
    protected abstract void computeDc();

    /**
     *
     */
    protected abstract void computeBc();

    /**
     *
     */
    public abstract void writeInFile();

    /**
     *
     * @param root
     * @param filePath
     * @return
     */
    protected String getPath(String root, String filePath) {
        File file = new File(filePath);
        String absolutePath = file.getAbsolutePath();
        int srcPosition = absolutePath.lastIndexOf(root);
        String relativePath = absolutePath.substring(srcPosition);
        return relativePath;
    }

    /**
     *
     * @return
     */
    protected abstract String getName();
    //endregions

}
