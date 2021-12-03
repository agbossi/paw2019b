package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.LocationDao;
import ar.edu.itba.paw.model.Location;
import org.postgresql.core.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository
public class LocationDaoImpl implements LocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final static int MAX_LOCATIONS_PER_PAGE = 20;

    @Override
    public Location getLocationByName(String locationName){
        return entityManager.find(Location.class,locationName);
    }

    @Override
    public Location createLocation(String name){
        Location location = new Location(name);
        entityManager.persist(location);
        return location;
    }

    @Override
    public List<Location> getLocations(){
        TypedQuery<Location> query = entityManager.createQuery("from Location as location ORDER BY location.name",Location.class);
        return query.getResultList();
    }

    @Override
    public List<Location> getPaginatedObjects(int page){

        Query nativeQuery = entityManager.createNativeQuery("SELECT name FROM locations");
        @SuppressWarnings("unchecked")
        List<String> ids = nativeQuery.setFirstResult(page * MAX_LOCATIONS_PER_PAGE)
                .setMaxResults(MAX_LOCATIONS_PER_PAGE)
                .getResultList();

        if(ids.isEmpty()) {
            return Collections.emptyList();
        }

        TypedQuery<Location> query = entityManager.createQuery("from Location as location " +
                "where location.name IN (:filteredLocations) ORDER BY location.name",Location.class);
        query.setParameter("filteredLocations", ids);
        List<Location> list = query.setFirstResult(page * MAX_LOCATIONS_PER_PAGE)
                                   .setMaxResults(MAX_LOCATIONS_PER_PAGE)
                                   .getResultList();
        return list;
    }

    @Override
    public int maxAvailablePage() {
        return (int) (Math.ceil(( ((double)getLocations().size()) / (double)MAX_LOCATIONS_PER_PAGE)));
    }

    @Override
    public long deleteLocation(String name){
        Query query = entityManager.createQuery("delete from Location as location where location.name = :name");
        query.setParameter("name",name);
        return query.executeUpdate();
    }

    @Override
    public void updateLocation(String oldName, String name) {
        final Query query = entityManager.createQuery("update Location as loc set loc.name = :newName where loc.name = :oldName");
        query.setParameter("newName",name);
        query.setParameter("oldName", oldName);
        query.executeUpdate();

    }
}
