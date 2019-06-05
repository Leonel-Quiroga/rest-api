package ar.edu.unnoba.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PlayListDTO implements Serializable {

	private static final long serialVersionUID = -4577343811289614900L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	private UserDTO user;
	@Inject
	private List<SongDTO> song;
	private String name;

	
	
	public PlayListDTO() {

	}
	
	public void instanceArraylist() {
		song = new ArrayList<SongDTO>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void addSong(SongDTO s) {
		song.add(s);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<SongDTO> getSong() {
		return song;
	}

	public void setSong(List<SongDTO> song) {
		this.song = song;
	}

}
