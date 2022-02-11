package main.java;

import java.io.File;

/**
 * Classe Main
 */
public class Main {

    /**
     * Méthode principal du programme
     * @param args args[0] est un path
     */
    public static void main(String[] args) {

        String s = args[0];

        File file = new File(s);

        if(file.exists()) {
            Controller controller = new Controller(s);
            controller.computeAllMetric();
        } else {
            System.out.println("Not a path");
        }

    }
}
