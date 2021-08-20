package ec.com.vaccination.backend.models;

import ec.com.vaccination.backend.constants.SicknessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    private Long id;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private boolean active;

    @NotNull
    private String identification;

    @NotNull
    private String name;

    @NotNull
    private String surName;


    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String email;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private SicknessType sicknessType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id.user_id")
    List<UserRole> roleList;

    public Integer getAge() {
        return LocalDate.now().getYear() - this.birthDate.getYear();
    }

}
