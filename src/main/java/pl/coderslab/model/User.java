package pl.coderslab.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
public class User extends BaseEntity{

    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    private Set<Role> roles;

}