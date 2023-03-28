package cl.tarea.crudspringboot.service.impl;


import cl.tarea.crudspringboot.constant.Constants;
import cl.tarea.crudspringboot.model.ErrorResponse;
import cl.tarea.crudspringboot.model.Tarea;
import cl.tarea.crudspringboot.repository.ITareaRepository;
import cl.tarea.crudspringboot.service.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class TareaServiceImpl implements ITareaService {


    @Autowired
    private ITareaRepository tareaRepository;
    @Override
    public ResponseEntity<Object> add(Tarea tarea) {
        if(tareaRepository.findById(tarea.getId()).isPresent()){
            ErrorResponse error = new ErrorResponse("Error", BAD_REQUEST.value());
            error.addError(Constants.getTareaExistError());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tareaRepository.save(tarea), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> edit(Tarea tarea) {

        return new ResponseEntity<>(tareaRepository.save(tarea), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> get(Long id) {
        return new ResponseEntity<>(tareaRepository.findById(id), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Object> getAll() {

        return new ResponseEntity<>(tareaRepository.findAll(), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        tareaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
