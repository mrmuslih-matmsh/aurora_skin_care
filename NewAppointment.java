import java.util.Scanner;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class NewAppointment extends Menu {

    // In-memory map to store booked time slots for each dermatologist on each day
    private static Map<String, Map<String, Boolean>> bookedTimeSlots = new HashMap<>();

    static {
        // Initialize the bookedTimeSlots map with available days and time slots
        String[] mondaySlots = {
                "10:00-10:15", "10:15-10:30", "10:30-10:45", "10:45-11:00",
                "11:00-11:15", "11:15-11:30", "11:30-11:45", "11:45-12:00",
                "12:00-12:15", "12:15-12:30", "12:30-12:45", "12:45-01:00"
        };

        String[] wednesdaySlots = {
                "02:00-02:15", "02:15-02:30", "02:30-02:45", "02:45-03:00",
                "03:00-03:15", "03:15-03:30", "03:30-03:45", "03:45-04:00",
                "04:00-04:15", "04:15-04:30", "04:30-04:45", "04:45-05:00"
        };

        String[] fridaySlots = {
                "04:00-04:15", "04:15-04:30", "04:30-04:45", "04:45-05:00",
                "05:00-05:15", "05:15-05:30", "05:30-05:45", "05:45-06:00",
                "06:00-06:15", "06:15-06:30", "06:30-06:45", "06:45-07:00",
                "07:00-07:15", "07:15-07:30", "07:30-07:45", "07:45-08:00"
        };

        String[] saturdaySlots = {
                "09:00-09:15", "09:15-09:30", "09:30-09:45", "09:45-10:00",
                "10:00-10:15", "10:15-10:30", "10:30-10:45", "10:45-11:00",
                "11:00-11:15", "11:15-11:30", "11:30-11:45", "11:45-12:00",
                "12:00-12:15", "12:15-12:30", "12:30-12:45", "12:45-01:00"
        };

        // Initialize time slots for each day and mark them as available
        bookedTimeSlots.put("Monday", initializeDaySlots(mondaySlots));
        bookedTimeSlots.put("Wednesday", initializeDaySlots(wednesdaySlots));
        bookedTimeSlots.put("Friday", initializeDaySlots(fridaySlots));
        bookedTimeSlots.put("Saturday", initializeDaySlots(saturdaySlots));
    }

    // Helper method to initialize the time slots as available
    private static Map<String, Boolean> initializeDaySlots(String[] timeSlots) {
        Map<String, Boolean> daySlots = new HashMap<>();
        for (String slot : timeSlots) {
            daySlots.put(slot, false);
        }
        return daySlots;
    }

    // Method to create a new appointment
    public static void New_Appointment() {

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Collect patient details
        System.out.print("Enter Patient NIC: ");
        String nic = scanner.nextLine();

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Patient Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Patient Phone Number: ");
        String phone = scanner.nextLine();

        // Appointment Date input and validation
        Date date = null;
        String dayOfWeek = "";
        while (date == null) {
            System.out.print("Enter Appointment Date (dd/MM/yyyy): ");
            String dateString = scanner.nextLine();
            try {
                date = dateFormat.parse(dateString);
                dayOfWeek = new SimpleDateFormat("EEEE").format(date);
                if (!bookedTimeSlots.containsKey(dayOfWeek)) {
                    System.out.println("Appointments cannot be booked on this day. Please select a valid day.");
                    date = null;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }

        // Dermatologist selection
        System.out.println("\nSelect Dermatologist:");
        System.out.println("1. Dr. Sarah Thompson");
        System.out.println("2. Dr. Michael Roberts");
        System.out.print("Enter your choice: ");
        int dermatologistChoice = scanner.nextInt();
        String dermatologist = "";

        if (dermatologistChoice == 1) {
            dermatologist = "Dr. Sarah Thompson";
        } else if (dermatologistChoice == 2) {
            dermatologist = "Dr. Michael Roberts";
        } else {
            System.out.println("Invalid choice");
            MainMenu();
        }

        // Time slot selection
        System.out.println("\nAvailable Time Slots for " + dermatologist + " on " + dayOfWeek + ":");
        Map<String, Boolean> daySlots = bookedTimeSlots.get(dayOfWeek);
        int slotNumber = 1;
        Map<Integer, String> availableSlots = new HashMap<>();

        // Display time slots as a numbered list
        for (Map.Entry<String, Boolean> entry : daySlots.entrySet()) {
            if (!entry.getValue()) {
                System.out.println(slotNumber + ". " + entry.getKey() + " - Available");
                availableSlots.put(slotNumber, entry.getKey());
                slotNumber++;
            }
        }

        System.out.print("\nSelect a suitable time slot: ");
        int selectedSlotNumber = scanner.nextInt();

        // Check if the selected number is valid
        if (availableSlots.containsKey(selectedSlotNumber)) {
            String selectedSlot = availableSlots.get(selectedSlotNumber);
            daySlots.put(selectedSlot, true);
        } else {
            System.out.println("The selected time slot is not available. Please try another.");
        }

        // Confirm selected time slot and dermatologist
        System.out.println(
                "\nYou have selected " + availableSlots.get(selectedSlotNumber) + " with " + dermatologist + ".");
        System.out.print("Do you want to confirm this appointment? (yes/no): ");
        String confirmAppointment = scanner.next();

        // Check if the appointment is confirmed
        if (!confirmAppointment.equalsIgnoreCase("yes")) {
            System.out.println("Appointment not confirmed. Exiting...");
            MainMenu();
        }

        // Confirm payment of the registration fee
        System.out.println("\nA registration fee of LKR 500 is required.");
        System.out.print("Do you confirm payment of LKR 500? (yes/no): ");
        String confirmPayment = scanner.next();

        if (!confirmPayment.equalsIgnoreCase("yes")) {
            System.out.println("Payment not confirmed. Exiting...");
            MainMenu();
        }

        // Generate a random Appointment ID for the new appointment
        Random rand = new Random();
        int appointmentid = 1000 + rand.nextInt(9999);

        // Create new Appointment object and add it to the database
        Appointment newAppointment = new Appointment(nic, name, email, phone, date, dermatologist,
                availableSlots.get(selectedSlotNumber));
        db.addAppointment(appointmentid, newAppointment);

        // Display confirmation of appointment details
        System.out.println("------------------------");
        System.out.println("\nAppointment Confirmed!");
        System.out.println("------------------------");
        System.out.println("Appointment ID: " + appointmentid);
        System.out.println("Patient: " + name);
        System.out.println("Dermatologist: " + dermatologist);
        System.out.println("Appointment Date: " + date);
        System.out.println("Appointment Time: " + availableSlots.get(selectedSlotNumber));
        System.out.println("Registration Fee: LKR 500");
        System.out.println("------------------------------------------");
        System.out.println("\nThank you for choosing Aurora Skin Care.");
        System.out.println("------------------------------------------");

        MainMenu(); // Return to main menu after appointment confirmation

    }
}
