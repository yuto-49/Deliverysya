public class Package {
    private static final int nextID = 1;
    private int packageID;
    private String receiverCompanyName;
    private String deliveryZone;
    private Date deliveryDate;
    private double weight;
    private double volume;

    public Package() {
        this(packageID,"Default Company", "A1", new Date(), 1, 1);
    }

    public Package(int packageID,String receiverCompanyName, String deliveryZone, Date deliveryDate, double weight, double volume) {
        setPackageID(packageID);
        setReceiverCompanyName(receiverCompanyName);
        setDeliveryZone(deliveryZone);
        setDeliveryDate(deliveryDate);
        setWeight(weight);
        setVolume(volume);
    }



    // Accessor methods
    public int getPackageID() { return packageID; }
    public String getReceiverCompanyName() { return receiverCompanyName; }
    public String getDeliveryZone() { return deliveryZone; }
    public Date getDeliveryDate() { return new Date(deliveryDate); }
    public double getWeight() { return weight; }
    public double getVolume() { return volume; }

    // Mutator methods
    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public void setReceiverCompanyName(String receiverCompanyName) {
        this.receiverCompanyName = receiverCompanyName;
    }

    public void setDeliveryZone(String deliveryZone) {
        this.deliveryZone = deliveryZone;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = new Date(deliveryDate);
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new InvalidPackageException("Weight must be positive.");
        }
        this.weight = weight;
    }

    public void setVolume(double volume) {
        if (volume <= 0) {
            throw new InvalidPackageException("Volume must be positive.");
        }
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Package ID: " + packageID + ", Receiver Company Name: " + receiverCompanyName + ", Delivery Zone: " + deliveryZone +
                ", Delivery Date: " + deliveryDate + ", Weight: " + weight + ", Volume: " + volume;
    }

    public static class InvalidPackageException extends RuntimeException {
        public InvalidPackageException(String message) {
            super(message);
        }
    }
}

