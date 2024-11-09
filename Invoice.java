import java.util.Scanner;

public class Invoice extends Menu {

    // Method to generate an invoice for a selected appointment
    public static void GenerateInvoice() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------");
        System.out.println("Generate Invoice");
        System.out.println("----------------");

        // Prompt for Appointment ID to retrieve the appointment details
        System.out.print("Enter the Appointment ID: ");
        int appointmentID = scanner.nextInt();

        Appointment appointment = db.getAppointment(appointmentID);

        // Check if appointment exists for the given ID
        if (appointment != null) {

            // Display treatment options with prices
            System.out.println("Select Treatment Type:");
            System.out.println("1. Acne Treatment (LKR 2750.00)");
            System.out.println("2. Skin Whitening (LKR 7650.00)");
            System.out.println("3. Mole Removal (LKR 3850.00)");
            System.out.println("4. Laser Treatment (LKR 12500.00)");
            System.out.print("Enter choice (1-4): ");
            int treatmentChoice = scanner.nextInt();

            // Invoice header
            System.out.println("\n-------------------------------------");
            System.out.println("              INVOICE               ");
            System.out.println("-------------------------------------");
            System.out.println("Appointment ID: " + appointmentID);
            System.out.println("Patient Name  : " + appointment.getName());
            System.out.println("Email         : " + appointment.getEmail());
            System.out.println("Mobile        : " + appointment.getPhone());
            System.out.println("Dermatologist : " + appointment.getDermatologist());
            System.out.println("Appointment Date : " + appointment.getDate());
            System.out.println("Time   : " + appointment.getTimeSlot());
            System.out.println("-------------------------------------");

            // Variables to hold treatment details and calculate costs
            String treatment = "";
            double price = 0.0;

            // Assigns treatment type and cost based on selection
            switch (treatmentChoice) {
                case 1:
                    treatment = "Acne Treatment";
                    price = 2750.00;
                    break;
                case 2:
                    treatment = "Skin Whitening";
                    price = 7650.00;
                    break;
                case 3:
                    treatment = "Mole Removal";
                    price = 3850.00;
                    break;
                case 4:
                    treatment = "Laser Treatment";
                    price = 12500.00;
                    break;
                default:
                    System.out.println("Invalid selection. Invoice cannot be generated.");
                    return;
            }

            // Calculation of tax and total amounts
            double tax = price * 0.025;
            double totalBeforeTax = price + 500.00; // Adding fixed registration fee
            double totalWithTax = totalBeforeTax + tax;

            // Rounding total to two decimal places
            totalWithTax = Math.round(totalWithTax * 100.0) / 100.0;

            // Display invoice details including treatment and costs breakdown
            System.out.println("Treatment     : " + treatment);
            System.out.println("Service Fee   : LKR " + price);
            System.out.println("-------------------------------------");
            System.out.println("Registration Fee: LKR 500.00");
            System.out.println("Tax (2.5%)    : LKR " + Math.round(tax * 100.0) / 100.0);
            System.out.println("-------------------------------------");
            System.out.println("TOTAL AMOUNT  : LKR " + totalWithTax);
            System.out.println("-------------------------------------");
            System.out.println("Thank you for choosing Aurora Skin Care.");
            System.out.println("-------------------------------------");

            MainMenu();

        } else {
            // Display message if no appointment matches the given ID
            System.out.println("No appointment found with ID: " + appointmentID);
            MainMenu();
        }
    }
}
