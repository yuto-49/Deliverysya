import java.util.ArrayList;
import java.util.List;

public class Truck {
    public enum TruckType {
        SMALL(1000, 2000),
        MEDIUM(2000, 4000),
        LARGE(4000, 8000);

        private final int volumeLimit;
        private final int weightLimit;

        TruckType(int volumeLimit, int weightLimit) {
            this.volumeLimit = volumeLimit;
            this.weightLimit = weightLimit;
        }

        public int getVolumeLimit() { return volumeLimit; }
        public int getWeightLimit() { return weightLimit; }
    }

    private static int nextID = 1;
    private int truckID;
    private double packageWeight;
    private double packageVolume;
    private TruckType truckType;
    private List<Package> packages;

    public Truck(TruckType truckType) {
        setTruckID(nextID++);
        setTruckType(truckType);
        setPackageWeight(0);
        setPackageVolume(0);
        packages = new ArrayList<>();
    }

    // Accessor methods
    public int getTruckID() { return truckID; }
    public double getPackageWeight() { return packageWeight; }
    public double getPackageVolume() { return packageVolume; }
    public TruckType getTruckType() { return truckType; }
    public List<Package> getPackages() { return new ArrayList<>(packages); }

    // Mutator methods
    public void setTruckID(int truckID) {
        this.truckID = truckID;
    }

    public void setPackageWeight(double packageWeight) {
        this.packageWeight = packageWeight;
    }

    public void setPackageVolume(double packageVolume) {
        this.packageVolume = packageVolume;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

    public void addPackage(Package pkg) {
        double newWeight = packageWeight + pkg.getWeight();
        double newVolume = packageVolume + pkg.getVolume();

        if (newWeight > truckType.getWeightLimit() || newVolume > truckType.getVolumeLimit()) {
            throw new InvalidTruckException("Package weight or volume exceeds truck limits.");
        }

        packageWeight = newWeight;
        packageVolume = newVolume;
        packages.add(pkg);
    }

    @Override
    public String toString() {
        return "Truck ID: " + truckID + ", Truck Type: " + truckType + ", Package Weight: " + packageWeight +
                ", Package Volume: " + packageVolume + ", Packages: " + packages;
    }

    public class InvalidTruckException extends RuntimeException {
        public InvalidTruckException(String message) {
            super(message);
        }
    }
}

