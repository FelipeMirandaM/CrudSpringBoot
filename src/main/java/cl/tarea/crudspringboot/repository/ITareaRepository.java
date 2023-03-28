package cl.tarea.crudspringboot.repository;

import cl.tarea.crudspringboot.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITareaRepository extends JpaRepository<Tarea, Long> {
}
