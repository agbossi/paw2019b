package ar.edu.itba.paw.persistence;

import java.util.HashMap;
import java.util.Map;

public class DoctorQueryBuilder {

    private String query;

    private Map<Integer, String> positionalParameters;

    public void buildNativeQuery(String location, String specialty, String firstName,
                                 String lastName, String prepaid, int consultPrice){
        StringBuilder query = new StringBuilder("select distinct doctorLicense from doctorclinics join schedule on doctorclinics.doctorLicense = schedule.doctor and doctorclinics.clinicid = schedule.clinic join doctors on doctorclinics.doctorLicense = doctors.license join users on users.email = doctors.email join clinics on doctorclinics.clinicId = clinics.id join clinicPrepaids on clinicPrepaids.clinicId = doctorclinics.clinicId where ");
        this.positionalParameters = new HashMap<>();
        int position = 0;

        if(!(location.equals(""))){
            query.append("clinics.location = ?1 and ");
            positionalParameters.put(position++, location);
        }
        if(!(specialty.equals(""))){
            query.append("doctors.specialty = ?2 and ");
            positionalParameters.put(position++, specialty);
        }
        if(!(firstName.equals(""))){
            query.append("users.firstName = ?3 and ");
            positionalParameters.put(position++, firstName);
        }
        if(!(lastName.equals(""))){
            query.append("users.lastName = ?4 and ");
            positionalParameters.put(position++, lastName);
        }
        if(!(prepaid.equals(""))){
            query.append("clinicPrepaids.prepaid = ?5");
            positionalParameters.put(position, prepaid);
        } else if(consultPrice > 0){
            query.append("doctorclinics.consultPrice <= ?6");
            positionalParameters.put(position, String.valueOf(consultPrice));
        } else {
            query.append("1=1");
        }
        this.query = query.toString();
    }

    public Map<Integer, String> getPositionalParameters() { return positionalParameters; }

    public void buildQuery(String location, String specialty, String firstName, String lastName, String prepaid, int consultPrice){
        StringBuilder query = new StringBuilder("select doctorCli from DoctorClinic as doctorCli inner join doctorCli.clinic.prepaids as p where ");
        if(!(location.equals(""))){
            query.append("doctorCli.clinic.location.name = :location and ");
        }
        if(!(specialty.equals(""))){
            query.append("doctorCli.doctor.specialty.name = :specialty and ");
        }
        if(!(firstName.equals(""))){
            query.append("doctorCli.doctor.user.firstName = :firstName and ");
        }
        if(!(lastName.equals(""))){
            query.append("doctorCli.doctor.user.lastName = :lastName and ");
        }
        if(!(prepaid.equals(""))){
            query.append("p.prepaid.name = :prepaid");
        }
        else if(consultPrice > 0){
            query.append("doctorCli.consultPrice <= :consultPrice");
        }else {
            query.append("1=1");
        }
        this.query = query.toString();
    }

    public String getQuery(){
        return query;
    }
}


