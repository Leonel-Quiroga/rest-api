package ar.edu.unnoba.dto;

import java.io.Serializable;

import ar.edu.unnoba.enums.Genre;

public class SongDTO implements Serializable {

	private static final long serialVersionUID = -3915426058447207741L;
	private String name;
	private String author;
	private Genre genre;

	public SongDTO() {
	}

	public SongDTO(String name, String author, Genre genre) {
		this.name = name;
		this.author = author;
		this.genre = genre;
	}

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

	@Override
	public String toString() {
		return "Song [name = " + name + ", author = " + author + ", genre = " + genre + "]";
	}

}
