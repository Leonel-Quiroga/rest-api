package ar.edu.unnoba.async;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import ar.edu.unnoba.dao.PlayListDAO;
import ar.edu.unnoba.dao.UserDAO;
import ar.edu.unnoba.dto.PlayListDTO;
import ar.edu.unnoba.resources.PlayListResource;
import ar.edu.unnoba.util.MapperUtils;
import ar.edu.unnoba.util.Response;

@Path("/async/playlists")
public class PlayListAsync {
	
	final static Logger logger = Logger.getLogger(PlayListResource.class.getName());

	@Inject
	private PlayListDAO playlistDAO;

	@Context
	private UserDAO userDAO;

	@Context
	private SecurityContext securityContext;
	
	private Response<PlayListDTO> response = new Response<PlayListDTO>();
	
	@GET
	@SuppressWarnings("rawtypes")
	@Produces(MediaType.APPLICATION_JSON)
	public void getPlaylistsCreated(@Suspended final AsyncResponse asyncResponse) {
		final CompletableFuture<Response> future = CompletableFuture.supplyAsync(()-> {
			try {
				response.setLista(MapperUtils.mapAll(playlistDAO.getPlayListCreadas(),PlayListDTO.class));
				response.setSuccess(true);
			} catch (Exception e) {
				logger.warning("getPlaylistsCreated: " + e.getMessage());
				response.setMsg("Error al buscar playlists");
			}
			return response;
		});
		future.thenApply((response) -> asyncResponse.resume(response));
	}
	
}
