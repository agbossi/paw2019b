package ar.edu.itba.paw.model;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @OneToOne
    @JoinColumn(name = "email")
    @MapsId
    private User user;

    public Admin(User user) {
        this.user = user;
    }

    public Admin(){}

    public String getEmail() {
        return user.getEmail();
    }
}
