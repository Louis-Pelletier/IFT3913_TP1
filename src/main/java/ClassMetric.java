package main.java;

import java.io.File;

/**
 *
 */
public class ClassMetric extends Metricable {

    //region Attributs
    private String filePath;
    private double wmc;
    //endregion

    //region Constructeur
    /**
     *
     * @param filePath
     */
    public ClassMetric(String filePath) {
        super();
        this.filePath = filePath;
        this.wmc = 0;
    }
    //endregion

    //region Méthodes
    /**
     *
     */
    public void calculateAllMetric() {
        calculateLoc();
        calculateCloc();
        calculateDc();
        calculateWmc();
        calculateBc();
    }

    /**
     *
     */
    protected void calculateLoc() {

    }

    /**
     *
     */
    protected void calculateCloc() {

    }

    /**
     *
     */
    protected void calculateDc() {

    }

    /**
     *
     */
    protected void calculateWmc() {

    }

    /**
     *
     */
    protected void calculateBc() {

    }

    /**
     *
     */
    public void writeInFile() {
        ClassFile classFile = ClassFile.getInstance();
        classFile.add(filePath + ", " + getName() + ", " + loc + ", " + cloc + ", " + dc);
    }

    /**
     *
     * @return
     */
    protected String getName() {

        File file = new File(filePath);
        String nameExt = file.getName();
        String name = nameExt.split("\\.")[0];
        return name;

    }
    //endregion

    //region Getter
    public int getLoc() {
        return loc;
    }

    public int getCloc() {
        return loc;
    }
    //endregion

}
