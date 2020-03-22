import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class CarShowroom {
    private Set<Car> ListOfCar = new TreeSet<>(
            Comparator
                    .comparing(Car::getBrand,Comparator.reverseOrder())
                    .thenComparing(Car::getPrice,Comparator.reverseOrder())
    );

    public Set<Car> getListOfCar() {
        return ListOfCar;
    }

    public void setListOfCar(Set<Car> listOfCar) {
        ListOfCar = listOfCar;
    }

    public CarShowroom() {
    }

    public CarShowroom(Set<String> listOfFilename) {
        setListOfCar( listOfFilename
                .stream()
                .map(Car::new)
                .collect(Collectors.toSet()));
    }

    public Set<Car> sortByPrice(){
        return getListOfCar()
                .stream()
                .sorted(Comparator
                        .comparing(Car::getPrice))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Car> sortByColor(){
        return getListOfCar()
                .stream()
                .sorted(Comparator
                        .comparing(x-> x.getBody().getColor().toString()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Car> sortByBodyTyp(){
        return getListOfCar()
                .stream()
                .sorted(Comparator
                        .comparing(x-> x.getBody().getType().toString()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Car> selectByBodyTypAndPrice(BodyType type, int minPrice, int MaxPrice){
        return getListOfCar()
                .stream()
                .filter(x -> x.getBody().getType()==type && x.getPrice()>minPrice && x.getPrice()<MaxPrice)
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    public List<String> selectByFuelType(EngineType fuel){
        return getListOfCar()
                .stream()
                .filter(x -> x.getEngine().getEngineType()==fuel)
                .map(Car::getBrand)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Set<Car> selectWithEnterParametrs(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter minimum price and maximum price:");
        System.out.println("Minimum Price:");
        int minPrice = sc.nextInt();
        System.out.println("Maximum Price:");
        int maxPrice = sc.nextInt();

        sc.nextLine();
        System.out.println("Fuel Type: (DIESEL or GASOLINE)");
        String fuelType = sc.nextLine().toUpperCase();
        EngineType fuel = EngineType.valueOf(fuelType);

        System.out.println("Enter minimum and maximum capacity");
        System.out.println("minimum Capacity: ");
        double minCapacity = sc.nextDouble();
        System.out.println("maximum Capacity: ");
        double maxCapacity = sc.nextDouble();

        System.out.println("Additional equipment!");
        System.out.println("You can chose: (ELECTRIC_MIRRORS, ELECTRIC_WINDOWS,\n" +
                         " DOOR, ALARM, FOG_LIGHTS, AIR_CONDITIONING, AUDIO, SKIN;)");
        System.out.println("select number of eq:");
        int x = sc.nextInt();
        sc.nextLine();


        Set<Equipment> eq = new HashSet<>();
        for (int i = 0; i < x ; i++) {
            System.out.println("Choese "+ (i+1) + " :");
            eq.add(Equipment.valueOf(sc.nextLine().toUpperCase()));
        }

        System.out.println("------------------------------------");
        return getListOfCar()
                .stream()
                .filter(car -> car.getBody().getListOfEq().containsAll(eq))
                .filter(car -> car.getEngine().getEngineType()==fuel)
                .filter(car -> (car.getPrice()>minPrice && car.getPrice()<maxPrice))
                .filter(car -> (car.getEngine().getCapacity()>minCapacity && car.getEngine().getCapacity()<maxCapacity))
                .collect(Collectors.toCollection(HashSet::new));

    }

    public DoubleSummaryStatistics statisticsChoseParametrs(String name){

        switch (name.toLowerCase()) {
            case "capacity":
                return getListOfCar()
                        .stream()
                        .collect(Collectors.summarizingDouble(car -> car.getEngine().getCapacity()));
            case "price":
                return getListOfCar()
                        .stream()
                        .collect(Collectors.summarizingDouble(Car::getPrice));
            case "wheel":
                return getListOfCar()
                        .stream()
                        .collect(Collectors.summarizingDouble(car -> car.getWheel().getSize()));
            default:
                System.out.println("WRONG CHOSE!");
                return null;
        }
    }

    private Map<Car, Integer> carInspections(String filename){
        try {
            FileReader  reader = new FileReader(filename);
            Scanner sc = new Scanner(reader);

            Map<String, Integer> properties = new HashMap<>();
            String[] arr;
            
            while (sc.hasNextLine()==true){
                arr=splitLine(sc.nextLine()," ");
                properties.put(arr[0], Integer.parseInt(arr[1]));
            }

            Map<Car, Integer> carAndMiles =
                    getListOfCar()
                    .stream()
                            .sorted(Comparator.comparing(Car::getBrand).thenComparing(Car::getPrice))
                            .collect(Collectors.toMap(
                            k -> k,
                            k -> properties.get(k.getBrand()),
                            (v1,v2)-> v1,
                            () -> new LinkedHashMap<>()
                    ));

            return carAndMiles;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Car, Integer> sortCarInspections(String filename){

        return carInspections(filename)
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1,v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    public Map<TireType, List<Car>> wheelTypeList(){
        List<Car> summerType = getListOfCar().stream().filter(car -> car.getWheel().getType().equals(TireType.SUMMER)).collect(Collectors.toList());
        List<Car> winterType = getListOfCar().stream().filter(car -> car.getWheel().getType().equals(TireType.WINTER)).collect(Collectors.toList());

        Map<TireType, List<Car>> wheelTypCar = new HashMap<>();
        wheelTypCar.put(TireType.SUMMER,summerType);
        wheelTypCar.put(TireType.WINTER,winterType);

        return wheelTypCar;
    }

    private static String[] splitLine(String line, String regex)
    {
        if (line != null && regex != null)
        {
            return line.split(regex);
        }
        return null;
    }







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarShowroom that = (CarShowroom) o;

        return ListOfCar != null ? ListOfCar.equals(that.ListOfCar) : that.ListOfCar == null;
    }

    @Override
    public int hashCode() {
        return ListOfCar != null ? ListOfCar.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ListOfCar=" + ListOfCar;
    }
}
