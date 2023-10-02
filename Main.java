import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

public class Main {
    public static void main(String[] args) {
        String maindirpath = "C:\\Users\\anca-maria.colacel\\Desktop\\Office_Stuff\\TEME_PRACTICE\\FirstPracticeMaven\\ExempluTask";
        // create file using maindirpath
        File maindir = new File(maindirpath);
        SearchWords print = new SearchWords();
        Scanner myscanner = new Scanner(System.in);
        // creating Logger object
        Logger logger = Logger.getLogger(HTMLOutput.class.getName());
        // disable message printing on the keyboard
        logger.setUseParentHandlers(false);
        // creating FileHndler object
        FileHandler fileHandler;
        List<MyStructure> mylist = new ArrayList<>();
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        try {
            fileHandler = new FileHandler("logfile1.xml");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(simpleFormatter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            System.out.println("Insert String to be found: ");
            // read the input from keyboard
            String searchwords = myscanner.nextLine();
            if (searchwords.equals("exit")) {
                HTMLOutput output = new HTMLOutput();
                output.CreateOutput(mylist, searchwords);
                // setting level to INFO and message for this case
                logger.info( "EXIT PROGRAM!\n");
                break;
            }
            if (!searchwords.equals("exit")) {
                if (maindir.exists() && maindir.isDirectory()) {
                    // arr[] contains the file and subdirectory from maindir
                    File arr[] = maindir.listFiles();
                    print.RecursiveSearch(arr, 0, searchwords, mylist);
                    // check if the word cannot be found in files/directories
                    if (mylist.isEmpty()) {
                        // setting level to INFO and message for this case
                        logger.info("THE SEARCHED WORD CANNOT BE FOUND!\n");
                        HTMLOutput output = new HTMLOutput();
                        output.CreateOutput(mylist, searchwords);
                    }
                    else {
                        // sorting in ascending order by date
                        //Collections.sort(mylist, new MyStructureComparator());
                        // call the output method
                        HTMLOutput output = new HTMLOutput();
                        output.CreateOutput(mylist, searchwords);
                        mylist = new ArrayList<>();
                        // setting level to INFO and message for this case
                        logger.info("STATEMENT IS POSTED!\n");
                    }
                }
            }
        }
    }
}