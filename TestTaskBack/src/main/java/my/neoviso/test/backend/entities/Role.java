package my.neoviso.test.backend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "role")
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private RoleName name;

}
