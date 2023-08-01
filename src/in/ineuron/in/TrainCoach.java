package in.ineuron.in;
import java.util.Arrays;
import java.util.Scanner;

public class TrainCoach {
    private final int totalSeats = 80;
    private final int seatsInRow = 7;
    private final int lastRowSeats = 3;
    private boolean[] availableSeats;

    public TrainCoach() {
        availableSeats = new boolean[totalSeats];
        Arrays.fill(availableSeats, true);
    }

    private boolean areConsecutiveSeatsAvailable(int numSeats) {
        int consecutiveSeats = 0;
        for (boolean available : availableSeats) {
            if (available) {
                consecutiveSeats++;
                if (consecutiveSeats == numSeats) {
                    return true;
                }
            } else {
                consecutiveSeats = 0;
            }
        }
        return false;
    }

    private void reserveSeatsInRow(int numSeats) {
        int consecutiveSeats = 0;
        int startSeatIndex = -1;
        for (int i = 0; i < totalSeats; i++) {
            if (availableSeats[i]) {
                if (consecutiveSeats == 0) {
                    startSeatIndex = i;
                }
                consecutiveSeats++;
                if (consecutiveSeats == numSeats) {
                    for (int j = startSeatIndex; j < startSeatIndex + numSeats; j++) {
                        availableSeats[j] = false;
                    }
                    break;
                }
            } else {
                consecutiveSeats = 0;
            }
        }
    }

    public void reserveSeats(int numSeats) {
        if (numSeats <= 0) {
            System.out.println("Number of seats should be greater than zero.");
            return;
        }

        if (numSeats > seatsInRow) {
            System.out.println("Cannot reserve more than 7 seats at a time.");
            return;
        }

        if (areConsecutiveSeatsAvailable(numSeats)) {
            reserveSeatsInRow(numSeats);
            System.out.println("Seats reserved successfully.");
        } else {
            System.out.println("No available seats for the requested number.");
        }
    }

    public int getAvailableSeats() {
        int count = 0;
        for (boolean available : availableSeats) {
            if (available) {
                count++;
            }
        }
        return count;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public static void main(String[] args) {
        TrainCoach trainCoach = new TrainCoach();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Available Seats: " + trainCoach.getAvailableSeats() + " / " + trainCoach.getTotalSeats());
            System.out.print("Enter the number of seats to reserve (0 to exit): ");
            int numSeats = scanner.nextInt();

            if (numSeats == 0) {
                break;
            }

            trainCoach.reserveSeats(numSeats);
        }

        scanner.close();
    }
}
