package ar.edu.itba.paw.webapp.caching;

import ar.edu.itba.paw.interfaces.web.Caching;
import ar.edu.itba.paw.webapp.dto.ClinicDto;
import ar.edu.itba.paw.webapp.dto.DoctorClinicDto;
import ar.edu.itba.paw.webapp.dto.DoctorDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DoctorClinicCaching implements Caching<DoctorClinicDto> {
    @Override
    public int calculateHash(DoctorClinicDto dc) {
        if(dc == null) {
            return 0;
        }

        return Objects.hash(dc.getLicense(), dc.getClinicId());
    }
}
