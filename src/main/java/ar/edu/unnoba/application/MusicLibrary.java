package ar.edu.unnoba.application;

import java.security.Key;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import ar.edu.unnoba.dao.PlayListDAO;
import ar.edu.unnoba.dao.SongDAO;
import ar.edu.unnoba.dao.UserDAO;
import ar.edu.unnoba.filters.CORSFilter;
import ar.edu.unnoba.filters.JWTSecurityFilter;
import io.jsonwebtoken.impl.crypto.MacProvider;

@ApplicationPath("/mymusic")
public class MusicLibrary extends ResourceConfig {
	private static UserDAO userDAO;
	private static SongDAO songDAO;
	private static PlayListDAO playListDAO;
	private static Key key;
	
	public MusicLibrary () {
		this(new UserDAO(), new SongDAO(), new PlayListDAO(), MacProvider.generateKey());
		
	}
	
	public MusicLibrary(final UserDAO userDAO, final SongDAO songDAO, final PlayListDAO playlistDAO, final Key key) {	
		this.setUserDAO(userDAO);
		this.setSongDAO(songDAO);
		this.setPlayListDAO(playlistDAO);
		this.setKey(key);
		
		register(CORSFilter.class);
		register(RolesAllowedDynamicFeature.class);
		register(JWTSecurityFilter.class);
		packages("ar.edu.unnoba.resources");
		packages("ar.edu.unnoba.async");
		
		
		register(new AbstractBinder() {		
			@Override
			protected void configure() {
				bind(getUserDAO()).to(UserDAO.class);
				bind(getSongDAO()).to(SongDAO.class);
				bind(getPlayListDAO()).to(PlayListDAO.class);
				bind(getKey()).to(Key.class);	
			}
		});
        property("jersey.config.beanValidation.enableOutputValidationErrorEntity.server", "true");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);	
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		MusicLibrary.userDAO = userDAO;
	}
	
	public SongDAO getSongDAO() {
		return songDAO;
	}

	public void setSongDAO(SongDAO songDAO) {
		MusicLibrary.songDAO = songDAO;
	}
	
	public PlayListDAO getPlayListDAO() {
		return playListDAO;
	}

	public void setPlayListDAO(PlayListDAO playListDAO) {
		MusicLibrary.playListDAO = playListDAO;
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		MusicLibrary.key = key;
	}

}
