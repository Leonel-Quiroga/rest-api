package ar.edu.unnoba.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PlaylistSong")
@Table(name = "playlists_songs")
public class PlaylistSong implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "playlist_id")
	private Integer playlist_id;
	@Column(name = "song_id")
	private Integer song_id;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlaylist_id() {
		return playlist_id;
	}
	public void setPlaylist_id(Integer playlist_id) {
		this.playlist_id = playlist_id;
	}
	public Integer getSong_id() {
		return song_id;
	}
	public void setSong_id(Integer song_id) {
		this.song_id = song_id;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		} else if (!(obj instanceof AbstractEntity)) {
			return false;
		} else if (((PlaylistSong) obj).id == null) {
			return false;
		} else
			return ((PlaylistSong) obj).id.equals(this.id);
	}

}
