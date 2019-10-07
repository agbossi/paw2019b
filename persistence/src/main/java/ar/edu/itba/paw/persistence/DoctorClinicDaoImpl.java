package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.DoctorClinicDao;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorClinicDaoImpl implements DoctorClinicDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<DoctorClinic> ROW_MAPPER = new RowMapper<DoctorClinic>() {
        @Override
        public DoctorClinic mapRow(ResultSet resultSet, int i) throws SQLException {
            return new DoctorClinic(new Doctor(resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    new Specialty(resultSet.getString("specialty")),
                    resultSet.getString("doctorLicense"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("email")),
                    new Clinic(resultSet.getInt("clinicid"),
                            resultSet.getString("name"),
                            resultSet.getString("address"),
                            new Location(resultSet.getString("location"))),
                    resultSet.getInt("consultPrice"));
        }
    };

    @Autowired
    public DoctorClinicDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("doctorClinics");
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
        final List<DoctorClinic> list = jdbcTemplate.query("select firstName,lastName,specialty,doctorLicense,phoneNumber,doctors.email," +
                " clinicid,name,address,location,consultPrice" +
                " from ((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license)" +
                " join clinics on doctorclinics.clinicid = clinics.id)" +
                " join users on doctors.email = users.email",ROW_MAPPER);
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<DoctorClinic> getDoctorClinicsForDoctor(Doctor doctor) {
        final List<DoctorClinic> list = jdbcTemplate.query(
           "select firstName,lastName,specialty,doctorLicense,phoneNumber,doctors.email," +
                " clinicid,name,address,location,consultPrice" +
                " from ((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license)" +
                " join clinics on doctorclinics.clinicid = clinics.id)" +
                " join users on doctors.email = users.email" +
                " where doctorclinics.doctorLicense = ?",ROW_MAPPER, doctor.getLicense());
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<DoctorClinic> getDoctorsInClinic(int clinic) {
        final List<DoctorClinic> list = jdbcTemplate.query("select firstName,lastName,specialty,doctorLicense,phoneNumber,doctors.email," +
                " clinicid,name,address,location,consultPrice" +
                " from ((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license)" +
                " join clinics on doctorclinics.clinicid = clinics.id)" +
                " join users on doctors.email = users.email where clinicid = ?",ROW_MAPPER, clinic);
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public DoctorClinic getDoctorInClinic(String doctor, int clinic) {
        final List<DoctorClinic> list = jdbcTemplate.query("select firstName,lastName,specialty,doctorLicense,phoneNumber,doctors.email," +
                " clinicid,name,address,location,consultPrice" +
                " from ((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license)" +
                " join clinics on doctorclinics.clinicid = clinics.id)" +
                " join users on doctors.email = users.email where clinicid = ? and doctors.license = ?",ROW_MAPPER, clinic, doctor);
        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<DoctorClinic> getClinicsWithDoctor(String doctor) {
        final List<DoctorClinic> list = jdbcTemplate.query("select firstName,lastName,specialty,doctorLicense,phoneNumber,doctors.email," +
                " clinicid,name,address,location,consultPrice" +
                " from ((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license)" +
                " join clinics on doctorclinics.clinicid = clinics.id)" +
                " join users on doctors.email = users.email where doctors.license = ?",ROW_MAPPER, doctor);
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<DoctorClinic> getFilteredDoctors(final Location location, final Specialty specialty,
                                                final String firstName, final String lastName, final Prepaid prepaid,
                                                 final int consultPrice) {

        DoctorQueryBuilder builder = new DoctorQueryBuilder();
        builder.buildQuery(location.getLocationName(),specialty.getSpecialtyName(), firstName, lastName, prepaid.getName(), consultPrice);

        List<DoctorClinic> list = jdbcTemplate.query(builder.getQuery(), new PreparedStatementSetter() {

            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                int i =1;
                if (location.getLocationName() != ""){
                    preparedStatement.setString(i, location.getLocationName());
                    i++;
                }
                if(specialty.getSpecialtyName() != ""){
                    preparedStatement.setString(i, specialty.getSpecialtyName());
                    i++;
                }
                if(firstName != ""){
                    preparedStatement.setString(i, firstName);
                    i++;
                }
                if(lastName != ""){
                    preparedStatement.setString(i, lastName);
                    i++;
                }
                if(prepaid.getName() != ""){
                    preparedStatement.setString(i, prepaid.getName());
                }else{
                    if(consultPrice > 0){
                        preparedStatement.setInt(i, consultPrice);
                    }
                }
            }
        },ROW_MAPPER);


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

//        final List<DoctorClinic> list;
//        if(prepaid != null){
//             list = jdbcTemplate.query("select firstName,lastName,specialty,doctorLicense,phoneNumber,doctors.email," +
//                    " clinic.clinicid,name,address,location,consultPrice " +
//                    " from (((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license)" +
//                    " join clinics on doctorclinics.clinicid = clinics.id)" +
//                    " join users on doctors.email = users.email)" +
//                    " join clinicPrepaids on clinicPrepaids.clinicid = clinics.id" +
//                    "where location = ? and specialty = ? and firstName = ? and lastName = ? and clinicPrepaids.prepaid = ?",ROW_MAPPER, location.getLocationName(), specialty.getSpecialtyName(),(firstName != null ? firstName:true),(lastName != null ? lastName:true), prepaid.getName());
//
//
//
//        }else {
//             list = jdbcTemplate.query("select firstName,lastName,specialty,doctorLicense,phoneNumber,doctors.email," +
//                    " clinic.clinicid,name,address,location,consultPrice " +
//                    " from ((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license)" +
//                    " join clinics on doctorclinics.clinicid = clinics.id)" +
//                    " join users on doctors.email = users.email" +
//                    "where location = ? and specialty = ? and firstName = ? and lastName = ? and consultPrice <= ?",ROW_MAPPER, location.getLocationName(), specialty.getSpecialtyName(),(firstName != null ? firstName:true),(lastName != null ? lastName:true), consultPrice);
//        }
        return ( list.isEmpty() ? null : list );
    }
}

