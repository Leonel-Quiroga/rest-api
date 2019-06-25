package ar.edu.unnoba.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity(name = "PlayList")
@Table(name = "playlists")
@NamedQueries({ 
		@NamedQuery(
				name = "PlayList.getPlayListCreadas", 
				query = "SELECT pl FROM PlayList pl"),
		@NamedQuery(
				name = "PlayList.getInformacionDeUnaPlayList", 
				query = "SELECT pl FROM PlayList pl WHERE pl.id = :id"
				)
		})
public class PlayList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "playlist_seceunce", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "playlist_seceunce", sequenceName = "playlist_seceunce", allocationSize = 1)
	private Integer id;

	@Column(name = "pl_name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "playlists_songs", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
	private List<Song> songs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public void addSong(Song song) {
		songs.add(song);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!(obj instanceof PlayList)) {
			return false;
		} else if (((PlayList) obj).id == null) {
			return false;
		} else
			return ((PlayList) obj).id.equals(this.id);
	}
	
	@Override
	public String toString() {
		return "PlayList [name=" + name + ", user=" + user.toString() + ", song=" + songs.toString() + "]";
	}

}
