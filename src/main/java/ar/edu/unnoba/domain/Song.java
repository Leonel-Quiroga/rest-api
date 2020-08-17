package ar.edu.unnoba.domain;

import java.util.List;

import javax.persistence.*;

import ar.edu.unnoba.enums.Genre;

@SuppressWarnings("serial")
@Entity(name = "Song")
@Table(name = "songs")
@NamedQueries({
		@NamedQuery(
				name = "Song.getByAuthorAndGenre", 
				query = "SELECT s FROM Song s WHERE s.author=:author and s.genre=:genre"
				),
		@NamedQuery(
				name = "Song.getSongById", 
				query = "SELECT s FROM Song s WHERE s.id=:id") 
		})

public class Song extends AbstractEntity {

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
			name = "playlists_songs",
			joinColumns = @JoinColumn(name = "song_id"), 
			inverseJoinColumns = @JoinColumn(name = "playlist_id")
			)
	private List<PlayList> playLists;

	@Column(name = "s_name")
	private String name;
	@Column(name = "author")
	private String author;
	@Enumerated(EnumType.STRING)
	@Column(name = "genre")
	private Genre genre;

	public Song() {	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Song copyForm(Song song) {

		if (song.name != null) {
			this.name = song.name;
		}
		if (song.name != null) {
			this.author = song.author;
		}
		if (song.name != null) {
			this.genre = song.genre;
		}

		return this;
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
		return "Song [name = " + name + " , author = " + author + " , genre = " + genre + "]";
	}

}
