package pl.mvwojcik.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "application_users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessDetails {

    @Id
    @GeneratedValue(generator = "application_user_generation", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "application_user_generation", sequenceName = "app_user_seq", allocationSize = 5, initialValue = 20)
    @Column(name = "id", updatable = false, unique = true)
    private Long id;

    private String username;

    @Column(nullable = false)
    private String password;

    private Boolean active;

    private Boolean locked;

    private String role;
}
