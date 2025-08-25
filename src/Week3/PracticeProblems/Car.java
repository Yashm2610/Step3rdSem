package Week3.PracticeProblems;

public class Car {

    // Attributes
    private String brand;
    private String model;
    private int year;
    private String color;
    private boolean isRunning;

    // Constructor
    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false; // Default state
    }

    // Methods
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(brand + " " + model + " engine started.");
        } else {
            System.out.println("Engine is already running.");
        }
    }

    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println(brand + " " + model + " engine stopped.");
        } else {
            System.out.println("Engine is already off.");
        }
    }

    public void displayInfo() {
        System.out.println("Car Info: " + brand + " " + model + ", Year: " + year + ", Color: " + color + ", Running: " + isRunning);
    }

    public int getAge() {
        return java.time.Year.now().getValue() - year;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2020, "Red");
        Car car2 = new Car("Honda", "Civic", 2018, "Blue");
        Car car3 = new Car("Tesla", "Model S", 2022, "Black");

        car1.startEngine();
        car1.displayInfo();
        System.out.println("Car Age: " + car1.getAge() + " years");

        car2.displayInfo();
        car3.displayInfo();
    }
}
