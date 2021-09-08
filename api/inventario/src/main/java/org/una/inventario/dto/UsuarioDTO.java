package org.una.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Rol;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private String cedula;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private Departamento departamento;
    private Rol rol;
    private boolean esJefe;

}
