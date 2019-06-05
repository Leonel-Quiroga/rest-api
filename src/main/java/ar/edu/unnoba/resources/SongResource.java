package ar.edu.unnoba.resources;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import ar.edu.unnoba.dao.SongDAO;
import ar.edu.unnoba.dto.SongDTO;
import ar.edu.unnoba.enums.Genre;
import ar.edu.unnoba.util.Adapter;
import ar.edu.unnoba.util.Response;

@Path("/songs")
public class SongResource {

	@Inject
	private SongDAO songDAO;
	
	private Response<SongDTO> response = new Response<SongDTO>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response<SongDTO> getbyAuthorAndGenere(
			@QueryParam("author") @DefaultValue("ozzy") String autor,
			@QueryParam("genre") @DefaultValue("Rock") Genre genre) {
		Adapter adapter = new Adapter();
		try {			
			response.setLista(adapter.domainToDTO(songDAO.getByAuthorAndGenre(autor, genre)));
			response.setSuccess(true);
		} catch (Exception ex) {
			System.out.println("Fallo en retornar todas(o algunas) canciones " + ex.getMessage());
			response.setMsg("No se pudo obtener el listado de canciones");
		}
		return response;
	}
}
