package main.java;

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
    public abstract void calculateAllMetric();

    /**
     *
     */
    protected abstract void calculateDc();

    /**
     *
     */
    protected abstract void calculateBc();

    /**
     *
     */
    public abstract void writeInFile();

    /**
     *
     * @return
     */
    protected abstract String getName();
    //endregions

}
