public class Queue {
    private static final Queue[] waitingList = new Queue[1];
    Passenger[] waitingPassenger;
    private final int guest;
    private static int Front;
    private static int Rear;
    private static int size;



    public Queue(Passenger[] waitingPassenger, int guest) {
        this.waitingPassenger = waitingPassenger;
        this.guest = guest;
    }

    public static void enQueue(Queue waitingPassengers){
        if (!isQueueFull()){
            waitingList[Rear] = waitingPassengers;
            Rear = (Rear + 1) % 5;
            size++;
            System.out.printf("Cruise ship is full\n%d passengers has/have been added to the waiting list\n", waitingPassengers.getGuest());
        }else{
            System.out.println("Oops! Queue is full");
        }
    }

    public static Queue deQueue(){
        Queue waitingCus = null;
        if (!isQueueEmpty()){
            waitingCus = waitingList[Front];
            Front = (Front + 1) % 5;
            size--;
        }
        return waitingCus;
    }




    public int getGuest() {
        return guest;
    }
    public static boolean isQueueEmpty(){
        return getSize() == 0;
    }
    public static boolean isQueueFull(){
        return getSize() == 5;
    }

    public Passenger[] getWaitingPassenger() {
        return waitingPassenger;
    }

    public static int getSize() {
        return size;
    }


}
