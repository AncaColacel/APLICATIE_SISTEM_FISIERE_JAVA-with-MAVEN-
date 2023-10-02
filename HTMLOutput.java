import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;


public class HTMLOutput {
    public void CreateOutput(List<MyStructure> mylist, String searchword) {
        // a html page if the entered string cannot be found found
        if (mylist.isEmpty() && searchword.equalsIgnoreCase("exit") == false ) {
            Document document = Jsoup.parse("<!DOCTYPE html><html><head><title>Output_HTML</title><style type=\"text/css\">\n" +
                    "</style><h2 style=\"color:black; text-align: center;\">Application OUTPUT</h2></head><body></body></html>");
            // create paragraphs with text and font
            Element p1 = document.createElement("p");
            Element strong = document.createElement("strong");
            strong.attr("style", "font-size: 18px");
            strong.text("The entered string cannot be found. It is not output for create tabel :(");
            p1.appendChild(strong);
            document.body().appendChild(p1);
            // save the output in a html file
            try {
                FileWriter writer = new FileWriter("output.html");
                writer.write(document.outerHtml());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // string is found
        else if (searchword.equalsIgnoreCase("exit") == false) {
            Document document = Jsoup.parse("<!DOCTYPE html><html><head><title>Tabel_HTML</title><style type=\"text/css\">\n" +
                    "   table    { border:ridge 3px black; color:#; }\n" +
                    "   table td, td{ border:inset 1px #000; }\n" +
                    "   table th {background-color: #696969}\n" +
                    "   table td {background-color: #d3d3d3}\n" +
                    "</style>"
                    + "<h2 style=\"color:black; text-align: center; \">TABEL OUTPUT</h2></head><body></body></html>");
            // create paragraphs with text and font
            Element p1 = document.createElement("p");
            Element strong = document.createElement("strong");
            strong.attr("style", "font-size: 18px");
            strong.text("Click the headers buttons to sort the table.");
            p1.appendChild(strong);
            document.body().appendChild(p1);
            Element p2 = document.createElement("p");
            p2.text("First, second and third headers are sorted in ascending/descending direction (A to Z)/(Z to A)\n " +
                    "and the forth header is sorted as the previous ones (but from 1 to 1+n).");
            p2.attr("style", "font-size: 18px");
            document.body().appendChild(p2);
            Element p3 = document.createElement("p");
            p3.text("The first time you click, the sorting direction is ascending.");
            p3.attr("style", "font-size: 18px");
            document.body().appendChild(p3);
            Element p4 = document.createElement("p");
            p4.text("Click again, and the sorting direction will be descending.");
            p4.attr("style", "font-size: 18px");
            document.body().appendChild(p4);
            // create table
            Element table = document.createElement("table");
            table.attr("style", "width:100%");
            table.attr("id", "Table_HTML");
            // create tr tag (table row)
            Element tr_header = document.createElement("tr");
            // create th (table header)
            Element th1 = document.createElement("th");
            // add atribute for th
            th1.attr("onclick", "sortTableSTRING(0)");
            //create button for th1
            Element button_th1 = document.createElement("button");
            button_th1.text("STATEMENT");
            th1.appendChild(button_th1);
            Element th2 = document.createElement("th");
            // add atribute for th
            th2.attr("onclick", "sortTableSTRING(1)");
            //create button for th2
            Element button_th2 = document.createElement("button");
            button_th2.text("NAME FILE");
            th2.appendChild(button_th2);
            Element th3 = document.createElement("th");
            // add atribute for th
            th3.attr("onclick", "sortTableSTRING(2)");
            //create button for th3
            Element button_th3 = document.createElement("button");
            button_th3.text("DATE FILE");
            th3.appendChild(button_th3);
            Element th4 = document.createElement("th");
            // add atribute for th
            th4.attr("onclick", "sortTableNUMBER(3)");
            Element button_th4 = document.createElement("button");
            button_th4.text("DIMENSION FILE");
            th4.appendChild(button_th4);
            // add th to tr
            tr_header.appendChild(th1);
            tr_header.appendChild(th2);
            tr_header.appendChild(th3);
            tr_header.appendChild(th4);
            // add tables child -> tr
            table.appendChild(tr_header);

        /* create Stream from initial list for manipulate the
        elements using foreach function with a lambda expression
         */
            Stream<MyStructure> mystream = mylist.stream();
            mystream.forEach(myStructure -> {
                // create tr tag
                Element tr_cells = document.createElement("tr");
                // create td tag for statement
                Element td_cells_statement = document.createElement("td");
                // add statement for td tag
                td_cells_statement.text(myStructure.first);
                // create td tag for name of file
                Element td_cells_name = document.createElement("td");
                // create a element for hyperlink
                Element a = document.createElement("a");
                a.attr("href", myStructure.second);
                // add name for td tag
                a.text(myStructure.second);
                td_cells_name.appendChild(a);
                // create td tag for date of file
                Element td_cells_date = document.createElement("td");
                // format the date
                Date lastModifiedDate = new Date(myStructure.third.toMillis());
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest"));
                String formattedDate = df.format(lastModifiedDate);
                td_cells_date.text(String.valueOf(formattedDate));
                // create td tag for len
                Element td_cells_len = document.createElement("td");
                // add len for td tag
                td_cells_len.text(String.valueOf(myStructure.fourth));
                tr_cells.appendChild(td_cells_statement);
                tr_cells.appendChild(td_cells_name);
                tr_cells.appendChild(td_cells_date);
                tr_cells.appendChild(td_cells_len);
                table.appendChild(tr_cells);
            });

            // add documents child -> table
            document.body().appendChild(table);
            // create script tag
            Element script = document.createElement("script");
            document.body().appendChild(script);
        /* sorting function in JavaScript
        the script sorts the String from table (statements, name and date)
        sorting in ascending & descedending order
         */
            script.text("function sortTableSTRING(n) {\n" +
                    "  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;\n" +
                    "  table = document.getElementById(\"Table_HTML\");\n" +
                    "  switching = true;\n" +
                    "  dir = \"asc\";\n" +
                    "  while (switching) {\n" +
                    "    switching = false;\n" +
                    "    rows = table.rows;\n" +
                    "    for (i = 1; i < (rows.length - 1); i++) {\n" +
                    "      shouldSwitch = false;\n" +
                    "      x = rows[i].getElementsByTagName(\"TD\")[n];\n" +
                    "      y = rows[i + 1].getElementsByTagName(\"TD\")[n];\n" +
                    "      if (dir == \"asc\") {\n" +
                    "        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {\n" +
                    "          shouldSwitch = true;\n" +
                    "          break;\n" +
                    "        }\n" +
                    "      } else if (dir == \"desc\") {\n" +
                    "        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {\n" +
                    "          shouldSwitch = true;\n" +
                    "          break;\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "    if (shouldSwitch) {\n" +
                    "      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);\n" +
                    "      switching = true;\n" +
                    "      switchcount ++;\n" +
                    "    } else {\n" +
                    "      if (switchcount == 0 && dir == \"asc\") {\n" +
                    "        dir = \"desc\";\n" +
                    "        switching = true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "};\n" +
                    "function sortTableNUMBER(n) {\n" +
                    "  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;\n" +
                    "  table = document.getElementById(\"Table_HTML\");\n" +
                    "  switching = true;\n" +
                    "  dir = \"asc\";\n" +
                    "  while (switching) {\n" +
                    "    switching = false;\n" +
                    "    rows = table.rows;\n" +
                    "    for (i = 1; i < (rows.length - 1); i++) {\n" +
                    "      shouldSwitch = false;\n" +
                    "      x = rows[i].getElementsByTagName(\"TD\")[n];\n" +
                    "      y = rows[i + 1].getElementsByTagName(\"TD\")[n];\n" +
                    "      if (dir == \"asc\") {\n" +
                    "        if (Number(x.innerHTML) > Number(y.innerHTML)) {\n" +
                    "          shouldSwitch = true;\n" +
                    "          break;\n" +
                    "        }\n" +
                    "      } else if (dir == \"desc\") {\n" +
                    "        if (Number(x.innerHTML) < Number(y.innerHTML)) {\n" +
                    "          shouldSwitch = true;\n" +
                    "          break;\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "    if (shouldSwitch) {\n" +
                    "      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);\n" +
                    "      switching = true;\n" +
                    "      switchcount ++;\n" +
                    "    } else {\n" +
                    "      if (switchcount == 0 && dir == \"asc\") {\n" +
                    "        dir = \"desc\";\n" +
                    "        switching = true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}");

            // save the output in a html file
            try {
                FileWriter writer = new FileWriter("output.html");
                writer.write(document.outerHtml());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // a html page if the entered command is exit
        else if (searchword.equalsIgnoreCase("exit")) {
            Document document = Jsoup.parse("<!DOCTYPE html><html><head><title>Output_HTML</title><style type=\"text/css\">\n" +
                    "</style><h2 style=\"color:black; text-align: center;\">Application OUTPUT</h2></head><body></body></html>");
            // create paragraphs with text and font
            Element p1 = document.createElement("p");
            Element strong = document.createElement("strong");
            strong.attr("style", "font-size: 18px");
            strong.text("The entered command is EXIT. It is not output for create tabel :(");
            p1.appendChild(strong);
            document.body().appendChild(p1);
            // save the output in a html file
            try {
                FileWriter writer = new FileWriter("output.html");
                writer.write(document.outerHtml());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
