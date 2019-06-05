package ar.edu.unnoba.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import ar.edu.unnoba.domain.PlayList;
import ar.edu.unnoba.domain.PlaylistSong;
import ar.edu.unnoba.domain.Song;
import ar.edu.unnoba.dto.UserDTO;
import ar.edu.unnoba.exceptions.EntityNotFoundException;

public class PlayListDAO extends AbstractDAO<PlayList> {

	final static Logger logger = Logger.getLogger(PlayListDAO.class.getName());

	public PlayListDAO() {
		super(PlayList.class);
	}

	@SuppressWarnings("unchecked")
	public List<PlayList> getPlayListCreadas() throws EntityNotFoundException {
		List<PlayList> result = new ArrayList<PlayList>();
		try {
			Query query = em.createNamedQuery("PlayList.getPlayListCreadas");
			result = query.getResultList();
		} catch (NullPointerException e) {
			logger.warning("getPlayListCreadas: " + e.getMessage());
			throw new EntityNotFoundException("No hay playlists creadas");
		}
		return result;
	}

	public PlayList getInformationOfPlaylist(Integer id) throws EntityNotFoundException {
		Object result = null;
		try {
			result = new Object();
			Query query = em.createNamedQuery("PlayList.getInformacionDeUnaPlayList");
			query.setParameter("id", id);
			result = query.getSingleResult();
		} catch (Exception e) {
			logger.warning("getInformationOfPlaylist: " + e.getMessage());
			throw new EntityNotFoundException("No se encontro playlist");
		}
		return (PlayList) result;
	}

	public UserDTO getUserById(Integer id) throws EntityNotFoundException {
		Object[] result;
		UserDTO user;
		try {
			user = new UserDTO();
			Query query = em.createNativeQuery(
					  "SELECT u.id, u.email, u.pass "
					+ "FROM playlists p "
					+ "INNER JOIN users u ON p.user_id = u.id "
					+ "WHERE p.id = :id");
			query.setParameter("id", id);
			result = (Object[]) query.getSingleResult();
			user.setId((Integer)result[0]);
			user.setEmail((String) result[1]);
			user.setPassword((String) result[2]);
		} catch (Exception e) {
			logger.warning("getInformationOfPlaylist: " + e.getMessage());
			throw new EntityNotFoundException("Operacion invalida");
		}
		return user;
	}

	public void createPlayList(PlayList playList) throws EntityNotFoundException {
		try {
			em.getTransaction().begin();
			em.persist(playList);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			logger.warning("createPlayList: " + e.getMessage());
			throw new EntityNotFoundException("No se pudo crear playlist");
		} 
	}

	public void updateName(Integer id, PlayList playList) throws EntityNotFoundException {
		try {
			PlayList pl = em.find(PlayList.class, id);
			em.getTransaction().begin();
			pl.setName(playList.getName());
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			logger.warning("updateName: " + e.getMessage());
			throw new EntityNotFoundException("No se pudo actualizar nombre de playlist");
		} 
	}

	public void addSongToPlayList(Integer id, Song song) throws EntityNotFoundException {
		try {
			PlaylistSong pl_s = new PlaylistSong();
			pl_s.setPlaylist_id(id);
			pl_s.setSong_id(song.getId());
			em.getTransaction().begin();
			em.persist(pl_s);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			logger.warning("addSongToPlayList: " + e.getMessage());
			throw new EntityNotFoundException("No pudo insertarse la canción");
		}
	}
	
	public void removeSongToPlayList(Integer idPlayList, Integer idSong) throws EntityNotFoundException {
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(
					"DELETE FROM playlists_songs "
					+ "WHERE playlist_id = :pl AND song_id= :s ")
					.setParameter("pl", idPlayList)
					.setParameter("s", idSong);
			query.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			logger.warning("updateName: " + e.getMessage());
			throw new EntityNotFoundException("No se pudo remover canción");
		} 
	}
	
	public void deletePlayList(Integer idPLayList) throws EntityNotFoundException {
		try {
			PlayList pl = em.find(PlayList.class, idPLayList);
			em.getTransaction().begin();
			em.remove(pl);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			logger.warning("addSongToPlayList: " + e.getMessage());
			throw new EntityNotFoundException("No pudo se pudo borrar la playlist");
		} 
	}
	
	

}
