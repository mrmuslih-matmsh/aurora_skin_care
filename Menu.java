import java.util.Scanner;

public class Menu {

    // Main menu method to display options and handle user selection
    public static void MainMenu() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------");
        System.out.println("Welcome to Aurora Skin Care");
        System.out.println("---------------------------");
        System.out.println("01 - Make New Appointment");
        System.out.println("02 - Treatment Types and Prices");
        System.out.println("03 - Update Appointment");
        System.out.println("04 - Search Appointment");
        System.out.println("05 - View Appointments by Date");
        System.out.println("06 - Generate Invoice");
        System.out.println("07 - Exit");

        // Prompt user for menu selection
        System.out.print("Enter your selection: ");
        int selection = scanner.nextInt();

        // Execute corresponding method based on user's choice
        switch (selection) {
            case 01:
                NewAppointment.New_Appointment();
                break;
            case 02:
                Treatment.TreatmentTypesPrices();
                break;
            case 03:
                UpdateAppointment.Update_Appointment();
                break;
            case 04:
                SearchAppointment.Search_Appointment();
                break;
            case 05:
                ViewAppointment.View_Appointment();
                break;
            case 06:
                Invoice.GenerateInvoice();
                break;
            case 07:
                System.exit(0); // Exit the program
                break;
            default:
                MainMenu(); // Rerun menu on invalid selection
                break;
        }
    }
}
