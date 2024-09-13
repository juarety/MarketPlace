package marketplace.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marketplace.dto.UsuarioDto;
import marketplace.service.UsuarioService;

@RestController
@RequestMapping("marketplace/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> findAll() {

		return new ResponseEntity<List<UsuarioDto>>(usuarioService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable int id) {

		UsuarioDto response = usuarioService.findById(id);
		if (response != null) {
			return new ResponseEntity<UsuarioDto>(usuarioService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid UsuarioDto dto) {

		boolean response = usuarioService.create(dto);

		if (response) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@RequestBody @Valid UsuarioDto dto) {

		boolean response = usuarioService.update(dto);

		if (response) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

}
