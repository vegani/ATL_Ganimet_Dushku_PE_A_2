import java.io.*;

import java.lang.reflect.AnnotatedArrayType;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.util.stream.Collectors.toList;

/// @Author Ganimet Dushku
/// @Title  ATL1 PE A
public class ReadCSV
    {
        // Dies ist der Pfad zur CSV Datei.
        private static String csvDat = "src/cars.csv";

        // In dieser Methode wird wird die CSV Datei eingelesen und Abfrage der CSV Daten.
        public void workWithCsv() throws IOException {

                InputStream iS = new FileInputStream(new File(csvDat));
                BufferedReader bR = new BufferedReader(new InputStreamReader(iS));

                Function<String, CarObj> mapToCar = (line) ->
                {
                    String[] p = line.split(";");
                    return new CarObj(p[0], p[1], p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4]));
                };


                //  Liste zusammenfassen und den Header überspringen.
                List<CarObj> cars = bR.lines()
                        .skip(1)
                        .map(mapToCar)
                        .limit(20)
                        .collect(toList());


                //Scanner
                Scanner input = new Scanner(System.in);
                System.out.println
                        (
                        "1. Zeige die Top Ten der Liste\n" +
                        "2. Zeige Autos mit hohen Drehmoment\n" +
                        "3. Zeige Automarken mit Sortierung von A-Z\n" +
                        "4. Zeige das schwächste Auto\n\n" +
                        "Eingabe: "
                        );

                    int Auswahl = Integer.parseInt(input.nextLine());


                    // Je nach Auswahl wird entschieden, welcher If ausgelöst wird.
                    if (Auswahl == 1)
                    {
                        // Zeige die 10 besten Autos
                        List bestCars = cars.stream()
                                .limit(10)
                                .map(g -> g.getMarke() + " " + g.getModell() + " " + g.getHubraum() + " " + g.getPS() + " PS " + g.getNM() + " Nm")
                                .collect(toList());

                        bestCars.stream().forEach(System.out::println);

                    }
                    else if (Auswahl == 2)
                    {
                        // Zeige Autos mit über 600 Nm oder 600 Nm.
                        List fastCars = cars.stream()
                                .filter(carObj -> carObj.getNM() >= 600)
                                .map(g -> g.getModell() + " " + g.getNM() +"Nm")
                                .collect(toList());

                        fastCars.stream().forEach(System.out::println);
                    }
                    else if (Auswahl == 3)
                    {
                        // Sortiere die Autos nach Marke von A-Z
                        List sortedCars = cars.stream()
                                .sorted((g1, g2) -> g1.getMarke().compareTo(g2.getMarke()))
                                .map(g -> g.getMarke() + " " + g.getModell())
                                .collect(toList());
                                sortedCars.stream().forEach(System.out::println);
                    }
                    else if (Auswahl == 4)
                    {
                        // Zeige das Auto mit der niedrigesten Leistung (PS)
                        CarObj weakCar = cars.stream()
                                .min(Comparator.comparing(CarObj::getPS)).get();
                        System.out.println("Auto: " + weakCar.getMarke() + " " + weakCar.getModell() + " " + weakCar.getPS()  + " PS " + weakCar.getNM() + " NM");
                    }
                    else
                    {
                        // Wenn keine Auswahl getroffen wird, wird folgende Meldung ausgegeben.
                        System.out.println("Keine Selektion gewählt!");
                    }













        }


    }



