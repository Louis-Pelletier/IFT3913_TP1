package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 */
public class ClassMetric extends Metricable {

    //region Attributs
    private String filePath;
    private double wmc;
    final int STATE_NON_IMBRIQUE = 0;
    final int STATE_IMBRIQUE = 1;
    //endregion

    //region Constructeur
    /**
     *
     * @param filePath
     */
    public ClassMetric(String filePath) {
        super();
        this.filePath = filePath;
        this.wmc = 1;
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

        int lines = 0;
        String line;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                if (!isEmpty(line)) {
                    lines++;
                }
            }
            loc = lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     */
    protected void computeCloc() {

        File file = new File(filePath);
        int lines = 0;
        String line;
        int state = STATE_NON_IMBRIQUE;      // État qui indique s'il s'agit d'un commentaire imbriqué
        int counter = 0;                     // Compteur pour les commentaires imbiqués

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                if (state == 1) {
                    counter++;
                }
                if (line.contains("//")) {
                    lines++;
                    counter = 0;
                } else if (line.contains("/*") || line.contains("/**")) {
                    lines++;
                    state = STATE_IMBRIQUE;
                } else if (line.contains("*/")) {
                    lines += counter;
                    counter = 0;
                    state = STATE_NON_IMBRIQUE;
                }
            }
            cloc = lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     */
    protected void computeDc() {
        dc = (double) cloc / (double) loc;
    }

    /**
     *
     */
    protected void computeWmc() {
        File file = new File(filePath);
        int predicates = 0;
        String line;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
               if(containsPredicate(line))
                   predicates++;


               if(isMethod(line)) {
                   predicates++;
               }


            }
            wmc += predicates;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    protected void computeBc() {
        bc = dc / wmc;
    }
    /**
     *
     */
    public void writeInFile() {
        ClassFile classFile = ClassFile.getInstance();
        classFile.add(getPath("src",filePath) + ", " + getName() + ", " + loc + ", " +
                cloc + ", " + dc + ", " + wmc + ", " + bc);
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

    /**
     *
     * @param line
     */
    private Boolean isEmpty(String line) {
        String noTabLine = line.replace("\t","");
        String noSpaceLine = noTabLine.replace(" ","");

        return noSpaceLine.equals("");

    }

    /**
     *
     * @param line
     * @return
     */
    private Boolean containsPredicate(String line) {
        String noSpaceLine = line.replace(" ","");
        Boolean containsIf = noSpaceLine.contains("if(");
        Boolean containsFor = noSpaceLine.contains("for(");
        Boolean containsWhile = noSpaceLine.contains("while(");
        Boolean containsSwitch = noSpaceLine.contains("switch(");

        return containsIf || containsFor || containsWhile || containsSwitch;
    }

    /**
     *
     * @param line
     * @return
     */
    private Boolean isMethod(String line) {
        if(line.length() < 5)
            return false;

        Boolean oneTab = line.substring(0,4).equals("    ");
        Boolean exactlyOneTab = oneTab && line.charAt(4) != ' ';
        Boolean bracket = line.contains("{");

        return exactlyOneTab && bracket;
    }

    //endregion

    //region Getter
    public int getLoc() {
        return loc;
    }

    public int getCloc() {
        return cloc;
    }

    public double getWmc() {
        return wmc;
    }
    //endregion

}
