package marketplace.dto;

import java.util.List;

import marketplace.entity.Articulo;
import marketplace.entity.PedidoArticulo;
import marketplace.entity.Usuario;

public class PedidoDto {

	private int id;
	private String fecha;
	private String ciudad;
	private List<ArticuloCantidadDto> pedidoArticulos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<ArticuloCantidadDto> getPedidoArticulos() {
		return pedidoArticulos;
	}

	public void setPedidoArticulos(List<ArticuloCantidadDto> pedidoArticulos) {
		this.pedidoArticulos = pedidoArticulos;
	}

}
