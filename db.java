import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class db {

    // In-memory storage for appointments, using appointmentID as the key
    private static Map<Integer, Appointment> appointmentDB = new HashMap<>();

    // Adds a new appointment to the database
    public static void addAppointment(int appointmentID, Appointment appointment) {
        appointmentDB.put(appointmentID, appointment);
    }

    // Retrieves an appointment by its ID
    public static Appointment getAppointment(int appointmentID) {
        return appointmentDB.get(appointmentID);
    }

    // Updates an existing appointment by its ID
    public static void updateAppointment(int appointmentID, Appointment updatedAppointment) {
        appointmentDB.put(appointmentID, updatedAppointment);
    }

    // Searches for an appointment by patient's name (case-insensitive)
    public static Appointment searchAppointmentByName(String patientName) {
        for (Appointment appointment : appointmentDB.values()) {
            if (appointment.getName().equalsIgnoreCase(patientName)) {
                return appointment;
            }
        }
        return null;
    }

    // Searches for an appointment by date
    public static Appointment searchAppointmentByDate(Date appointmentDate) {
        for (Appointment appointment : appointmentDB.values()) {
            if (appointment.getDate().equals(appointmentDate)) {
                return appointment;
            }
        }
        return null;
    }

    // Retrieves all appointments
    public static Map<Integer, Appointment> getAllAppointments() {
        return appointmentDB;
    }
}
