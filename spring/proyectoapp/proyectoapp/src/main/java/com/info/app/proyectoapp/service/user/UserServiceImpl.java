package com.info.app.proyectoapp.service.user;

import com.info.app.proyectoapp.domain.Proyecto;
import com.info.app.proyectoapp.domain.Usuario;
import com.info.app.proyectoapp.dto.user.UsuarioDto;
import com.info.app.proyectoapp.mappers.user.UserMapper;
import com.info.app.proyectoapp.repository.proyecto.ProyectoRepository;
import com.info.app.proyectoapp.repository.usuario.UsuarioRepository;
import com.info.app.proyectoapp.service.proyecto.ProyectoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;

    private ProyectoService proyectoService;

    private ProyectoRepository proyectoRepository;

    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto createUser(UsuarioDto usuario) {

        Usuario usuarioCreated = userMapper.usuarioDtoToUsuario(usuario);

        //buscar por id el proyecto:

        Proyecto proyecto = proyectoService.getProyectoById( usuario.idProyecto());

        usuarioCreated.setProyecto(proyecto);

        proyecto.setUsuarioByRol(usuarioCreated);

        proyectoRepository.save(proyecto);
        return userMapper.usuarioToUsuarioDto( usuarioRepository.save( usuarioCreated ) );

        //guardar entidad Usuario
    }

}
