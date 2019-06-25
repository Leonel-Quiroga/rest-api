package ar.edu.unnoba.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-05-23T19:24:45.796-0300")
@StaticMetamodel(PlayList.class)
public class PlayList_ extends AbstractEntity_ {
	public static volatile SingularAttribute<PlayList, String> name;
	public static volatile SingularAttribute<PlayList, User> user;
}
