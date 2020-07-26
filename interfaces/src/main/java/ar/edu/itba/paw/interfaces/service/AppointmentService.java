package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.*;

import java.util.Calendar;
import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(String license, int clinicId, String patientEmail, int year, int month, int day, int time);

    List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic);

    List<Appointment> getPatientsAppointments(User patient);

    void cancelAppointment(String license, int clinicId, String userEmail, int year, int month, int day, int time, boolean cancelledByDoctor);

    Appointment hasAppointment(DoctorClinic doctorClinic,Calendar date);

    boolean hasAppointment(String doctorLicense,String patientEmail,Calendar date);

    List<Appointment> getAllDoctorsAppointments(Doctor doctor);

    List<Appointment> getDoctorAppointmentsWithinWeek(Doctor doctor, Calendar beginning, Calendar end);

    void cancelAllAppointmentsOnSchedule(DoctorClinic doctorClinic, int day, int hour);
}
