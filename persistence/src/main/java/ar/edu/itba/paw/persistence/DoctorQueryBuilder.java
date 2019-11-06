package ar.edu.itba.paw.persistence;

public class DoctorQueryBuilder {

    private String query;

    public void buildQuery(String location, String specialty, String firstName, String lastName, String prepaid, int consultPrice){
        StringBuilder query = new StringBuilder("select firstName, lastName, specialty, doctorLicense, phoneNumber, doctors.email," +
                " id as clinicid, name, address, location, consultPrice " +
                " from (((doctorclinics join doctors on doctorLicense = license) natural join (clinics join clinicPrepaids on clinicPrepaids.clinicid = clinics.id))" +
                " join users on doctors.email = users.email) where ");
        query.append( location!="" ? ("location = ? and ") : ("TRUE and ") );
        query.append( specialty!="" ? ("specialty = ? and ") : ("TRUE and ") );
        query.append( firstName!="" ? ("firstName = ? and ") : ("TRUE and ") );
        query.append( lastName!="" ? ("lastName = ? and ") : ("TRUE and ") );
        if(prepaid!=""){
            query.append("clinicPrepaids.prepaid = ?;");
        }else if(consultPrice > 0){
            query.append("consultPrice <= ?;");
        }else{
            query.append("TRUE;");
        }

        this.query = query.toString();
    }

    public String getQuery(){
        return query;
    }
}


