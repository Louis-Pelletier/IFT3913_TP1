package main.java;

import java.io.File;

/**
 *
 */
public class Controller {

    String path;

    /**
     *
     * @param path
     */
    public Controller(String path) {
        this.path = path;
    }

    /**
     * 
     */
    public void computeAllMetric() {

        File file = new File(path);

        if(file.isFile()) {

            ClassFile classFile = ClassFile.getInstance();
            ClassMetric classMetric = new ClassMetric(path);
            classMetric.computeAllMetric();
            classMetric.writeInFile();
            classFile.closeWriter();

        } else {

            ClassFile classFile = ClassFile.getInstance();
            PackageFile packageFile = PackageFile.getInstance();
            PackageMetric packageMetric = new PackageMetric(path,"");
            packageMetric.computeAllMetric();
            if(packageMetric.getIsPackage())
                packageMetric.writeInFile();
            packageFile.closeWriter();
            classFile.closeWriter();

        }

    }

}
