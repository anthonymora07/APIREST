package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Rol;
import org.una.inventario.entities.Usuario;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IRolRepository;
import org.una.inventario.repositories.IUsuarioRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RolServiceImplementation implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    private RolDTO getSavedRolDTO(RolDTO rolDTO) {
        Rol rol = MapperUtils.EntityFromDto(rolDTO, Rol.class);
        if (rol.toString().isEmpty()) throw new NotFoundInformationException();
        Rol rolCreated = rolRepository.save(rol);
        return MapperUtils.DtoFromEntity(rolCreated, RolDTO.class);
    }


    @Override
    public Optional<RolDTO> findById(Long id) {
        Optional<Rol> rol = rolRepository.findById(id);
        if (rol.isEmpty()) throw new NotFoundInformationException();

        RolDTO rolDTO = MapperUtils.DtoFromEntity(rol.get(), RolDTO.class);
        return Optional.ofNullable(rolDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findByEstado(boolean estado) {

        List<Rol> rolList = rolRepository.findByEstado(estado);
        if (rolList.isEmpty()) throw new NotFoundInformationException();

        List<RolDTO> rolDTOList = MapperUtils.DtoListFromEntityList(rolList, RolDTO.class);
        return Optional.ofNullable(rolDTOList);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findByFechaCreacionBetween(Date startDate, Date endDate) {
        List<Rol> rolList = rolRepository.findByFechaCreacionBetween(startDate, endDate);
        if (rolList.isEmpty()) throw new NotFoundInformationException();

        List<RolDTO> rolDTOList = MapperUtils.DtoListFromEntityList(rolList, RolDTO.class);
        return Optional.ofNullable(rolDTOList);
    }


    @Override
    public Optional<RolDTO> create(RolDTO rolDTO) {
        return Optional.ofNullable(getSavedRolDTO(rolDTO));
    }

    @Override
    @Transactional
    public Optional<RolDTO> update(RolDTO rolDTO, Long id) {
        if (rolRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedRolDTO(rolDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        rolRepository.deleteAll();
    }


}
