import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ViewAppointment extends Menu {

    // Method to handle viewing an appointment by date
    public static void View_Appointment() {

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Disallow lenient date parsing

        System.out.println("----------------");
        System.out.println("View Appointment");
        System.out.println("----------------");

        Date appointmentDate = null;
        boolean validDate = false;

        // Keep prompting for the date until a valid one is entered
        while (!validDate) {
            System.out.print("Enter the Date (dd/MM/yyyy): ");
            String appointmentDateStr = scanner.nextLine();

            try {
                // Parse the input date string to a Date object
                appointmentDate = dateFormat.parse(appointmentDateStr);
                validDate = true; // Exit loop if parsing succeeds
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            }
        }

        // Search for an appointment by the validated date
        Appointment appointment = db.searchAppointmentByDate(appointmentDate);
        if (appointment != null) {
            // Display details if an appointment is found for the specified date
            System.out.println("\nAppointment Found:");
            System.out.println("Patient: " + appointment.getName());
            System.out.println("Email: " + appointment.getEmail());
            System.out.println("Mobile: " + appointment.getPhone());
            System.out.println("Date: " + dateFormat.format(appointment.getDate()));
            System.out.println("Dermatologist: " + appointment.getDermatologist());
            System.out.println("Appointment Time: " + appointment.getTimeSlot());
        } else {
            // Notify if no appointments are found for the provided date
            System.out.println("No appointment found for this date.");
        }

        MainMenu(); // Return to main menu after viewing appointment details
    }
}
