package be.bnair.lovehotel.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import be.bnair.lovehotel.utils.UserEnumRole;

@Getter @Setter
@NoArgsConstructor
@Entity (name = "lh_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "address_love_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private UserEnumRole role;

    private LocalDate birthDate;
    private boolean enabled;

    public User(String firstName, String lastName, String email, String password, LocalDate birth) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birth;
        this.enabled = true;
    }
}
