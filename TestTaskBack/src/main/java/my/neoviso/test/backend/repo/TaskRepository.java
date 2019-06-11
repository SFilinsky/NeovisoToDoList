package my.neoviso.test.backend.repo;

import my.neoviso.test.backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE LOWER(t.owner) = LOWER(:username)")
    List<Task> findTasks(@Param("username") String username);

    @Query("SELECT t FROM Task t WHERE LOWER(t.owner) = LOWER(:username) AND t.id = :id")
    Optional<Task> findById(@Param("username") String username, @Param("id") Long id);

    @Query("SELECT t FROM Task t WHERE t.id = :id")
    Optional<Task> findById(@Param("id") Long id);

}                           
