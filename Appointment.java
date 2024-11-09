import java.util.Date;

public class Appointment {
    // Unique identifier for the patient
    private String nic;
    private String name;
    private String email;
    private String phone;
    private Date date; // Appointment date as a Date object
    private String dermatologist; // Dermatologist assigned to the appointment
    private String timeSlot; // Scheduled time slot for the appointment

    // Constructor to initialize all fields of the Appointment
    public Appointment(String nic, String name, String email, String phone, Date date, String dermatologist,
            String timeSlot) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.dermatologist = dermatologist;
        this.timeSlot = timeSlot;
    }

    // Getter and Setter methods for each field
    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(String dermatologist) {
        this.dermatologist = dermatologist;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    // Custom toString method to represent appointment details as a string
    @Override
    public String toString() {
        return "Appointment [NIC=" + nic + ", Name=" + name + ", Email=" + email + ", Phone=" + phone +
                ", Date=" + date + ", Dermatologist=" + dermatologist + ", TimeSlot=" + timeSlot + "]";
    }
}
