package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "address_id")
    private User user;

    public Address(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Address() {
    }
}
