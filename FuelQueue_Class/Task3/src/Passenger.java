public class Passenger {
    private String First_Name;
    private String Last_Name;
    private String Vehicle_No;
    private double Req_Liters;

    public Passenger(String First_Name, String Last_Name, String Vehicle_No, double Req_Liters) {
        this.setFirst_Name(First_Name);
        this.setLast_Name(Last_Name);
        this.setVehicle_No(Vehicle_No);
        this.setReq_Liters(Req_Liters);

    }


    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getVehicle_No() {
        return Vehicle_No;
    }

    public void setVehicle_No(String vehicle_No) {
        Vehicle_No = vehicle_No;
    }

    public double getReq_Liters() {
        return Req_Liters;
    }

    public void setReq_Liters(double req_Liters) {
        Req_Liters = req_Liters;
    }

    public void setPassenger(Passenger[] passengerDetailsQueue) {

    }

}
