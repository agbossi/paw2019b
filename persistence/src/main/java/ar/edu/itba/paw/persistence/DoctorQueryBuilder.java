package ar.edu.itba.paw.persistence;

public class DoctorQueryBuilder {

    private String query;

    public void buildQuery(String location, String specialty, String firstName, String lastName, String prepaid, int consultPrice){
        StringBuilder query = new StringBuilder("from DoctorClinic as doctorCli where ");
        query.append( location != "" ? ("doctorCli.clinic.location.name = :location and ") : ("TRUE and ") );
        query.append( specialty != "" ? ("doctorCli.doctor.specialty.name = :specialty and ") : ("TRUE and ") );
        query.append( firstName != "" ? ("doctorCli.doctor.firstName = :firstName and ") : ("TRUE and ") );
        query.append( lastName != "" ? ("doctorCli.doctor.lastName = :lastName ") : ("TRUE and ") );
        if(prepaid != ""){
            query.append("doctorCli.clinic.prepaids.prepaid = :prepaid"); //TODO query a list with a value?
        }else if(consultPrice > 0){
            query.append("doctorCli.consultPrice <= :consultPrice");
        }else{
            query.append("TRUE");
        }

        this.query = query.toString();
    }

    public String getQuery(){
        return query;
    }
}


