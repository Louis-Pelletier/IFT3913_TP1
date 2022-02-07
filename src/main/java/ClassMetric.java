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
    public void computeAllMetric() {
        computeLoc();
        computeCloc();
        computeDc();
        computeWmc();
        computeBc();
    }

    /**
     *
     */
    protected void computeLoc() {

=======
        int lines = 0;
        String line;

        try{
            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            while((line = br.readLine()) != null){
                if(!line.equals("")){
                    lines++;
                }
            }
            cloc = lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
=======
    protected void computeLoc() {

>>>>>>> main
>>>>>>> Stashed changes
    }

    /**
     *
     */
    protected void computeCloc() {

=======
        File file = new File(filePath);
        int lines = 0;
        String line;
        int state = STATE_NON_IMBRIQUE;      // État qui indique s'il s'agit d'un commentaire imbriqué
        int counter = 0;    // Compteur pour les commentaires imbiqués

        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            while((line = br.readLine()) != null){
                if(state == 1){
                    counter++;
                }
                if(line.contains("//")){
                    System.out.println(line);
                    lines++;
                    counter = 0;
                }
                else if(line.contains("/*") || line.contains("/**")){
                    System.out.println(line);
                    lines++;
                    state = STATE_IMBRIQUE;
                }
                else if(line.contains("*/")){
                    System.out.println(line);
                    lines+= counter;
                    counter = 0;
                    state = STATE_NON_IMBRIQUE;
                }
            }
            cloc = lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
=======
    protected void computeCloc() {

>>>>>>> main
>>>>>>> Stashed changes
    }

    /**
     *
     */
    protected void computeDc() {

=======
        dc = cloc/loc;
=======
    protected void computeDc() {

>>>>>>> main
>>>>>>> Stashed changes
    }

    /**
     *
     */
    protected void computeWmc() {

    }

    /**
     *
     */
    protected void computeBc() {

=======
        bc = dc/wmc;
=======
    protected void computeBc() {

>>>>>>> main
>>>>>>> Stashed changes
    }

    /**
     *
     */
    public void writeInFile() {
        ClassFile classFile = ClassFile.getInstance();
        classFile.add(filePath + ", " + getName() + ", " + loc + ", " + cloc + ", " + dc + ", " + wmc + ", " + bc);
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
