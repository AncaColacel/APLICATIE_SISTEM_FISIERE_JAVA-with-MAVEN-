# CERINTA PROIECT
Se da o structura de foldere si subfoldere in care sunt stocate fisiere de un anumit tip sa zicem html, sa se realizeze un program care pe baza unor cuvinte de input sau grupuri de cuvinte sa caute toate propozitiile frazele in care sunt continute si sa le prezinte catre utilizator in ordine cronologica si cu referinta titlul (de pagina html).

***Acest branch contine cea de-a doua versiune a proiectului.***
***Noi cerinte pentru versiunea a doua:***
- sa se creeze un fisier HTML in care sa se retina outputul programului sub forma de tabel, avand ca si coloane: statementul gasit, numele fisierului de provenienta, data fisierului, dimensiunea fisierului. 
- sa se poata sorta tabelul dupa headere.
- sa se creeze pagini pentru celelalte doua cazuri ale aplicatiei:
    1) cand se introduce un string care nu se gaseste in niciun fisier,
    2) cand se introduce comanda "exit".
- sa se creeze un fisier de jurnalizare pentru aplicatie.


***EXPLICATII***
****
1) Pentru creearea fisierului HTML am utilizat o biblioteca externa, JSOUP, care sa ma ajute la crearea tag-urilor de html si la adaugarea de informatii (text, clase, argumente) pentru fiecare tag in parte.
Am creat o clasa numita HTMLOutput care are o metoda, CreateOutput, care primeste ca si parametru lista de elemente pe care sa le introduc in tabel (cele 4 campuri). Am creat un element de tip Document si alte elemente pe care sa le pun pe acesta, paragrafe de text si tabelul.
Am transformat lista in stream pentru o prelucrare mai usoara si cu ajutorul functiei foreach am parcurs lista, am preluat elementele din ea si le-am adaugat ca si continut in tabel in celulele corespunzatoare.
Pentru sortare am creat un tag script, unde am adaugat 2 functii de sortare in JavaScript, una pentru sortare a stringurilor, alta pentru a sortare a numerelor (la coloana de dimensiune). Sortarea se face dupa cele 4 headere ale tabelului, crescator si descrescator, apasand pe butoanele de pe header.
Metoda primeste ca si parametru stringul introdus pentru a se verifica daca s-a introdus sau nu exit. In caz afirmativ se creeaza o pagina de html simpla cu un paragraf explicativ.
Se verifica de asemenea daca lista este goala, ceea ce inseamna ca nu s-a gasit nicio propozitie care sa contina stringul, astfel ca se creeaza o pagina html cu un text explicativ acestei situatii.
2) Pentru fisierul de jurnalizare am utilizat java.util.logging. Am creat un obiect de tip Looging, un obiect FileHandler pentru a retine jurnalizarea intr-un fisier .xml si un obiect Formatter pentru a seta
tipul de format al fisierului. Pentru cele 3 situatii in aplicatia mea (gasirea inputului in vreo propozitie, negasirea inputului si incheierea executiei programului) am setat loggerul pe nivelul INFO si i-am setat un mesaj corspunzator. 

**RULARE PROIECT**
****
Am rulat proiectul cu Maven, pentru a adauga dependinta bibliotecii externe utilizate jsoup in fisierul pom.xml.

Aceasta modalitate este mai eficienta, permitand rularea proiectului din afara IDE-ului,
utilizand doar fisierele incarcate in proiect.

Comenzi folosite:

**-mvn install (pentru crearea fisierului .jar)**

**-java -jar fisier.jar**