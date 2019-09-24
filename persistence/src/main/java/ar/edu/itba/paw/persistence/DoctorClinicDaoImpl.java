package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DoctorClinicDao;
import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorClinicDaoImpl implements DoctorClinicDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<DoctorClinic> ROW_MAPPER = new RowMapper<DoctorClinic>() {
        @Override
        public DoctorClinic mapRow(ResultSet resultSet, int i) throws SQLException {
            return new DoctorClinic(new Doctor(resultSet.getString("doctorName"),
                    new Specialty(resultSet.getString("specialty")),
                    resultSet.getString("doctorLicense"),
                    resultSet.getString("phoneNumber")),
                    new Clinic(resultSet.getInt("clinicId"),resultSet.getString("name"),
                            new Location(resultSet.getString("location"))),
                    resultSet.getInt("consultPrice"));
        }
    };

    @Autowired
    public DoctorClinicDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("doctorClinics");

        jdbcTemplate.execute( "CREATE TABLE IF NOT EXISTS doctorclinics("+
                "doctorLicense VARCHAR(20) REFERENCES doctors(license),"+
                "clinicId VARCHAR(20) REFERENCES clinics(id),"+
                "consultPrice INTEGER," +
                "PRIMARY KEY (doctorLicense, clinicid)"+
                ");"
        );
    }

    @Override
    public DoctorClinic createDoctorClinic(Doctor doctor, Clinic clinic, int consultPrice) {
        final Map<String, Object> args = new HashMap<>();
        args.put("doctorLicense", doctor.getLicense());
        args.put("clinicid", clinic.getId());
        args.put("consultPrice", consultPrice);
        int result;

        result = jdbcInsert.execute(args);

        return new DoctorClinic(doctor, clinic, consultPrice);
    }

    @Override
    public List<DoctorClinic> getDoctorClinics() {
        final List<DoctorClinic> list = jdbcTemplate.query("select * from (doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) join clinics on doctorclinics.clinicId = clinics.clinicid ",ROW_MAPPER);
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<DoctorClinic> getDoctorsInClinic(int clinic) {
        final List<DoctorClinic> list = jdbcTemplate.query("select * from (doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) join clinics on doctorclinics.clinicId = clinics.clinicid where clinics.clinicId = ?",ROW_MAPPER, clinic);
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public DoctorClinic getDoctorInClinic(String doctor, int clinic) {
        final List<DoctorClinic> list = jdbcTemplate.query("select * from (doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) join clinics on doctorclinics.clinicId = clinics.clinicid where clinics.clinicid = ? and doctors.license = ?",ROW_MAPPER, clinic, doctor);
        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<DoctorClinic> getClinicsWithDoctor(String doctor) {
        final List<DoctorClinic> list = jdbcTemplate.query("select * from (doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) join clinics on doctorclinics.clinicId = clinics.clinicid where doctors.license = ?",ROW_MAPPER, doctor);
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<DoctorClinic> getFilteredDoctors(final Location location, final Specialty specialty, final int clinic) {

//            DoctorQueryBuilder builder = new DoctorQueryBuilder();
//            builder.buildQuery(location.getLocationName(), specialty.getSpecialtyName(), clinic);
//
//            final List<DoctorClinic> list = jdbcTemplate.query(builder.getQuery(), new PreparedStatementSetter() {
//                @Override
//                public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                    int i=1;
//
//                    if(location!=null){
//                        preparedStatement.setString(i,location.getLocationName());
//                        i++;
//                    }
//                    if(specialty!=null){
//                        preparedStatement.setString(i,specialty.getSpecialtyName());
//                        i++;
//                    }
//                    if(clinic!=null){
//                        preparedStatement.setString(i,clinic);
//                        i++;
//                    }
//                }
//            }, ROW_MAPPER);

        final List<DoctorClinic> list = jdbcTemplate.query("select * from (doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) join clinics on doctorclinics.clinicid = clinics.clinicid where clinics.clinicid = ? and location = ? and specialty = ?",ROW_MAPPER, clinic, location.getLocationName(), specialty.getSpecialtyName());


        return ( list.isEmpty() ? null : list );

    }
}

