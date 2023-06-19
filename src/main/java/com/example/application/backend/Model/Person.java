package com.example.application.backend.Model;
import com.example.application.backend.Enums.Race;
import com.example.application.backend.Enums.Sex;
import com.example.application.backend.Enums.ShirtSize;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "person")
public class Person {

    public static final String CURSA_42KM_VALUE = "40$";
    public static final String CURSA_21KM_VALUE = "25$";
    public static final String CURSA_10KM_VALUE = "15$";
    public static final String CURSA_COPII_VALUE = "7$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "shirtSize")
    @Enumerated(EnumType.STRING)
    private ShirtSize shirtSize;

    @Column(name = "race")
    @Enumerated(EnumType.STRING)
    private Race race;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "u_id")
    private User user;

}
