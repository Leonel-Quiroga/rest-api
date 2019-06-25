package ar.edu.unnoba.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import ar.edu.unnoba.domain.Song;
import ar.edu.unnoba.enums.Genre;
import ar.edu.unnoba.exceptions.EntityNotFoundException;

public class SongDAO extends AbstractDAO<Song>  {

	final static Logger logger = Logger.getLogger(SongDAO.class.getName());
	public SongDAO() {
		super(Song.class);
	}

	@SuppressWarnings("unchecked")
	public List<Song> getByAuthorAndGenre(String author, Genre genre) throws EntityNotFoundException {
		List<Song> result = new ArrayList<Song>();
		try {
			Query query = em.createNamedQuery("Song.getByAuthorAndGenre");
			query.setParameter("author", author);
			query.setParameter("genre", genre);
			result = query.getResultList();
		} catch (Exception e) {
			logger.warning("getByAuthorAndGenre: " + e.getMessage());
			throw new EntityNotFoundException("No se encontro el autor o genero");
		}
		return result;
	}

}
