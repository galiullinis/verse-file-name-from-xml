import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Directory {
    private String directory = "files/";

    public Directory(){};
    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public static List<String> readDirectory(String path){
        try {
            File dir = new File(path);
            File []arrFiles = dir.listFiles();
            List<File> lst = Arrays.asList(arrFiles);
            List<String> fileNames = new ArrayList<>();
            for (File file : lst) {
                if (file.getName().endsWith(".xml")) {
                    fileNames.add(path + "\\" + file.getName());
                }
            }
            if (fileNames.size() == 0) {
                System.out.println("In the directory '" + path + "' no xml files founded!");
                throw new FileNotFoundException();
            };
            return fileNames;
        } catch (Exception e){
            System.out.println("Wrong directory! Please, try again..");
            System.exit(0);
            return null;
        }
    }
}
