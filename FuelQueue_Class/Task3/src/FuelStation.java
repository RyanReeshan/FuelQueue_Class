import java.io.*;
import java.util.Scanner;

public class FuelStation {
    static FuelQueue[] queue = new FuelQueue[5];
    static Queue[] waitingList = new Queue[1];
    public static Scanner input = new Scanner(System.in);
    public static double Add_Fuel;
    public static double Q1, Q2, Q3, Q4, Q5;
    static FuelQueue obj = new FuelQueue();

    public static void main(String[] args) throws IOException {
        obj.setPassengerCount(0);

        for (int i = 0; i < queue.length; i++) {    //Initialize queue array
            queue[i] = new FuelQueue();
        }
        for(int i = 0; i < waitingList.length; i++){     //Initialize waitingList array
            waitingList[i] = new Queue(obj.passenger, 6);
        }

        if (FuelQueue.Fuel == 500) {        // warning message
            System.out.println("Warning ! your Stock has only 500l.");
        }
        System.out.println("\n...........Fuel Queue Management System..........\n");
        while (true) {
            System.out.println("\n+=======================================================+");
            System.out.println();
            System.out.println("100 or VFQ : View Fuel Queues.");
            System.out.println("101 or VEQ : View all Empty Queues.");
            System.out.println("102 or ACQ : Add customer from a Queues.");
            System.out.println("103 or RCQ : Remove a customer from a Queues.");
            System.out.println("104 or PCQ : Removed a served customer.");
            System.out.println("105 or VCS : View Customers Sorted in alphabetical order.");
            System.out.println("106 or SPD : Store Program Data into a file.");
            System.out.println("107 or LPD : Load Program Data from file.");
            System.out.println("108 or STK : View Remaining Fuel Stock.");
            System.out.println("109 or AFS : Add Fuel Stock.");
            System.out.println("110 or IFQ : Income of Each Fuel Queue.");
            System.out.println("999 or EXT : Exit the Program.");
            System.out.println();
            System.out.println("+=======================================================+");

            System.out.print("\nPlease select an Option : ");
            String option = input.next().toUpperCase();

            switch (option) {
                case "100", "VFQ" -> ViewAllFuelQue();
                case "101", "VEQ" -> ViewAllEmpQue();
                case "102", "ACQ" -> AddCusQue();
                case "103", "RCQ" -> RemCusQue();
                case "104", "PCQ" -> RemServCusQue();
                case "105", "VCS" -> SortAlpha();
                case "106", "SPD" -> LoadFile();
                case "107", "LPD" -> ReadData();
                case "108", "STK" -> ViewRemFuelStock();
                case "109", "AFS" -> AddFuelSto();
                case "110", "INC" -> Income();
                case "999", "EXT" -> {
                    System.out.println("\n.......... THANK YOU HAVE A GOOD DAY! ..........");
                    System.out.println();
                    return;
                }
                default -> System.out.println("\n.....Invalid Option Chosen.....");
            }
        }
    }
    //This method shoes all the queues of the fuel station.
    public static void ViewAllFuelQue() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                   100 or VFQ - View Fuel Queues.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        for (int i = 0; i < queue.length; i++) {
            System.out.println("Queue " + (i + 1));
            System.out.println();
            for (int j = 0; j < obj.passenger.length; j++) {
                System.out.println("Customer " + (j + 1) + " -> First Name      : " + queue[i].passenger[j].getFirst_Name());
                System.out.println("Customer " + (j + 1) + " -> Last Name       : " + queue[i].passenger[j].getLast_Name());
                System.out.println("Customer " + (j + 1) + " -> Vehicle Number  : " + queue[i].passenger[j].getVehicle_No());
                System.out.println("Customer " + (j + 1) + " -> Required Liters : " + queue[i].passenger[j].getReq_Liters());
                System.out.println();
            }
        }
    }
    //This method shows all the empty queues of the fuel station
    public static void ViewAllEmpQue() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                   101 or VEQ - View all Empty Queues.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");
        for (int i = 0; i < queue.length; i++) {
            if (queue[i].passenger[0].getFirst_Name().equals("Empty")) {
                System.out.println("Queue " + (i + 1) + " is Empty");
            } else {
                System.out.println("Unfortunately Queue " + (i + 1) + " is NOT Empty !");
            }
        }
        System.out.println();
    }
    //adding customer to the minimum length queue. I declared a method called ""add""
    public static void AddCusQue() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                   102 or ACQ - Add customer from a Queues.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        //this is for finding the minimum length of the queue out of five queues
        int i = 0;
        if (queue[i].getPassengerCount() < queue[i + 1].getPassengerCount()) {
            if (queue[i].getPassengerCount() < queue[i + 2].getPassengerCount()) {
                if (queue[i].getPassengerCount() < queue[i + 3].getPassengerCount()) {
                    if (queue[i].getPassengerCount() < queue[i + 4].getPassengerCount()) {
                        add(0);
                    } else {
                        add(4);
                    }
                } else if (queue[i + 3].getPassengerCount() < queue[i + 4].getPassengerCount()) {
                    add(3);
                } else {
                    add(4);
                }
            } else if (queue[i + 2].getPassengerCount() < queue[i + 3].getPassengerCount()) {
                if (queue[i + 2].getPassengerCount() < queue[i + 4].getPassengerCount()) {
                    add(2);
                } else {
                    add(4);
                }
            } else if (queue[i + 3].getPassengerCount() < queue[i + 4].getPassengerCount()) {
                add(3);
            } else {
                add(4);
            }
        } else if (queue[i + 1].getPassengerCount() < queue[i + 2].getPassengerCount()) {
            if (queue[i + 1].getPassengerCount() < queue[i + 3].getPassengerCount()) {
                if (queue[i + 1].getPassengerCount() < queue[i + 4].getPassengerCount()) {
                    add(1);
                } else {
                    add(4);
                }
            } else if (queue[i + 3].getPassengerCount() < queue[i + 4].getPassengerCount()) {
                add(3);
            } else {
                add(4);
            }
        } else if (queue[i + 2].getPassengerCount() < queue[i + 3].getPassengerCount()) {
            if (queue[i + 2].getPassengerCount() < queue[i + 4].getPassengerCount()) {
                add(2);
            } else {
                add(4);
            }
        } else if (queue[i + 3].getPassengerCount() < queue[i + 4].getPassengerCount()) {
            add(3);
        } else {
            add(4);
        }
    }
    //I create this method to assign values to the queues and passengers.
    public static void add(int y) {
        for (int j = 0; j < 6; j++) {
            if (queue[y].passenger[j].getFirst_Name().equals("Empty")) {
                System.out.print("Enter Your First Name : ");
                String First_name = input.next();
                queue[y].passenger[j].setFirst_Name(First_name);

                System.out.print("Enter Your Last Name : ");
                String Last_name = input.next();
                queue[y].passenger[j].setLast_Name(Last_name);

                System.out.print("Enter Your Vehicle Number : ");
                String Vehicle_no = input.next();
                queue[y].passenger[j].setVehicle_No(Vehicle_no);

                System.out.print("Enter Required Liters : ");
                double Liters = input.nextDouble();
                queue[y].passenger[j].setReq_Liters(Liters);
                System.out.println("\n.....Successfully Added !.....");
                queue[y].PassengerCount += 1;
                obj.PassengerCount = obj.PassengerCount + queue[y].PassengerCount;
                return;
            }
        }
        //if all the queues customers are full, I created this waiting list for them.
        if (!queue[y].passenger[5].getFirst_Name().equals("Empty")) {
            System.out.println("We are moving you to a waiting queue because all Queues are Full !");
            Passenger[] passengersForQueueArray = new Passenger[6];

            for (int i = 0; i < passengersForQueueArray.length; i++) {

                if (waitingList[0].waitingPassenger[i].getFirst_Name() == null) {
                    System.out.print("Enter Your First Name : ");
                    String First_name = input.next();
                    waitingList[0].waitingPassenger[i].setFirst_Name(First_name);

                    System.out.print("Enter Your Last Name : ");
                    String Last_name = input.next();
                    waitingList[0].waitingPassenger[i].setLast_Name(Last_name);

                    System.out.print("Enter Your Vehicle Number : ");
                    String Vehicle_no = input.next();
                    waitingList[0].waitingPassenger[i].setVehicle_No(Vehicle_no);

                    System.out.print("Enter Required Liters : ");
                    double Liters = input.nextDouble();
                    waitingList[0].waitingPassenger[i].setReq_Liters(Liters);

                    Passenger waitingPassenger = new Passenger(First_name, Last_name, Vehicle_no, Liters);
                    passengersForQueueArray[i] = waitingPassenger;
                }
            }
            Queue passengersForQueueSlot = new Queue(passengersForQueueArray, 6);
            Queue.enQueue(passengersForQueueSlot);
        }
    }
    //This is Remove customer, you can remove a customer from anywhere you want to delete ( between the range)
    public static void RemCusQue() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                   103 or RCQ - Remove a customer from a Queues.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        System.out.print("Enter Queue Number (Queue numbers are from 1 to 5) : ");
        int x = input.nextInt();

        if (x <= 5 && x >= 1) {
            System.out.print("Enter which Customer Number that you want to delete from ? (Customer numbers are from 1 to 6) : ");
            int z = input.nextInt();

            if (z <= 6 && z >= 1) {
                if (z == 6) {
                    if (!queue[x - 1].passenger[z - 1].getFirst_Name().equals("Empty")) {
                        queue[x - 1].passenger[z - 1].setFirst_Name("Empty");
                        queue[x - 1].passenger[z - 1].setLast_Name("Empty");
                        queue[x - 1].passenger[z - 1].setVehicle_No("Empty");
                        queue[x - 1].passenger[z - 1].setReq_Liters(0);
                    }
                } else if (z == 5) {
                    if (!queue[x - 1].passenger[z - 1].getFirst_Name().equals("Empty")) {
                        queue[x - 1].passenger[z - 1] = queue[x - 1].passenger[z];
                    }
                } else if (z == 4) {
                    if (!queue[x - 1].passenger[z - 1].getFirst_Name().equals("Empty")) {
                        queue[x - 1].passenger[z - 1] = queue[x - 1].passenger[z];
                        queue[x - 1].passenger[z] = queue[x - 1].passenger[z + 1];
                    }
                } else if (z == 3) {
                    if (!queue[x - 1].passenger[z - 1].getFirst_Name().equals("Empty")) {
                        queue[x - 1].passenger[z - 1] = queue[x - 1].passenger[z];
                        queue[x - 1].passenger[z] = queue[x - 1].passenger[z + 1];
                        queue[x - 1].passenger[z + 1] = queue[x - 1].passenger[z + 2];
                    }
                } else if (z == 2) {
                    if(!queue[x - 1].passenger[z - 1].getFirst_Name().equals("Empty")){
                        queue[x - 1].passenger[z - 1] = queue[x - 1].passenger[z];
                        queue[x - 1].passenger[z] = queue[x - 1].passenger[z + 1];
                        queue[x - 1].passenger[z + 1] = queue[x - 1].passenger[z + 2];
                        queue[x - 1].passenger[z + 2] = queue[x - 1].passenger[z + 3];

                    }
                } else {
                    if (!queue[x - 1].passenger[z - 1].getFirst_Name().equals("Empty")) {
                        queue[x - 1].passenger[z - 1] = queue[x - 1].passenger[z];
                        queue[x - 1].passenger[z] = queue[x - 1].passenger[z + 1];
                        queue[x - 1].passenger[z + 1] = queue[x - 1].passenger[z + 2];
                        queue[x - 1].passenger[z + 2] = queue[x - 1].passenger[z + 3];
                        queue[x - 1].passenger[z + 3 ] = queue[ x - 1].passenger[z + 4];

                    }
                }
                System.out.println("\n.....Successfully Removed !.....");
            } else {
                System.out.println("\n.....Invalid Input !.....");
            }
        } else {
            System.out.println("\n.....Invalid Input !.....");
        }
    }
    //This is served customer. after getting fuel the customer will be and calculate the price
    public static void RemServCusQue() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("               104 or RSQ - Removed a served customer                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        try {
            System.out.print("From which Queue do you want to remove a served customer ? (Queue numbers are between 1 to 5) : ");
            int z = input.nextInt();
            if (z <= 5 && z >= 1) {
                for (int i = 0; i < 1; i++) {
                    if (!queue[z - 1].passenger[0].getFirst_Name().equals("Empty")) {
                        FuelQueue.Fuel = FuelQueue.Fuel - queue[z - 1].passenger[0].getReq_Liters();
                        if (z == 1) {
                            Q1 = Q1 + queue[z - 1].passenger[0].getReq_Liters();
                        } else if (z == 2) {
                            Q2 = Q2 + queue[z - 1].passenger[0].getReq_Liters();
                        } else if (z == 3) {
                            Q3 = Q3 + queue[z - 1].passenger[0].getReq_Liters();
                        } else if (z == 4) {
                            Q4 = Q4 + queue[z - 1].passenger[0].getReq_Liters();
                        } else {
                            Q5 = Q5 + queue[z - 1].passenger[0].getReq_Liters();
                        }

                        queue[z - 1].passenger[0] = queue[z - 1].passenger[1];
                        queue[z - 1].passenger[1] = queue[z - 1].passenger[2];
                        queue[z - 1].passenger[2] = queue[z - 1].passenger[3];
                        queue[z - 1].passenger[3] = queue[z - 1].passenger[4];
                        queue[z - 1].passenger[4] = queue[z - 1].passenger[5];
                        System.out.println("\n.....Successfully Removed !.....");

                        Queue waitingPassengers = Queue.deQueue();
                        if (waitingPassengers != null) {
                            Passenger[] passengerDetailsQueue = waitingPassengers.getWaitingPassenger();
                            int j = 0;
                            queue[z - 1].passenger[5].setFirst_Name(passengerDetailsQueue[j].getFirst_Name());
                            queue[z - 1].passenger[5].setLast_Name(passengerDetailsQueue[j].getLast_Name());
                            queue[z - 1].passenger[5].setVehicle_No(passengerDetailsQueue[j].getVehicle_No());
                            queue[z - 1].passenger[5].setReq_Liters(passengerDetailsQueue[i].getReq_Liters());
                            queue[z - 1].passenger[5].setPassenger(passengerDetailsQueue);
                        }
                    } else {
                        System.out.println("     All ready Empty !    ");
                    }
                }
            } else {
                System.out.println("    Invalid Input !    ");
            }
        } catch (Exception e) {
            System.out.println("    Invalid Input !   ");
        }
    }
    //This shows remaining fuel stock
    public static void ViewRemFuelStock() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("               108 or STK - View Remaining Fuel Stock.                    ");
        System.out.println("|-------ViewRemFuelStock---------------------------------------------------------------|\n");

        if (FuelQueue.Fuel == 500) {
            System.out.println("Warning ! your Stock has only 500l.");
            System.out.println("Remaining Fuel is : " + FuelQueue.Fuel);
        } else {
            System.out.println("\nRemaining Fuel is : " + FuelQueue.Fuel);
        }
    }
    //This show amount of the add fuel stock
    public static void AddFuelSto() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                  109 or AFS - Add Fuel Stock.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        Add_Fuel = 6600 - FuelQueue.Fuel;
        System.out.println("The Amount of Add Fuel stock should be : " + Add_Fuel);

    }
    //This is the process of the calculating the total
    public static void Income() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                  110 or IFQ - Income from Queues                  ");
        System.out.println("|----------------------------------------------------------------------|\n");

        double T1, T2, T3, T4, T5;
        T1 = Q1 * 430;
        T2 = Q2 * 430;
        T3 = Q3 * 430;
        T4 = Q4 * 430;
        T5 = Q5 * 430;

        System.out.println("Queue 1 Total Income  : " + T1);
        System.out.println("Queue 2 Total Income  : " + T2);
        System.out.println("Queue 3 Total Income  : " + T3);
        System.out.println("Queue 4 Total Income  : " + T4);
        System.out.println("Queue 5 Total Income  : " + T5);
    }
    //This will write program data to file.txt file.
    public static void LoadFile() throws IOException {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                  106 or LDF - Load Data into a File                 ");
        System.out.println("|----------------------------------------------------------------------|\n");

        File dataFile = new File("File.txt");
        FileWriter file = new FileWriter(dataFile);
        file.write("\nQueue Details");
        for (int i = 0; i < queue.length; i++) {
            file.write("\nQueue " + (i + 1));
            System.out.println();
            for (int j = 0; j < obj.passenger.length; j++) {
                file.write("\nCustomer " + (j + 1) + " -> First Name      : " + queue[i].passenger[j].getFirst_Name());
                file.write("\nCustomer " + (j + 1) + " -> Last Name       : " + queue[i].passenger[j].getLast_Name());
                file.write("\nCustomer " + (j + 1) + " -> Vehicle Number  : " + queue[i].passenger[j].getVehicle_No());
                file.write("\nCustomer " + (j + 1) + " -> Required Liters : " + queue[i].passenger[j].getReq_Liters());
                System.out.println();
            }
        }
        file.close();
        System.out.println("\nPROGRAM DATA SUCCESSFULLY STORED IN THE TEXT FILE");

    }
    //read the data from the file
    public static void ReadData() throws FileNotFoundException {
        File readFile = new File("File.txt");
        Scanner reader = new Scanner(readFile);
        while (reader.hasNext()) {
            System.out.println(reader.nextLine());
        }
        System.out.println("\n---------- Successfully Read ! ----------------");
    }
    //This method show sorting customers names
    public static void SortAlpha() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("          105 or VCS - View Customers Sorted in alphabetical order.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        String[] array = new String[30];
        int pointer = 0;
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 6; j++){
                array[pointer++] = queue[i].passenger[j].getFirst_Name();

            }
        }
        String temp;
        for (int i = 0; i < array.length; i++){
            for (int j = i + 1; j < array.length; j++){
                if(array[j].compareTo(array[i]) < 0){
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            if (!array[i].equals("Empty")){
                System.out.println(array[i]);
                //System.out.println();
            }
        }
        System.out.println();
    }

}