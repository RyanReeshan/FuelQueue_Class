public class FuelQueue {
    static double Fuel = 6600;
    int PassengerCount = 0;

    Passenger[] passenger = new Passenger[6];

    public FuelQueue() {
        for (int i = 0; i < passenger.length; i++) {
            passenger[i] = new Passenger("Empty", "Empty", "Empty", 0);
        }
    }




    public int getPassengerCount() {
        return PassengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        PassengerCount = passengerCount;
    }

}
