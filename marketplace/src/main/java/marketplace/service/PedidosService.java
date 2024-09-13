package marketplace.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marketplace.dto.ArticuloCantidadDto;
import marketplace.dto.ArticuloDto;
import marketplace.dto.PedidoDto;
import marketplace.entity.Articulo;
import marketplace.entity.Pedido;
import marketplace.entity.PedidoArticulo;
import marketplace.repository.ArticuloRepository;
import marketplace.repository.PedidoRepository;

@Service
public class PedidosService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ArticuloRepository articuloRepository;

	public List<PedidoDto> buscarPedidosPorCiudad(String ciudad) {

		List<PedidoDto> pedidosDto = new ArrayList<PedidoDto>();
		List<Pedido> pedidos = pedidoRepository.findByCiudadContaining(ciudad);

		if (pedidos == null) {
			return pedidosDto;
		}
		for (Pedido pedido : pedidos) {

			PedidoDto dto = new PedidoDto();

			dto.setId(pedido.getId());
			dto.setCiudad(pedido.getCiudad());
			dto.setFecha(pedido.getFecha());

			List<ArticuloCantidadDto> articuloCantidadDtos = new ArrayList<>();

			for (PedidoArticulo pedidoArticulo : pedido.getPedidoArticulos()) {
				Articulo articulo = pedidoArticulo.getArticulo();

				ArticuloCantidadDto articuloCantidadDto = new ArticuloCantidadDto();
				articuloCantidadDto.setId(articulo.getId());
				articuloCantidadDto.setCantidad(pedidoArticulo.getCantidad());

				articuloCantidadDtos.add(articuloCantidadDto);
			}

			dto.setPedidoArticulos(articuloCantidadDtos);

			pedidosDto.add(dto);
		}

		return pedidosDto;
	}

	public PedidoDto findById(int id) {

		try {
			Pedido pedido = new Pedido();

			pedido = pedidoRepository.findById(id).get();

			PedidoDto dto = new PedidoDto();

			dto.setId(pedido.getId());
			dto.setCiudad(pedido.getCiudad());
			dto.setFecha(pedido.getFecha());

			List<ArticuloCantidadDto> articuloCantidadDtos = new ArrayList<ArticuloCantidadDto>();

			for (PedidoArticulo pedidoArticulo : pedido.getPedidoArticulos()) {
				Articulo articulo = pedidoArticulo.getArticulo();

				ArticuloCantidadDto articuloCantidadDto = new ArticuloCantidadDto();
				articuloCantidadDto.setId(articulo.getId());
				articuloCantidadDto.setCantidad(pedidoArticulo.getCantidad());

				articuloCantidadDtos.add(articuloCantidadDto);
			}

			dto.setPedidoArticulos(articuloCantidadDtos);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean create(PedidoDto dto) {
		Pedido pedido = new Pedido();

		pedido.setId(dto.getId());
		pedido.setCiudad(dto.getCiudad());
		pedido.setFecha(dto.getFecha());

		for (ArticuloCantidadDto articuloCantidadDTO : dto.getPedidoArticulos()) {
			Articulo articulo = new Articulo();
			try {
				articulo = articuloRepository.findById(articuloCantidadDTO.getId())
						.orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
			} catch (Exception e) {
				return false;
			}
			PedidoArticulo pedidoArticulo = new PedidoArticulo();
			pedidoArticulo.setArticulo(articulo);
			pedidoArticulo.setCantidad(articuloCantidadDTO.getCantidad());
			pedidoArticulo.setPedido(pedido);
			pedido.getPedidoArticulos().add(pedidoArticulo);

			int valor = articulo.getStock() - pedidoArticulo.getCantidad();
			if (valor > 0) {
				articulo.setStock(valor);

			} else {
				return false;
			}
		}

		pedidoRepository.save(pedido);
		return true;
	}

	public boolean update(PedidoDto dto) {

		if (dto == null) {
			return false;
		}

		Pedido pedido = new Pedido();
		pedido.setId(dto.getId());
		pedido.setCiudad(dto.getCiudad());
		pedido.setFecha(dto.getFecha());

		for (ArticuloCantidadDto articuloCantidadDTO : dto.getPedidoArticulos()) {

			Articulo articulo = new Articulo();
			try {
				articulo = articuloRepository.findById(articuloCantidadDTO.getId())
						.orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
			} catch (Exception e) {
				return false;
			}

			PedidoArticulo pedidoArticulo = new PedidoArticulo();
			pedidoArticulo.setArticulo(articulo);
			pedidoArticulo.setCantidad(articuloCantidadDTO.getCantidad());
			pedidoArticulo.setPedido(pedido);
			pedido.getPedidoArticulos().add(pedidoArticulo);
		}

		pedidoRepository.save(pedido);
		return true;
	}

	public void deleteById(int id) {
		pedidoRepository.deleteById(id);

	}
}
