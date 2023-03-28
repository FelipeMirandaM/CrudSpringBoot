package cl.tarea.crudspringboot.service;

import cl.tarea.crudspringboot.model.Tarea;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface ITareaService {

    public ResponseEntity<Object> add(@Valid Tarea tarea);

    public ResponseEntity<Object> edit(Tarea tarea);

    public ResponseEntity<Object> get(Long id);

    public ResponseEntity<Object> getAll();

    public ResponseEntity<Object> delete(Long id);

}
