package ar.edu.itba.paw.persistence;

public class DoctorQueryBuilder {

    private String query;

    public void buildQuery(String location, String specialty, String firstName, String lastName, String prepaid){
        StringBuilder query = new StringBuilder("select firstName, lastName, specialty, doctorLicense, phoneNumber, doctors.email," +
                " id as clinicid, name, address, location, consultPrice " +
                " from (((doctorclinics natural join doctors) natural join (clinics natural join clinicPrepaids))" +
                " join users on doctors.email = users.email) where ");
        query.append( location!="" ? ("location = ? and ") : ("TRUE and ") );
        query.append( specialty!="" ? ("specialty = ? and ") : ("TRUE and ") );
        query.append( firstName!="" ? ("firstName = ? and ") : ("TRUE and ") );
        query.append( lastName!="" ? ("lastName = ? and ") : ("TRUE and ") );
        if(prepaid!=""){
            query.append("clinicPrepaids.prepaid = ?;");
        }else{
            query.append("consultPrice <= ?;");
        }

        this.query = query.toString();
    }

    public String getQuery(){
        return query;
    }
}


