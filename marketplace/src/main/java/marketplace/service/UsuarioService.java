package marketplace.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marketplace.dto.UsuarioDto;
import marketplace.entity.Usuario;
import marketplace.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<UsuarioDto> findAll() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		List<UsuarioDto> dtos = new ArrayList<UsuarioDto>();

		for (Usuario usuario : usuarios) {

			UsuarioDto dto = new UsuarioDto();

			dto.setId(usuario.getId());
			dto.setNombre(usuario.getNombre());
			dto.setContraseña(usuario.getContraseña());

			dtos.add(dto);
		}

		return dtos;
	}

	public UsuarioDto findById(int id) {

		try {
			Usuario usuario = usuarioRepository.findById(id).get();

			UsuarioDto dto = new UsuarioDto();

			dto.setId(usuario.getId());
			dto.setNombre(usuario.getNombre());
			dto.setContraseña(usuario.getContraseña());

			return dto;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean create(UsuarioDto dto) {

		if (dto == null) {
			return false;
		}
		Usuario usuario = new Usuario();

		usuario.setNombre(dto.getNombre());
		usuario.setContraseña(dto.getContraseña());

		usuarioRepository.save(usuario);
		return true;
	}

	public boolean update(UsuarioDto dto) {
		if (dto == null) {
			return false;
		}
		Usuario usuario = new Usuario();

		usuario.setId(dto.getId());
		usuario.setNombre(dto.getNombre());
		usuario.setContraseña(dto.getContraseña());

		usuarioRepository.save(usuario);
		usuarioRepository.save(usuario);
		return true;
	}
}
