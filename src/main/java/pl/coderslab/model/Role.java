package pl.coderslab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
public class Role extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy="roles")
    Set<User> users;

}
