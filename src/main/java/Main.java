package main.java;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        //Temporaire, utilisé args[0] quand fonctionnel
        String s = "C:\\Users\\nickd\\OneDrive\\Bureau\\jfreechart-master";

        File file = new File(s);

        if(file.exists()) {
            Controller controller = new Controller(s);
            controller.computeAllMetric();
        }

    }
}
