package ar.edu.itba.paw.service;
/*
import ar.edu.itba.paw.interfaces.dao.LocationDao;
import ar.edu.itba.paw.model.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {
    private static final String name = "location";

    private static final String name2 = "location2";

    @InjectMocks
    private LocationServiceImpl locationService = new LocationServiceImpl();

    @Mock
    private LocationDao mockDao;

    @Test
    public void testCreate(){
        //Set Up
        Mockito.when(mockDao.createLocation(Mockito.eq(name)))
                .thenReturn(new Location(name));

        //Execute
        Location location = locationService.createLocation(name);

        //Assert
        Assert.assertNotNull(location);
        Assert.assertEquals(name, location.getLocationName());
    }

    @Test
    public void testGetLocationByName(){
        //Set Up
        Mockito.when(mockDao.getLocationByName(Mockito.eq(name)))
                .thenReturn(new Location(name));

        //Execute
        Location location = locationService.getLocationByName(name);

        //Assert
        Assert.assertNotNull(location);
        Assert.assertEquals(name, location.getLocationName());
    }
}
*/