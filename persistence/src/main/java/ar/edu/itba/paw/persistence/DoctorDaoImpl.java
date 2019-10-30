package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.DoctorDao;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.model.User;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.print.Doc;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorDaoImpl implements DoctorDao {
   /* private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Doctor> ROW_MAPPER = new RowMapper<Doctor>() {
        @Override
        public Doctor mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Doctor(resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                   new Specialty(resultSet.getString("specialty")),
                    resultSet.getString("license"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("email")
            );
        }
    };

    @Autowired
    public DoctorDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                         .withTableName("doctors");
    }

    @Override
    public Doctor createDoctor(final Specialty specialty,final String license, final String phoneNumber, final String email) {
        final Map<String, Object> args = new HashMap<>();
        args.put("specialty", specialty.getSpecialtyName());
        args.put("license", license);
        args.put("phoneNumber", phoneNumber);
        args.put("email",email);
        int result;

        result = jdbcInsert.execute(args);

        return this.getDoctorByLicense(license);
    }

    @Override
    public List<Doctor> getDoctors() {
        return jdbcTemplate.query("select specialty,license,phoneNumber,doctors.email,firstName,lastName " +
                "from doctors join users on doctors.email = users.email",ROW_MAPPER);
    }


    @Override
    public List<Doctor> getDoctorByName(String firstName,String lastName) {
        final List<Doctor> list = jdbcTemplate.query("select specialty,license,phoneNumber,doctors.email,firstName,lastName " +
                "from doctors join users on doctors.email = users.email where firstName = ? and lastName = ?",ROW_MAPPER,firstName,lastName);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<Doctor> getDoctorBySpecialty(Specialty specialty) {
        final List<Doctor> list = jdbcTemplate.query("select specialty,license,phoneNumber,doctors.email,firstName,lastName " +
                               "from doctors join users on doctors.email = users.email where specialty = ?",ROW_MAPPER,specialty.getSpecialtyName());
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public Doctor getDoctorByLicense(String license) {
        final List<Doctor> list = jdbcTemplate.query("select specialty,license,phoneNumber,doctors.email,firstName,lastName"  +
                                               " from doctors join users on doctors.email = users.email where license = ?",ROW_MAPPER,license);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean isDoctor(String email) {
        final List<Doctor> list = jdbcTemplate.query("select specialty,license,phoneNumber,doctors.email,firstName,lastName" +
                " from doctors join users on doctors.email = users.email where doctors.email = ?",ROW_MAPPER,email);
        return !list.isEmpty();
    }

    @Override
    public Doctor getDoctorByEmail(String email) {
        final List<Doctor> list = jdbcTemplate.query("select specialty,license,phoneNumber,doctors.email,firstName,lastName" +
                " from doctors join users on doctors.email = users.email where doctors.email = ?", ROW_MAPPER, email);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public long deleteDoctor(String license) {
        String deleteQuery = "DELETE FROM doctors WHERE license = license";
        return jdbcTemplate.update(deleteQuery, license);
    }

    @Override
    public void updateDoctor(String license, Map<String, String> args) {
            jdbcTemplate.update("update doctors set phoneNumber = ?, specialty = ? where license = ?",
                    args.get("phoneNumber"),
                    args.get("specialty"),
                    license);
    }


    */
    //hibernate
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Doctor createDoctor(final Specialty specialty, final String license, final String phoneNumber, User user){
        //este persist toca usuario?
        final Doctor doctor = new Doctor(specialty,license,phoneNumber,user);
        entityManager.persist(doctor);
        return doctor;
    }

    @Override
    public List<Doctor> getDoctors(){
        final TypedQuery<Doctor> query = entityManager.createQuery("from Doctor as doctor inner join doctor.user",Doctor.class);
        final List<Doctor> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public Doctor getDoctorByLicense(String license) {
        return entityManager.find(Doctor.class,license);
    }

    @Override
    public List<Doctor> getDoctorByName(String firstName,String lastName) {
        final TypedQuery<Doctor> query = entityManager.createQuery("from Doctor as doctor inner join doctor.user " +
                "where doctor.firstName := firstName and doctor.lastName := lastName",Doctor.class);

        query.setParameter("firstName",firstName);
        query.setParameter("lastName", lastName);
        final List<Doctor> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }
    //TODO se accede a campo name de specialty asi o o paso el objeto specialty?
    @Override
    public List<Doctor> getDoctorBySpecialty(Specialty specialty){
        final TypedQuery<Doctor> query = entityManager.createQuery("from Doctor as doctor inner join doctor.user " +
                "where doctor.specialty.name := specialty",Doctor.class);

        query.setParameter("specialty",specialty.getSpecialtyName());
        final List<Doctor> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean isDoctor(String email) {
        final TypedQuery<Doctor> query = entityManager.createQuery("from Doctor as doctor inner join doctor.user " +
                "where doctor.email := email",Doctor.class);

        query.setParameter("email",email);
        final List<Doctor> list = query.getResultList();
        return !list.isEmpty();
    }

    @Override
    public Doctor getDoctorByEmail(String email){
        final TypedQuery<Doctor> query = entityManager.createQuery("from Doctor as doctor inner join doctor.user " +
                "where doctor.email := email",Doctor.class);

        query.setParameter("email",email);
        final List<Doctor> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int deleteDoctor(String license) {
       /* String qryString3 = "delete from Doctor d where d.license=:license";
        Query query3 = session.createQuery(qryString3);
        query3.setParameter("license", license);
        int count3 = query3.executeUpdate(); */

        final TypedQuery<Doctor> query = entityManager.createQuery("delete from Doctor as doctor where doctor.license =: license",Doctor.class);
        query.setParameter("license",license);
        return query.executeUpdate();
    }

    @Override
    public void updateDoctor(String license, Map<String, String> args){
        final TypedQuery<Doctor> query = entityManager.createQuery("update Doctor doctor set doctor.phoneNumber =: phoneNumber, doctor.specialty =: specialty where doctor.license =: license", Doctor.class);
        query.setParameter("license",license);
        query.setParameter("specialty",args.get("specialty"));
        query.setParameter("phoneNumber",args.get("phoneNumber"));
        query.executeUpdate();
    }
}
