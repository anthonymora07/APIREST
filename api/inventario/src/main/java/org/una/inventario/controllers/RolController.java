package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.services.IRolService;
import org.una.inventario.services.IUsuarioService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@Api(tags = {"Roles"})

public class RolController {

    @Autowired(required = false)

    private IRolService rolService;

    @ApiOperation(value = "Obtiene un rol a partir de su id", response = RolDTO.class, tags = "Roles")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<RolDTO> rolFound = rolService.findById(id);
        return new ResponseEntity<>(rolFound, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene una lista de roles a partir de su estado", response = RolDTO.class, tags = "Roles")
    @GetMapping("/{estado}")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        Optional<List<RolDTO>> rolFound = rolService.findByEstado(estado);
        return new ResponseEntity<>(rolFound, HttpStatus.OK);

    }

    @ApiOperation(value = "Obtiene los roles entre fechas", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    @GetMapping("/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<?> findByFechaCreacionBetween(@PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate") Date endDate) {
        try {
            Optional<List<RolDTO>> result = rolService.findByFechaCreacionBetween(startDate, endDate);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody RolDTO rolDTO) {
        try {
            Optional<RolDTO> rolCreated = rolService.create(rolDTO);
            return new ResponseEntity<>(rolCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RolDTO rolModified) {
        try {
            Optional<RolDTO> rolUpdated = rolService.update(rolModified, id);
            if (rolUpdated.isPresent()) {
                return new ResponseEntity<>(rolUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            rolService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
//        //TODO: Implementar este método
        throw new Exception("Not implemented Function");

    }



}
