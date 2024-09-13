package marketplace.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import marketplace.entity.Pedido;
import marketplace.service.ArticulosService;
import marketplace.service.PedidosService;

@RestController
@RequestMapping("marketplace/pedidos")
public class PedidosController {

	@Autowired
	PedidosService pedidosService;

	@GetMapping("/{nombreParcial}/ciudad")
	public ResponseEntity<List<PedidoDto>> buscarPedidos(@PathVariable String nombreParcial) {

		List<PedidoDto> response = pedidosService.buscarPedidosPorCiudad(nombreParcial);
		if (!response.isEmpty()) {
			return new ResponseEntity<List<PedidoDto>>(pedidosService.buscarPedidosPorCiudad(nombreParcial),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<List<PedidoDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<PedidoDto> findById(@PathVariable int id) {

		PedidoDto response = pedidosService.findById(id);
		if (response != null) {
			return new ResponseEntity<PedidoDto>(pedidosService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<PedidoDto>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid PedidoDto dto) {

		boolean response = pedidosService.create(dto);

		if (response) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@RequestBody @Valid PedidoDto dto) {

		boolean response = pedidosService.update(dto);

		if (response) {
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping
	public void deletePedido(@RequestParam int id) {

		pedidosService.deleteById(id);
	}

}
