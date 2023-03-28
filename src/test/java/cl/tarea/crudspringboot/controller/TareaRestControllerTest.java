package cl.tarea.crudspringboot.controller;

import cl.tarea.crudspringboot.model.Tarea;
import cl.tarea.crudspringboot.service.ITareaService;
import cl.tarea.crudspringboot.service.impl.TareaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
class TareaRestControllerTest {


    @Mock
    private ITareaService tareaService;
    @InjectMocks
    private TareaRestController tareaRestController;
    @Test
    void addTarea() {
        Tarea tareaRequest = new Tarea();
        Tarea tareaResponse = new Tarea();

        tareaRequest.setDescripcion("test");
        tareaRequest.setVigente(true);

        tareaResponse.setId(1);
        tareaResponse.setDescripcion("test");
        tareaResponse.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        when(tareaService.add(tareaRequest)).thenReturn(new ResponseEntity<>(tareaResponse, HttpStatus.CREATED));

        ResponseEntity<Object> response = tareaRestController.addTarea(tareaRequest);

        assertEquals(new ResponseEntity<>(tareaResponse, HttpStatus.CREATED), response);
    }


    @Test
    void getTarea() {

        Long id = 1L;
        Tarea tareaResponse = new Tarea();

        tareaResponse.setId(1L);
        tareaResponse.setDescripcion("test");
        tareaResponse.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        when(tareaService.get(id)).thenReturn(new ResponseEntity<>(tareaResponse, HttpStatus.CREATED));

        ResponseEntity<Object> response = tareaRestController.getTarea(id);

        assertEquals(new ResponseEntity<>(tareaResponse, HttpStatus.CREATED),response);

    }

    @Test
    void getAllTareas() {


        Tarea tareaResponse = new Tarea();
        List<Tarea> tareaList = new ArrayList<>();

        tareaResponse.setId(1L);
        tareaResponse.setDescripcion("test");
        tareaResponse.setVigente(true);
        tareaResponse.setFechaCreacion(new Date());

        tareaList.add(tareaResponse);

        when(tareaService.getAll()).thenReturn(new ResponseEntity<>(tareaList, HttpStatus.CREATED));

        ResponseEntity<Object> response = tareaRestController.getAllTareas();

        assertEquals(new ResponseEntity<>(tareaList, HttpStatus.CREATED), response);

    }

    @Test
    void editTarea() {

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

        when(tareaService.edit(tareaRequest)).thenReturn(new ResponseEntity<>(tareaResponse, HttpStatus.CREATED));

        ResponseEntity<Object> response = tareaRestController.editTarea(tareaRequest);

        assertEquals(new ResponseEntity<>(tareaResponse, HttpStatus.CREATED), response);

    }

    @Test
    void deleteTarea() {
        Long id = 1L;

        when(tareaService.delete(id)).thenReturn(null);

        ResponseEntity<Object> response = tareaRestController.deleteTarea(id);

        assertNull(response);

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}