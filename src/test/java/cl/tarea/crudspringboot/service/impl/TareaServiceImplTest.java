package cl.tarea.crudspringboot.service.impl;

import cl.tarea.crudspringboot.constant.Constants;
import cl.tarea.crudspringboot.model.ErrorResponse;
import cl.tarea.crudspringboot.model.Tarea;
import cl.tarea.crudspringboot.repository.ITareaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@SpringBootTest()
class TareaServiceImplTest {

    @Mock
    private ITareaRepository tareaRepository;
    @InjectMocks
    private TareaServiceImpl tareaService;

    @Test
    void add() {

        Tarea tareaRequest = new Tarea();
        Tarea tareaResponse = new Tarea();

        tareaRequest.setDescripcion("test");
        tareaRequest.setVigente(true);

        tareaResponse.setId(1);
        tareaResponse.setDescripcion("test");
        tareaResponse.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        when(tareaRepository.save(tareaRequest)).thenReturn(tareaResponse);

        ResponseEntity<Object> response = tareaService.add(tareaRequest);

        assertEquals(new ResponseEntity<>(tareaResponse, HttpStatus.CREATED), response);

    }

    @Test
    void addExist() {

        Tarea tareaResponse = new Tarea();
        ErrorResponse error = new ErrorResponse("Error", BAD_REQUEST.value());
        error.addError(Constants.getTareaExistError());

        tareaResponse.setId(1);
        tareaResponse.setDescripcion("test");
        tareaResponse.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        when(tareaRepository.findById(tareaResponse.getId())).thenReturn(Optional.of(tareaResponse));

        ResponseEntity<Object> response = tareaService.add(tareaResponse);
        ResponseEntity<Object> expected = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        assertEquals(expected.getStatusCode(),response.getStatusCode());
        assertEquals(expected.getStatusCode().value(),response.getStatusCode().value());

    }


    @Test
    void edit() {

        Tarea tareaRequest = new Tarea();
        Tarea tareaResponse = new Tarea();
        tareaRequest.setId(1);
        tareaRequest.setDescripcion("test");
        tareaRequest.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        tareaResponse.setId(1);
        tareaResponse.setDescripcion("test2");
        tareaResponse.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        when(tareaRepository.save(tareaRequest)).thenReturn(tareaResponse);

        ResponseEntity<Object> response = tareaService.edit(tareaRequest);

        assertEquals(new ResponseEntity<>(tareaResponse, HttpStatus.OK), response);

    }

    @Test
    void get() {

        Long id = 1L;
        Tarea tareaResponse = new Tarea();

        tareaResponse.setId(1L);
        tareaResponse.setDescripcion("test");
        tareaResponse.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        when(tareaRepository.findById(id)).thenReturn(Optional.of(tareaResponse));

        ResponseEntity<Object> response = tareaService.get(id);

        assertEquals(new ResponseEntity<>(Optional.of(tareaResponse), HttpStatus.FOUND),response);


    }

    @Test
    void getAll() {

        Tarea tareaResponse = new Tarea();
        List<Tarea> tareaList = new ArrayList<>();

        tareaResponse.setId(1L);
        tareaResponse.setDescripcion("test");
        tareaResponse.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        tareaList.add(tareaResponse);

        when(tareaRepository.findAll()).thenReturn(tareaList);

        ResponseEntity<Object> response = tareaService.getAll();

        assertEquals(new ResponseEntity<>(tareaList, HttpStatus.FOUND), response);

    }

    @Test
    void delete() {

        Long id = 1L;

        ResponseEntity<Object> response = tareaService.delete(id);

        assertEquals(new ResponseEntity<>(HttpStatus.OK), response);
        verify(tareaRepository).deleteById(id);

    }
}