package pl.coderslab.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class BaseEntity  {

    @Id
    @GeneratedValue
    protected Long id;

}
