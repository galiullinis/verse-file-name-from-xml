import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileNameCreater {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        List<String> tags = new ArrayList<>();
        tags.add("author");
        tags.add("title");
        tags.add("text");

        Directory directory = new Directory();
        List<String> fileNames = new ArrayList<>(Directory.readDirectory(directory.getDirectory()));

        Action.createDirectory();

        VerseFile verseFile = new VerseFile();
        XmlRead xmlRead = new XmlRead();
        Map<String, String> content = new HashMap<>();

        String dir = "txt_files/";
        String fileName;
        int count = 0;
        for (String file : fileNames){
            for (String tag : tags){
                xmlRead.setFileName(file);
                xmlRead.setTagName(tag);
                xmlRead.getTextFromTag();
                content.put(tag, xmlRead.data);
            }
            verseFile.setAuthor(content.get("author"));
            verseFile.setTitle(content.get("title"));
            verseFile.setText(content.get("text"));
            verseFile.setFileName(file);

            System.out.println("Original file name: " + verseFile.getFileName());
            fileName = dir + verseFile.getAuthor() + " - " + verseFile.getTitle();
            Action.writeTextToFile(verseFile.getText(), fileName);
            count++;
            System.out.println(" " + count + ". File '" + fileName + "' has been successfully created!");
        }
        System.out.println("Created " + String.valueOf(count) + " files!");
    }
}
