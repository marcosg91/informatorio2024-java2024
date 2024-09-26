package com.info.app.proyectoapp.mappers.user;

import com.info.app.proyectoapp.domain.Usuario;
import com.info.app.proyectoapp.dto.user.UsuarioDto;

public interface UserMapper {

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
}
