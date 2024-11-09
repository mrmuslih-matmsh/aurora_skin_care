import java.util.Scanner;

public class SearchAppointment extends Menu {

    // Method to search for an appointment by patient name or appointment ID
    public static void Search_Appointment() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("------------------");
        System.out.println("Search Appointment");
        System.out.println("------------------");

        System.out.print("Enter the Patient name or Appointment ID: ");
        String search = scanner.nextLine();

        try {
            // Try parsing input as an integer for appointment ID search
            int appointmentID = Integer.parseInt(search);

            // Search for appointment by ID
            Appointment appointment = db.getAppointment(appointmentID);
            if (appointment != null) {
                // Display appointment details if found
                System.out.println("\nAppointment Found:");
                System.out.println("Patient: " + appointment.getName());
                System.out.println("Email: " + appointment.getEmail());
                System.out.println("Mobile: " + appointment.getPhone());
                System.out.println("Dermatologist: " + appointment.getDermatologist());
                System.out.println("Appointment Time: " + appointment.getTimeSlot());

                MainMenu(); // Return to main menu
            } else {
                System.out.println("No appointment found with ID: " + appointmentID);
                MainMenu(); // Return to main menu if no match
            }
        } catch (NumberFormatException e) {
            // Search for appointment by patient name if input is not an integer
            Appointment appointment = db.searchAppointmentByName(search);
            if (appointment != null) {
                // Display appointment details if found by name
                System.out.println("\nAppointment Found:");
                System.out.println("Patient: " + appointment.getName());
                System.out.println("Email: " + appointment.getEmail());
                System.out.println("Mobile: " + appointment.getPhone());
                System.out.println("Date: " + appointment.getDate());
                System.out.println("Dermatologist: " + appointment.getDermatologist());
                System.out.println("Appointment Time: " + appointment.getTimeSlot());

                MainMenu(); // Return to main menu
            } else {
                System.out.println("No appointment found for patient: " + search);
                MainMenu(); // Return to main menu if no match by name
            }
        }
    }
}
