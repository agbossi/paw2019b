package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Patient;

import java.util.Calendar;
import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(DoctorClinic doctorClinic, Patient patient, Calendar date);

    List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic);

    List<Appointment> getPatientsAppointments(Patient patient);

    void cancelAppointment(DoctorClinic doctorClinic, Patient patient, Calendar date);

    boolean hasAppointment(DoctorClinic doctorClinic,Calendar date);

    List<Appointment> getAllDoctorsAppointments(Doctor doctor);
}
