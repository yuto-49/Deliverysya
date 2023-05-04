import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class PackageDeliverySystem {
    private List<Package> packages;
    private List<Truck> trucks;

    public PackageDeliverySystem() {
        packages = new ArrayList<>();
        trucks = new ArrayList<>();
    }

    public void loadPackagesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                int packageID = Integer.parseInt(tokens[0]);
                String receiverCompanyName = tokens[1];
                String deliveryZone = tokens[2];
                String deliveryDateString = tokens[3];
                Date deliveryDate;
                try {
                    deliveryDate = sdf.parse(deliveryDateString);
                } catch (ParseException e) {
                    System.out.println("Error parsing date: " + e.getMessage());
                    continue;
                }
                double weight = Double.parseDouble(tokens[4]);
                double volume = Double.parseDouble(tokens[5]);

                Package pkg = new Package(packageID, receiverCompanyName, deliveryZone, deliveryDate, weight, volume);
                packages.add(pkg);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }
    }


    public void displayTruckStats() {
        int smallTrucks = 0;
        int mediumTrucks = 0;
        int largeTrucks = 0;
        int totalTruckHours = 0;

        for (Truck truck : trucks) {
            if (truck.getTruckType() == Truck.Type.SMALL) {
                smallTrucks++;
                totalTruckHours += 1 * truck.getHoursUsed();
            } else if (truck.getTruckType() == Truck.Type.MEDIUM) {
                mediumTrucks++;
                totalTruckHours += 2 * truck.getHoursUsed();
            } else if (truck.getTruckType() == Truck.Type.LARGE) {
                largeTrucks++;
                totalTruckHours += 3 * truck.getHoursUsed();
            }
        }

        System.out.println("Number of small trucks: " + smallTrucks);
        System.out.println("Number of medium trucks: " + mediumTrucks);
        System.out.println("Number of large trucks: " + largeTrucks);
        System.out.println("Total truck hours used: " + totalTruckHours);
    }


    public void assignPackagesToTrucks() {
        for (Package pkg : packages) {
            boolean assigned = false;
            for (Truck truck : trucks) {
                if (truck.canCarry(pkg)) {
                    truck.addPackage(pkg);
                    assigned = true;
                    break;
                }
            }

            if (!assigned) {
                // Create a new truck
                Truck newTruck = new Truck(trucks.size() + 1); // Assign a unique ID to the new truck
                newTruck.addPackage(pkg);
                trucks.add(newTruck);
            }
        }
    }

    public void outputDeliveryData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("deliveries.txt"))) {
            for (Truck truck : trucks) {
                writer.println(truck);
            }
        } catch (IOException e) {
            System.out.println("Error writing deliveries.txt");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input file name: ");
        String fileName = scanner.nextLine();

        PackageDeliverySystem system = new PackageDeliverySystem();
        system.loadPackagesFromFile(fileName);
        system.assignPackagesToTrucks();
        system.outputDeliveryData();
        system.displayTruckStats();
        // Output the number of trucks of each size used and total truck hours used.
    }
}
