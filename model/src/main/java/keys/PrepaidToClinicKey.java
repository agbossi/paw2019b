package keys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrepaidToClinicKey implements Serializable {

    private String prepaid;

    private int clinic;

    public String getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(String prepaid) {
        this.prepaid = prepaid;
    }

    public int getClinic() {
        return clinic;
    }

    public void setClinic(int clinic) {
        this.clinic = clinic;
    }

    public PrepaidToClinicKey(String prepaid, int clinic) {
        this.prepaid = prepaid;
        this.clinic = clinic;
    }

    public PrepaidToClinicKey() {
    }

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrepaidToClinicKey that = (PrepaidToClinicKey) o;
        return getClinic() == that.getClinic() &&
                getPrepaid().equals(that.getPrepaid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrepaid(), getClinic());
    }
}
