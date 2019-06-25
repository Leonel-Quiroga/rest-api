package ar.edu.unnoba.async;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import ar.edu.unnoba.dao.SongDAO;
import ar.edu.unnoba.dto.SongDTO;
import ar.edu.unnoba.enums.Genre;
import ar.edu.unnoba.util.MapperUtils;
import ar.edu.unnoba.util.Response;

@Path("/async/songs")
public class SongAsync {

	@Inject
	private SongDAO songDAO;

	private Response<SongDTO> response = new Response<SongDTO>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("rawtypes")
	public void getByAuthorAndGenereAsync(@Suspended final AsyncResponse asyncResponse,
			@QueryParam("author") @DefaultValue("ozzy") String autor,
			@QueryParam("genre") @DefaultValue("Rock") Genre genre) {
		final CompletableFuture<Response> future = CompletableFuture.supplyAsync(() -> {
			try {
				response.setLista(MapperUtils.mapAll(songDAO.getByAuthorAndGenre(autor, genre),SongDTO.class));
				response.setSuccess(true);
			} catch (Exception ex) {
				System.out.println("Fallo en retornar todas(o algunas) canciones " + ex.getMessage());
				response.setMsg("No se pudo obtener el listado de canciones");
			}
			return response;
		});
		future.thenApply((response) -> asyncResponse.resume(response));
	}

}
