package ar.edu.itba.paw.persistence;

public class DoctorQueryBuilder {

    private String query;

    public void buildQuery(String location, String specialty, String clinic){
        StringBuilder query = new StringBuilder("select * from (doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) join clinics on doctorclinics.clinicname = clinics.name where ");
        query.append( location!=null ? ("location = ? and ") : ("TRUE and ") );
        query.append( clinic!=null ? ("clinicname = ? and ") : ("TRUE and ") );
        query.append( specialty!=null ? ("specialty = ?;") : ("TRUE;") );

        this.query = query.toString();
    }

    public String getQuery(){
        return query;
    }
}
