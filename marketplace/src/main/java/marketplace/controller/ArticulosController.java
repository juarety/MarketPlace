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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import marketplace.dto.ArticuloDto;
import marketplace.dto.PedidoDto;
import marketplace.dto.UsuarioDto;
import marketplace.entity.Articulo;

import marketplace.service.ArticulosService;

@RestController
@RequestMapping("marketplace/articulos")
public class ArticulosController {

	@Autowired
	ArticulosService articulosService;

	@GetMapping("/{nombreParcial}/buscar/")
	public ResponseEntity<List<Articulo>> buscarArticulos(@PathVariable String nombreParcial) {

		List<Articulo> response = articulosService.buscarArticulosPorNombre(nombreParcial);
		if (!response.isEmpty()) {
			return new ResponseEntity<List<Articulo>>(articulosService.buscarArticulosPorNombre(nombreParcial),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<List<Articulo>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<ArticuloDto> findById(@PathVariable int id) {

		ArticuloDto response = articulosService.findById(id);

		if (response != null) {
			return new ResponseEntity<ArticuloDto>(articulosService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<ArticuloDto>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid ArticuloDto dto) {

		boolean response = articulosService.create(dto);

		if (response) {
			return new ResponseEntity<String>(HttpStatus.CREATED);

		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@RequestBody @Valid ArticuloDto dto) {

		boolean response = articulosService.update(dto);

		if (response) {
			return new ResponseEntity<String>(HttpStatus.CREATED);

		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

}
