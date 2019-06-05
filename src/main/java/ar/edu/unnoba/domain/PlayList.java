package ar.edu.unnoba.domain;

import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name = "PlayList")
@Table(name = "playlists")
@NamedQueries({
	@NamedQuery(
			name = "PlayList.getPlayListCreadas",
			query = "SELECT pl FROM PlayList pl"
			),
	@NamedQuery(
			name = "PlayList.getInformacionDeUnaPlayList",
			query = "SELECT pl FROM PlayList pl WHERE pl.id = :id"
			)
})
public class PlayList extends AbstractEntity {

	@Column(name = "pl_name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
			name="playlists_songs",
			joinColumns=@JoinColumn(name="playlist_id"),
			inverseJoinColumns=@JoinColumn(name="song_id"))
	private List<Song> songs;
	
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
	public Integer getId() {
		return super.getId();
	}
		
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "PlayList [name=" + name + ", user=" + user.toString() + ", song=" + songs.toString() + "]";
	}
	
}
