package ar.edu.itba.paw.persistence;

public class DoctorQueryBuilder {

    private String query;

    public void buildQuery(String location, String specialty, String clinic){
        StringBuilder query = new StringBuilder("select * from doctors where ");
        query.append( location!=null ? ("location = ? and ") : ("TRUE and ") );
        query.append( specialty!=null ? ("specialty = ? and ") : ("TRUE and ") );
        query.append( clinic!=null ? ("clinic = ?;") : ("TRUE;") );

        this.query = query.toString();
    }

    public String getQuery(){
        return query;
    }
}
