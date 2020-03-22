import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Car {
    private String brand;
    private double price;
    private CarEngine engine;
    private Body body;
    private CarWheel wheel;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CarEngine getEngine() {
        return engine;
    }

    public void setEngine(CarEngine engine) {
        this.engine = engine;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public CarWheel getWheel() {
        return wheel;
    }

    public void setWheel(CarWheel wheel) {
        this.wheel = wheel;
    }

    public Car() {
    }

    public Car(String filename) {
        String[][] carData = writeFile(filename);
        if (carData.length==4){
            if (carData[0].length==2) {
                setBrand(carData[0][0]);
                setPrice(Double.parseDouble(carData[0][1]));
            }

            if (carData[1].length==3) {
                setEngine(new CarEngine(EngineType.valueOf(carData[1][0]), Double.parseDouble(carData[1][1]), Double.parseDouble(carData[1][2])));
            }

            if (carData[2].length==3){
                String[] eqipmentList = splitLine(carData[2][2], "\\;");
                Set<Equipment> eqList = new HashSet<>();


                for (int i = 0; i <eqipmentList.length ; i++) {
                    eqList.add(Equipment.valueOf(eqipmentList[i]));
                }

                setBody(new Body(BodyColor.valueOf(carData[2][0]), BodyType.valueOf(carData[2][1]), eqList));
            }

            if (carData[3].length==3) {
                setWheel(new CarWheel(carData[3][0], Integer.parseInt(carData[3][1]), TireType.valueOf(carData[3][2])));
            }
        }

    }


    public Car(String brand, double price, CarEngine engine, Body body, CarWheel wheel) {
        setEngine(engine);
        setWheel(wheel);
        setBody(body);
        setPrice(price);
        setBrand(brand);
    }

    private String[][] writeFile(String filename){
        String[][] arr = null;
        try {
            FileReader reader = new FileReader(filename);
            Scanner sc = new Scanner(reader);


            while (sc.hasNextLine()){
                String[] lineArr = splitLine(sc.nextLine(), "[\\s]");
                if (lineArr != null) {
                    arr = arr == null ? new String[1][] : Arrays.copyOf(arr, arr.length+1);
                    arr[arr.length - 1] = lineArr;
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
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

        Car car = (Car) o;

        if (Double.compare(car.price, price) != 0) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (engine != null ? !engine.equals(car.engine) : car.engine != null) return false;
        if (body != null ? !body.equals(car.body) : car.body != null) return false;
        return wheel != null ? wheel.equals(car.wheel) : car.wheel == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = brand != null ? brand.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (wheel != null ? wheel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nCar{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", engine=" + engine +
                ", body=" + body +
                ", wheel=" + wheel +
                '}';
    }
}
