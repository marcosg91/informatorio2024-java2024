package com.info.app.proyectoapp.mappers.user;

import com.info.app.proyectoapp.domain.Usuario;
import com.info.app.proyectoapp.dto.user.UsuarioDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        Usuario usuarioCreate = new Usuario();
        usuarioCreate.setId(UUID.randomUUID());
        usuarioCreate.setNombre(usuarioDto.nombre());
        usuarioCreate.setEmail(usuarioDto.email());
        usuarioCreate.setRol(usuarioDto.rol());

        return usuarioCreate;
    }

    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {

        return new UsuarioDto(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getProyecto().getId()
        );

    }
}
