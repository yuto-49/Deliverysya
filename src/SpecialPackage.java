public class SpecialPackage extends Package {
    private int timeDeadline;

    public SpecialPackage() {
        super();
        setTimeDeadline(9);
    }

    public SpecialPackage(String receiverCompanyName, String deliveryZone, Date deliveryDate,
                          double weight, double volume, int timeDeadline) {
        super(receiverCompanyName, deliveryZone, deliveryDate, weight, volume);
        setTimeDeadline(timeDeadline);
    }

    // Accessor method
    public int getTimeDeadline() { return timeDeadline; }

    // Mutator method
    public void setTimeDeadline(int timeDeadline) {
        if (timeDeadline < 9 || timeDeadline > 16) {
            throw new InvalidSpecialPackageException("Time deadline must be between 9 and 16 (inclusive).");
        }
        this.timeDeadline = timeDeadline;
    }

    @Override
    public String toString() {
        return super.toString() + ", Time Deadline: " + timeDeadline;
    }

    public static class InvalidSpecialPackageException extends RuntimeException {
        public InvalidSpecialPackageException(String message) {
            super(message);
        }
    }
}

