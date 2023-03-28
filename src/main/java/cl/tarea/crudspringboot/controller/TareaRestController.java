package cl.tarea.crudspringboot.controller;

import cl.tarea.crudspringboot.model.Tarea;
import cl.tarea.crudspringboot.service.ITareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/Tarea")
public class TareaRestController {

    @Autowired
    private ITareaService tareaService;

    @PostMapping
    public ResponseEntity<Object> addTarea(@Valid @RequestBody Tarea tarea) {

        return tareaService.add(tarea);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTarea(@PathVariable Long id) {

        return tareaService.get(id);

    }

    @GetMapping
    public ResponseEntity<Object> getAllTareas() {

        return tareaService.getAll();

    }

    @PatchMapping
    public ResponseEntity<Object> editTarea(@Valid @RequestBody Tarea tarea) {

        return tareaService.edit(tarea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTarea(@PathVariable Long id) {

        return tareaService.delete(id);

    }

}
