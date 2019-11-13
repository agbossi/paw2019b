package ar.edu.itba.paw.persistence;

public class DoctorQueryBuilder {

    private String query;

    public void buildQuery(String location, String specialty, String firstName, String lastName, String prepaid, int consultPrice){
        StringBuilder query = new StringBuilder("select doctorCli from DoctorClinic as doctorCli inner join doctorCli.clinic.prepaids as p where ");
        if(location != ""){
            query.append("doctorCli.clinic.location.name = :location and ");
        }
        if(specialty != ""){
            query.append("doctorCli.doctor.specialty.name = :specialty and ");
        }
        if(firstName != ""){
            query.append("doctorCli.doctor.user.firstName = :firstName and ");
        }
        if(lastName != ""){
            query.append("doctorCli.doctor.user.lastName = :lastName and ");
        }
        if(prepaid != ""){
            query.append("p.prepaid.name = :prepaid and "); //TODO query a list with a value?
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


