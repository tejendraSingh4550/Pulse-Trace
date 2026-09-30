import java.util.Random;
import java.util.Scanner;

/** Console CRUD application for pulse readings. */
public class PulseTraceApp {
    private static final int MAX_READINGS = 100;

    private final PulseReading[] readings = new PulseReading[MAX_READINGS];
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public static void main(String[] args) {
        new PulseTraceApp().run();
    }

    private void run() {
        boolean running = true;
        System.out.println("=== PulseTrace: Pulse Reading Manager ===");

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ", 0, 5);
            switch (choice) {
                case 1: addReading(); break;
                case 2: listReadings(); break;
                case 3: searchReading(); break;
                case 4: updateReading(); break;
                case 5: deleteReading(); break;
                case 0:
                    running = false;
                    System.out.println("PulseTrace closed. Take care!");
                    break;
                default:
                    System.out.println("Please choose a number from 0 to 5.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n1. Add reading");
        System.out.println("2. List readings");
        System.out.println("3. Search by ID");
        System.out.println("4. Update reading");
        System.out.println("5. Delete reading");
        System.out.println("0. Exit");
    }

    // CREATE
    private void addReading() {
        int emptyIndex = findEmptyIndex();
        if (emptyIndex == -1) {
            System.out.println("Storage is full (maximum 100 readings).");
            return;
        }

        String name = readName("Person's name: ");
        int bpm = readInt("Pulse in BPM (1-250): ", 1, 250);
        int id = createUniqueId();
        readings[emptyIndex] = new PulseReading(id, name, bpm);
        System.out.println("Reading added. ID: " + id);
    }

    // READ all
    private void listReadings() {
        if (isEmpty()) {
            System.out.println("No readings saved yet.");
            return;
        }
        printHeader();
        for (PulseReading reading : readings) {
            if (reading != null) {
                System.out.println(reading.toDisplayString());
            }
        }
    }

    // READ one
    private void searchReading() {
        int id = readInt("Reading ID: ", 1000, 9999);
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("No reading found for ID " + id + ".");
            return;
        }
        printHeader();
        System.out.println(readings[index].toDisplayString());
    }

    // UPDATE
    private void updateReading() {
        int id = readInt("ID to update: ", 1000, 9999);
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("No reading found for ID " + id + ".");
            return;
        }
        String name = readName("New person's name: ");
        int bpm = readInt("New pulse in BPM (1-250): ", 1, 250);
        readings[index].update(name, bpm);
        System.out.println("Reading updated.");
    }

    // DELETE
    private void deleteReading() {
        int id = readInt("ID to delete: ", 1000, 9999);
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("No reading found for ID " + id + ".");
            return;
        }
        readings[index] = null;
        System.out.println("Reading deleted.");
    }

    private int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= minimum && value <= maximum) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Show the same helpful prompt for non-number input.
            }
            System.out.println("Enter a whole number from " + minimum + " to " + maximum + ".");
        }
    }

    private String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Name cannot be empty.");
        }
    }

    private int findEmptyIndex() {
        for (int i = 0; i < readings.length; i++) {
            if (readings[i] == null) return i;
        }
        return -1;
    }

    private int findIndexById(int id) {
        for (int i = 0; i < readings.length; i++) {
            if (readings[i] != null && readings[i].getId() == id) return i;
        }
        return -1;
    }

    private int createUniqueId() {
        int id;
        do {
            id = 1000 + random.nextInt(9000);
        } while (findIndexById(id) != -1);
        return id;
    }

    private boolean isEmpty() {
        for (PulseReading reading : readings) {
            if (reading != null) return false;
        }
        return true;
    }

    private void printHeader() {
        System.out.println("ID     | Name                 | BPM       | Category  | Recorded at");
        System.out.println("-------+----------------------+-----------+-----------+-------------------");
    }
}
