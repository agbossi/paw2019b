package ar.edu.itba.paw.webapp.caching;

import ar.edu.itba.paw.interfaces.web.Caching;
import ar.edu.itba.paw.webapp.dto.ImageDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ImageCaching implements Caching<ImageDto> {

    @Override
    public int calculateHash(ImageDto element) {
        if(element == null) {
            return 0;
        }
        return Arrays.hashCode(element.getImage());
    }
}
