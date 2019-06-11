package my.neoviso.test.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task extends AbstractEntity{

   @NotBlank
   private String text;
   @NotBlank
   private String owner;
   private Boolean isDone = false;

}
