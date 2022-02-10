package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe qui calcule les métriques de lignes de code,
 * lignes de code contenant des commentaires, la densité de commentaire,
 * le degré selon lequel une classe est bien commentée, ainsi que le WMC.
 * Le tout pour les classes.
 * ++
 *
 * @author Louis pelletier & Nick (Mettre ton nom complet)
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
     * Constructeur de la classe ClassMetric.
     * Assigne le filepath de la classe au paramètre utilisé et assigne 1 au Wmc de la classe.
     * @param filePath chemin vers un fichier.
     */
    public ClassMetric(String filePath) {
        super();
        this.filePath = filePath;
        this.wmc = 1;
    }
    //endregion

    //region Méthodes
    /**
     * Méthode pour executer les cinq fonctions de calcule. (Loc, Cloc, Dc, Wmc, Bc).
     */
    public void computeAllMetric() {
        computeLoc();
        computeCloc();
        computeDc();
        computeWmc();
        computeBc();
    }

    /**
     * Méthode permettant de calculer les lignes de code dans un fichier.
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
     * Méthode permettant de calculer les lignes de code comportant
     * des commentaires dans un fichier.
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
     * Méthode simple qui calcule la densité de commentaire en divisant CLOC par LOC.
     */
    protected void computeDc() {
        dc = (double) cloc / (double) loc;
    }

    /**
     * Méthode qui calcule le WMC (Weighted methods per Class).
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
     * Méthode simple qui calucle le degré selon lequel une classe est bien commentée,
     * en divisant Dc par Wmc.
     *
     */
    protected void computeBc() {
        bc = dc / wmc;
    }

    /**
     * Méthode qui écrit une ligne contenant toutes les informations que cette classe calcule au début du fichier
     * Chemin + nom de la classe + loc + cloc + dc + wmc + bc.
     */
    public void writeInFile() {
        ClassFile classFile = ClassFile.getInstance();
        classFile.add(getPath("src",filePath) + ", " + getName() + ", " + loc + ", " +
                cloc + ", " + dc + ", " + wmc + ", " + bc);
    }

    /**
     * Méthode pour obtenir le nom d'un fichier.
     * @return le nom du fichier.
     */
    protected String getName() {
        File file = new File(filePath);
        String nameExt = file.getName();
        String name = nameExt.split("\\.")[0];
        return name;
    }

    /**
     * Méthode indiquant si un string est vide.
     * @param line une ligne d'un fichier
     * @return retourne True la ligne est vide.
     */
    private Boolean isEmpty(String line) {
        String noTabLine = line.replace("\t","");
        String noSpaceLine = noTabLine.replace(" ","");

        return noSpaceLine.equals("");

    }

    /**
     * Méthode qui vérifie si une ligne (dans ce cas ci, une ligne d'un fichier) contient un if, une boucle for, une boucle while, ou un switch.
     * @param line ligne d'un fichier lu.
     * @return Retourne vrai si elle la ligne comporte un des quatre, sinon faux.
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
     * Méthode qui vérifie si une ligne (dans ce cas ci, une ligne d'un fichier) contient une méthode
     * @param line ligne d'un fichier lu.
     * @return Retourne vrai si elle la ligne comporte une méthode, sinon faux.
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
