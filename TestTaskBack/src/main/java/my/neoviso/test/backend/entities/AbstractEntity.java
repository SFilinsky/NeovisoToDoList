package my.neoviso.test.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(of = {"id"})
public abstract class AbstractEntity implements Serializable {

    @JsonProperty("id")
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

}
