package main.java;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        //Temporaire, utilisé args[0] quand fonctionnel
        String s = "src/main/java/Main.java";

        File file = new File(s);

        if(file.isFile()) {
            //File
        } else {
            //Package
        }

    }
}
