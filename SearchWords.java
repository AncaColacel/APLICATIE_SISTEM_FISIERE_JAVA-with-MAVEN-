import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;


public class SearchWords {
    public void RecursiveSearch(File[] arr, int level, String searchwords,  List<MyStructure> mylist)
    {
        String line;
        StringBuilder content = new StringBuilder();
        Boolean intag = false;
        FileReader fr;
        BufferedReader br;
        String name_path;
        FileTime last_time;
        BasicFileAttributes file_att;
        String key = "ExempluTask";
        String final_path = null;
        int index = 0;
        long len = 0;
        for (File f : arr) {
            // check if f is file
            if (f.isFile()) {
                try {
                    fr = new FileReader(f);
                    br = new BufferedReader(fr);
                    // read line by line
                    while ((line = br.readLine()) != null) {
                        // check if the line contains the string
                       if (line.contains(searchwords)) {
                           intag = true;
                       }
                       if (intag) {
                           content.append(line);
                           /*
                            check if the line contains the last part of the tag in html
                            if the line contains the "</", i put the info in triplet,
                            else i will continue to read the next line until i find the
                            last character of the tag
                            */
                           if (line.contains("</")) {
                               intag = false;
                               name_path = f.getPath();
                               index = name_path.indexOf(key);
                               // create local path for hyperlink
                               if (index != -1) {
                                   final_path = name_path.substring(index);
                               }
                               file_att = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
                               last_time = file_att.creationTime();
                               String cnt = new String (content);
                               len = f.length();
                               MyStructure t = new MyStructure(cnt, final_path, last_time, len);
                               mylist.add(t);
                               content = new StringBuilder();
                           }
                       }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (f.isDirectory()) {
                RecursiveSearch(f.listFiles(), level + 1, searchwords, mylist);
            }
        }
    }
}
