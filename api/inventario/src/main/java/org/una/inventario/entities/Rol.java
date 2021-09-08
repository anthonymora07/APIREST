package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    @Column(name = "estado")
    private boolean estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private List<Usuario> usuarios= new ArrayList<>();

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {

        fechaCreacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {

        fechaCreacion = new Date();
    }


}
