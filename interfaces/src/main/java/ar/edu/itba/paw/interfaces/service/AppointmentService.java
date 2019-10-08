package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.*;

import java.util.Calendar;
import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(DoctorClinic doctorClinic, User patient, Calendar date);

    List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic);

    List<Appointment> getPatientsAppointments(User patient);

    void cancelAppointment(DoctorClinic doctorClinic, User patient, Calendar date,boolean cancelledByDoctor);

    Appointment hasAppointment(DoctorClinic doctorClinic,Calendar date);

    boolean hasAppointment(String doctorLicense,String patientEmail,Calendar date);

    List<Appointment> getAllDoctorsAppointments(Doctor doctor);

    void cancelAllAppointmentsOnSchedule(DoctorClinic doctorClinic, int day, int hour);
}
