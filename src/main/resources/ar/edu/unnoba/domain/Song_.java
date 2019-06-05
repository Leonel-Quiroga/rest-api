package ar.edu.unnoba.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-05-23T15:42:23.493-0300")
@StaticMetamodel(Song.class)
public class Song_ extends AbstractEntity_ {
	public static volatile SingularAttribute<Song, String> name;
	public static volatile SingularAttribute<Song, String> author;
	public static volatile SingularAttribute<Song, String> genre;
}
