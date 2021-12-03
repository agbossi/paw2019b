package ar.edu.itba.paw.webapp.caching;

import ar.edu.itba.paw.interfaces.web.Caching;
import ar.edu.itba.paw.webapp.dto.AppointmentDto;
import ar.edu.itba.paw.webapp.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class AppointmentCaching implements Caching<AppointmentDto> {
    @Override
    public int calculateHash(AppointmentDto element) {
        if(element == null) {
            return 0;
        }
        UserDto patient = element.getPatient();
        LocalDateTime date = element.getDate();
        return Objects.hash(patient.getEmail(), patient.getFirstName(), patient.getLastName(), date);
    }
}
