import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;


public class MyStructure  {
    // the statement
    String first;
    // the name of file
    String second;
    // the date
    FileTime third;
    Long fourth;

    public MyStructure(String first, String second, FileTime third, Long fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    @Override
    public String toString() {
        Date lastModifiedDate = new Date(this.third.toMillis());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest"));
        String formattedDate = df.format(lastModifiedDate);
        return "STATEMENT: " + this.first + "     NAME FILE: " + this.second + "     DATE: " + formattedDate + "    DIMENSION: " + fourth +"\n";
    }
}

class MyStructureComparator implements Comparator <MyStructure> {
    // method to sorting in ascending order by date
    public int compare(MyStructure t1, MyStructure t2) {
        if (t1.third.toMillis() < t2.third.toMillis())
            return -1;
        else if (t1.third.toMillis() > t2.third.toMillis())
            return 1;
        else
            return 0;
    }

}


