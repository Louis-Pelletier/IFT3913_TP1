package main.java;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        //Temporaire, utilisé args[0] quand fonctionnel
        String s = "path";

        File file = new File(s);

        if(file.isFile()) {
            ClassFile classFile = ClassFile.getInstance();
            ClassMetric classMetric = new ClassMetric(s);
            classMetric.calculateAllMetric();
            classMetric.writeInFile();
            classFile.closeWriter();
        } else {
            PackageFile packageFile = PackageFile.getInstance();
            PackageMetric packageMetric = new PackageMetric(s);
            packageMetric.calculateAllMetric();
            if(packageMetric.getIsPackage())
                packageMetric.writeInFile();
            packageFile.closeWriter();
        }

    }
}
