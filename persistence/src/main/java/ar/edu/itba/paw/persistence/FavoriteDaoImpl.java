package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.FavoriteDao;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Favorite;
import ar.edu.itba.paw.model.Patient;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class FavoriteDaoImpl implements FavoriteDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Favorite create(Doctor doctor, Patient patient) {
        Favorite favorite = new Favorite(doctor, patient);
        entityManager.persist(favorite);
        return favorite;
    }

    @Override
    public List<Favorite> getPatientsFavorite(Patient patient) {
        final TypedQuery<Favorite> query = entityManager.createQuery("from Favorite as fav " +
                "where fav.favoriteKey.patient = :email", Favorite.class);
        query.setParameter("email", patient.getEmail());

        final List<Favorite> list = query.getResultList();
        return list;
    }

    @Override
    public boolean isFavorite(Doctor doctor, Patient patient) {
        final TypedQuery<Favorite> query = entityManager.createQuery("from Favorite as fav " +
                "where fav.favoriteKey.patient = :email and fav.favoriteKey.doctor = :license", Favorite.class);
        query.setParameter("email", patient.getEmail());
        query.setParameter("license", doctor.getLicense());

        final List<Favorite> list = query.getResultList();
        return !list.isEmpty();

    }

    @Override
    public void deleteFavorite(Doctor doctor, Patient patient) {
        final Query query = entityManager.createQuery("delete from Favorite as fav " +
                "where fav.favoriteKey.doctor = :license and fav.favoriteKey.patient = :email");

        query.setParameter("license", doctor.getLicense());
        query.setParameter("email", patient.getEmail());
        query.executeUpdate();
    }
}
