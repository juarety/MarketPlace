package marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marketplace.dto.ArticuloDto;
import marketplace.entity.Articulo;

import marketplace.repository.ArticuloRepository;

@Service
public class ArticulosService {

	@Autowired
	ArticuloRepository articuloRepository;

	public List<Articulo> buscarArticulosPorNombre(String nombreParcial) {

		return articuloRepository.findByNombreContaining(nombreParcial);
	}

	public ArticuloDto findById(int id) {
		Articulo articulo = new Articulo();
		try {
			articulo = articuloRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		ArticuloDto dto = new ArticuloDto();

		dto.setId(articulo.getId());
		dto.setNombre(articulo.getNombre());
		dto.setPrecio(articulo.getPrecio());
		dto.setStock(articulo.getStock());

		return dto;
	}

	public boolean create(ArticuloDto dto) {

		if (dto == null) {
			return false;
		}
		Articulo articulo = new Articulo();

		articulo.setId(dto.getId());
		articulo.setNombre(dto.getNombre());
		articulo.setPrecio(dto.getPrecio());
		articulo.setStock(dto.getStock());

		articuloRepository.save(articulo);
		return true;
	}

	public boolean update(ArticuloDto dto) {
		if (dto == null) {
			return false;
		}
		Articulo articulo = new Articulo();

		articulo.setId(dto.getId());
		articulo.setNombre(dto.getNombre());
		articulo.setPrecio(dto.getPrecio());
		articulo.setStock(dto.getStock());

		articuloRepository.save(articulo);
		return true;
	}

}
