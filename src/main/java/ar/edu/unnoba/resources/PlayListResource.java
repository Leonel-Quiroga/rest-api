package ar.edu.unnoba.resources;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import ar.edu.unnoba.dao.PlayListDAO;
import ar.edu.unnoba.dao.UserDAO;
import ar.edu.unnoba.domain.PlayList;
import ar.edu.unnoba.domain.Song;
import ar.edu.unnoba.dto.PlayListDTO;
import ar.edu.unnoba.dto.UserDTO;
import ar.edu.unnoba.exceptions.EntityNotFoundException;
import ar.edu.unnoba.util.Adapter;
import ar.edu.unnoba.util.Response;

@Path("/playlists")
public class PlayListResource {

	final static Logger logger = Logger.getLogger(PlayListResource.class.getName());

	@Inject
	private PlayListDAO playlistDAO;

	@Context
	private UserDAO userDAO;

	@Context
	private SecurityContext securityContext;
	
	private Response<PlayListDTO> response = new Response<PlayListDTO>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response<PlayListDTO> getPlaylistsCreated() {
		Adapter adapter = null;
		try {
			adapter = new Adapter();
			response.setLista(adapter.playlistsCreated(playlistDAO.getPlayListCreadas()));
			response.setSuccess(true);
		} catch (Exception ex) {
			logger.warning("getPlaylistsCreated: " + ex.getMessage());
			response.setMsg("Error al buscar las listas de canciones");
		}
		return response;

	}

	@GET
	@Path("/{idPlayList}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response<PlayListDTO> getInformationOfPlaylist(
			@PathParam("idPlayList") @DefaultValue("-1") Integer idPlayList) {
		Adapter adapter = null;
		try {
			adapter = new Adapter();
			response.setElemento(adapter.getInformationOfPlaylist(playlistDAO.getInformationOfPlaylist(idPlayList)));
			response.setSuccess(true);
		} catch (Exception ex) {
			logger.warning("getInformationOfPlaylist: " + ex.getMessage());
			response.setMsg("Error al buscar la lista de canciones");
		}
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response<PlayListDTO> createPlayList(PlayList playList) throws EntityNotFoundException {
		try {
			playlistDAO.createPlayList(playList);
			response.setMsg("Lista creada exitosamente");
			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			throw new NotAllowedException("No puede realizar esta operacion");
		}	
			
	}

	@PUT
	@Path("/{idPlayList}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response<PlayListDTO> updateName(@PathParam("idPlayList") @DefaultValue("-1") Integer idPlayList,
			PlayList playList) throws EntityNotFoundException {
		if (playList.getUser().getEmail().equals(securityContext.getUserPrincipal().getName())) {
			playlistDAO.updateName(idPlayList, playList);
			response.setMsg("Actualización exitosamente el nombre de la playlist");
			response.setSuccess(true);
			return response;
		} else {
			throw new NotAllowedException("No puede realizar esta operacion");
		}
	}

	@PUT
	@Path("{idPlayList}/songs")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response<PlayListDTO> addSongToPlayList(@PathParam("idPlayList") @DefaultValue("-1") Integer idPlayList,
			Song song) throws EntityNotFoundException {
		if (userValidation(idPlayList)) {
			playlistDAO.addSongToPlayList(idPlayList, song);
			response.setMsg("Se incorporo exitosamente la canciona la playlist.");
			response.setSuccess(true);
			return response;
		} else {
			throw new NotAllowedException("No puede realizar esta operacion");
		}
	}

	@DELETE
	@Path("{idPlayList}/songs/{idSong}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response<PlayListDTO> removeSongToPlayList(@PathParam("idPlayList") @DefaultValue("-1") Integer idPlayList,
			@PathParam("idSong") @DefaultValue("-1") Integer idSong) throws EntityNotFoundException {
		if (userValidation(idPlayList)) {
			playlistDAO.removeSongToPlayList(idPlayList, idSong);
			response.setMsg("Se elimino exitosamente la canción de la playlist.");
			response.setSuccess(true);
			return response;
		} else {
			throw new NotAllowedException("No puede realizar esta operacion");
		}
	}

	@DELETE
	@Path("/{idPlayList}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response<PlayListDTO> deletePlayList(@PathParam("idPlayList") @DefaultValue("-1") Integer idPlayList)
			throws EntityNotFoundException {
		if (userValidation(idPlayList)) {
			playlistDAO.deletePlayList(idPlayList);
			response.setMsg("Se elimino exitosamente la playlist.");
			response.setSuccess(true);
			return response;
		} else {
			throw new NotAllowedException("No puede realizar esta operacion");
		}
	}

	public boolean userValidation(Integer idPlayList) throws EntityNotFoundException {
		UserDTO userDTO = playlistDAO.getUserById(idPlayList);
		return userDTO.getEmail().equals(securityContext.getUserPrincipal().getName()) ? true : false;
	}

}
