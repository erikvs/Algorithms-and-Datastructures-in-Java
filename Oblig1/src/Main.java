import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter how many time units to run simulation (ex: 100): ");
        int runTime = userInput.nextInt();
        System.out.println("Enter the expected amount of arrivals per time unit (ex: 0.6): ");
        double arrivalMean = userInput.nextDouble();
        System.out.println("Enter the expected amount of departures per time unit (ex: 0.4): ");
        double departureMean = userInput.nextDouble();

        int timeUnit = 0;
        int totalArrivals = 0;
        int totalDepartures = 0;
        int totalRejected = 0;
        int planeNumber = 0;
        int planesHandled = 0;
        int arrivalsForTimeUnit = 0;
        int departuresForTimeUnit = 0;
        int idleAirport = 0;
        double arrivalWait = 0;
        double departureWait = 0;

        //formatter for mean waiting time for queues in print
        DecimalFormat dfZero = new DecimalFormat("0.00");

        //Empty queues
        Queue<Plane> arrivals = new LinkedList<Plane>();
        Queue<Plane> departures = new LinkedList<Plane>();

        for (int i = 1; i < runTime; i++) {
            timeUnit++;

            //checking how many planes arrive, if any
            arrivalsForTimeUnit = Traffic.getPoissonRandom(arrivalMean);

            //if planes arrive in this timeUnit, process them based on current queue
            if (arrivalsForTimeUnit > 0) {
                //run this loop for Y times where Y is amount of planes arriving this timeUnit
                for (int y = 1; y < arrivalsForTimeUnit; y++) {
                    Plane plane = new Plane(timeUnit, planeNumber);
                    planeNumber++;
                    planesHandled++;

                    //if the arrival queue is full, reject the plane
                    if (arrivals.size() == 9) {
                        totalRejected++;
                    }
                    //if arrival queue is not full, add plane
                    else {
                        arrivals.add(plane);
                    }
                }
            }
            //checking how many planes request departure, if any
            departuresForTimeUnit = Traffic.getPoissonRandom(departureMean);

            if (departuresForTimeUnit > 0) {
                //run this loop for Y times where Y is amount of planes asking to depart this timeUnit
                for (int y = 1; y < departuresForTimeUnit; y++) {
                    Plane plane = new Plane(timeUnit, planeNumber);
                    planeNumber++;
                    planesHandled++;

                    //if the departure queue is full, reject the plane
                    if (departures.size() == 9) {
                        totalRejected++;
                    }
                    //if departure queue is not full, add plane
                    else {
                        departures.add(plane);
                    }
                }
            }
            //prio landing, pick first in queue if there is a queue
            if (arrivals.size() != 0) {
                arrivals.peek().setDepartureTime(timeUnit);
                System.out.println("At time interval " + timeUnit + " plane " + arrivals.peek().getID() + " has landed. Waiting time was " + arrivals.peek().totalTime() + "");
                arrivalWait = arrivalWait + arrivals.peek().totalTime();
                totalArrivals++;
                arrivals.remove();
            }
            //departure, pick first in queue if there is a queue
            else if (departures.size() != 0 ) {
                departures.peek().setDepartureTime(timeUnit);
                System.out.println("At time interval " + timeUnit + " plane " + departures.peek().getID() + " has departed. Waiting time was " + departures.peek().totalTime() + "");
                departureWait = departureWait + departures.peek().totalTime();
                totalDepartures++;
                departures.remove();

            }
            //if no planes land or leave this timeUnit, count idle
            else {
                idleAirport++;
            }
        }
        System.out.println("\nSimulation completed in " + runTime + " units of time \n");
        System.out.println("The total amount of planes processed was:" + planesHandled);
        System.out.println("The total amount of planes landed was:" + totalArrivals);
        System.out.println("The total amount of planes departed was:" + totalDepartures);
        System.out.println("The total amount of planes rejected was:" + totalRejected);
        System.out.println("The total amount of planes waiting to land :" + arrivals.size());
        System.out.println("The total amount of planes waiting to depart:" + departures.size());
        System.out.println("The amount of time the airport was idle : " + ((idleAirport * runTime) / 100) + " percent ");
        System.out.println("The mean waiting time for arrivals was : " + dfZero.format(arrivalWait/totalArrivals) + " time units");
        System.out.println("The mean waiting time for departures was : " + dfZero.format(departureWait/totalDepartures) + " time units");
    }
}


