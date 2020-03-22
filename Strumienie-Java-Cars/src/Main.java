import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static Set<String> carListData(){
        Set<String> listOfFilename = new HashSet<>();
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        List<String> brand = new LinkedList<>();
        Collections.addAll(brand, "ALFA_ROMEO","AUDI","BMW","CITROEN", "DACIA", "FORD", "HONDA", "JEEP");


        List<String> wheel = new LinkedList<>();
        Collections.addAll(wheel, "Debica","Goldline","Michelin");



        System.out.println("Podaj liczbę plików do wygenerowania: ");
        int how = sc.nextInt();
        for (int i = 0; i <how ; i++) {

            String rndBrand = brand.get(rnd.nextInt(brand.size()));
            double price = rnd.nextInt(100_000) + 50_000;


            double capacity = (rnd.nextInt(26) + 10) / 10.0;
            double combustion = (rnd.nextInt(100) + 40) / 10.0;

            Set<Equipment> eqLists = new HashSet<>();
            int x = rnd.nextInt(6) + 1;
            for (int j = 0; j < x; j++) {
                eqLists.add(Equipment.randomEquipment());
            }


            String rndWheel = wheel.get(rnd.nextInt(wheel.size()));
            int size = rnd.nextInt(7) + 13;


            try {
                FileWriter writer = new FileWriter(rndBrand + "_" + price);
                PrintWriter pw = new PrintWriter(writer);

                pw.println(rndBrand + " " + price);
                pw.println(EngineType.randomTEngineType() + " " + capacity + " " + combustion);
                pw.print(BodyColor.randomTBodyColor() + " " + BodyType.randomBodyType() + " ");
                eqLists.forEach(eq -> pw.print(eq + ";"));
                pw.println();
                pw.println(rndWheel + " " + size + " " + TireType.randomTireType());


                pw.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String filename = rndBrand + "_" + price;
            listOfFilename.add(filename);
        }
        return listOfFilename;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Set<String> listOfFilename = carListData();
        CarShowroom showroom = new CarShowroom(listOfFilename);
        System.out.println("All List:");
        System.out.println(showroom);
        System.out.println("---------------------------------------------------------");

        /*
        System.out.println("Sort by Body Type");
        showroom.sortByBodyTyp().forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        System.out.println("Sort by Color");
        showroom.sortByColor().forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        System.out.println("Sort by Price");
        showroom.sortByPrice().forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        System.out.println("select Body Type and price ");
        showroom.selectByBodyTypAndPrice(BodyType.COMBI,60000,100000).forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        System.out.println("Only name brand car witch select fuel ");
        showroom.selectByFuelType(EngineType.DIESEL).forEach(System.out::println);

        System.out.println("---------------------------------------------------------");
        showroom.selectWithEnterParametrs().forEach(System.out::println);
        */

        System.out.println("--------------------------------------");
        System.out.println("you will get the parameter of the selected field:");
        System.out.println("You can choose:  capacity, price, wheel");
        String name = sc.nextLine();

        DoubleSummaryStatistics dss = showroom.statisticsChoseParametrs(name);
        System.out.println(dss);

        System.out.println("--------------------------------------");
        System.out.println("Car review after: ");
        showroom.sortCarInspections("properties").forEach((k,v)-> System.out.println(k+ " please review after -> " +v ));

        System.out.println("--------------------------------------");
        System.out.println("A list broken down by type of tires: ");
        showroom.wheelTypeList().forEach((k,v)-> System.out.println(k+ " -> " +v ));

    }
}
