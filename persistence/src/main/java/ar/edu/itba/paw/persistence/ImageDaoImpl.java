package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.ImageDao;
import ar.edu.itba.paw.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long createProfileImage(byte[] image, String doctor) {
        Image im = new Image();
        im.setImage(image);
        im.setLicense(doctor);
        entityManager.persist(image);
        return im.getId();
    }

    @Override
    public Image getProfileImage(String doctor){
        TypedQuery<Image> query = entityManager.createQuery("from Image as image where image.doctor.license = :doctor",Image.class);
        query.setParameter("doctor",doctor);
        List<Image> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public long updateProfileImage(byte[] image, String doctor){
        Query query = entityManager.createQuery("update Image as im set im.image = :image where im.doctor.license = :doctor");
        query.setParameter("image",image);
        query.setParameter("doctor",doctor);
        return query.executeUpdate();
    }

}
