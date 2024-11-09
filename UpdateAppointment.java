import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UpdateAppointment extends Menu {

    // Method to handle updating an existing appointment's details
    public static void Update_Appointment() {

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        System.out.println("------------------");
        System.out.println("Update Appointment");
        System.out.println("------------------");

        // Prompt for the Appointment ID to locate the appointment in the database
        System.out.print("Enter the Appointment ID: ");
        int appointmentID = scanner.nextInt();
        scanner.nextLine();

        Appointment appointment = db.getAppointment(appointmentID);

        if (appointment != null) {

            // Display current details of the selected appointment
            System.out.println("\nCurrent Appointment Details:");
            System.out.println("Patient Name       : " + appointment.getName());
            System.out.println("Phone Number       : " + appointment.getPhone());
            System.out.println("Appointment Date   : " + dateFormat.format(appointment.getDate()));
            System.out.println("Dermatologist      : " + appointment.getDermatologist());
            System.out.println("Appointment Time   : " + appointment.getTimeSlot());
            System.out.println("---------------------------------");

            // Optionally update the phone number or keep it as is
            System.out.print("Enter New Patient Phone Number (or press Enter to keep current): ");
            String newPhone = scanner.nextLine();
            if (newPhone.isEmpty()) {
                newPhone = appointment.getPhone();
            }

            // Update appointment date with input validation loop
            Date newDate = appointment.getDate(); // Default to the current date if unchanged
            boolean validDate = false;
            while (!validDate) {
                System.out.print("Enter New Appointment Date (dd/MM/yyyy) (or press Enter to keep current): ");
                String newDateStr = scanner.nextLine();

                if (newDateStr.isEmpty()) {
                    // Keep current date if input is empty
                    validDate = true;
                } else {
                    try {
                        newDate = dateFormat.parse(newDateStr);
                        validDate = true;
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use dd/MM/yyyy.");
                    }
                }
            }

            // Confirm updated details before saving
            System.out.println("\nUpdated Appointment Details:");
            System.out.println("Patient Name       : " + appointment.getName());
            System.out.println("Phone Number       : " + newPhone);
            System.out.println("Appointment Date   : " + dateFormat.format(newDate));
            System.out.println("Dermatologist      : " + appointment.getDermatologist());
            System.out.println("Appointment Time   : " + appointment.getTimeSlot());

            // Confirm before finalizing the update
            System.out.print("\nDo you want to update this appointment? (yes/no): ");
            String confirmUpdate = scanner.nextLine();

            if (confirmUpdate.equalsIgnoreCase("yes")) {
                // Save changes to the database
                appointment.setPhone(newPhone);
                appointment.setDate(newDate);

                db.updateAppointment(appointmentID, appointment);

                System.out.println("\nAppointment updated successfully.");
            } else {
                System.out.println("Update canceled.");
            }

            MainMenu(); // Return to main menu after update or cancellation

        } else {
            // If no appointment found with provided ID
            System.out.println("No appointment found with ID: " + appointmentID);
            MainMenu();
        }
    }
}
