package main.java;

import java.io.File;

public class Main {

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
