package cl.tarea.crudspringboot.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "La descripción no puede  faltar")
    private String descripcion;

    @CreationTimestamp
    private Date fechaCreacion;

    @NotNull(message = "Vigente no puede faltar")
    @Column(nullable = false)
    private boolean vigente;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
