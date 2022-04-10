package ar.edu.itba.paw.webapp.caching;

import ar.edu.itba.paw.interfaces.web.Caching;
import ar.edu.itba.paw.webapp.dto.FavoriteDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FavoriteCaching implements Caching<FavoriteDto> {
    @Override
    public int calculateHash(FavoriteDto doctor) {
        if(doctor == null)
            return 0;
        return Objects.hash(doctor.getLicense());
    }
}
