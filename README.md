# rest-api

CONSIGNA:

Presentación y objetivos
Se desea aplicar en forma práctica los conceptos de Web Services adquiridos en teoría.
El objetivo de esta actividad es implementar el backend de una aplicación de software
mediante un web service Restful que implementa la arquitectura Rest.

Materiales y recursos
Para el desarrollo de la actividad deberán utilizarse los siguientes materiales y recursos:
<ul>
        <li>HTTP: https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol</li>
        <li> HTTP status codes: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes</li>
        <li> Rest: https://en.wikipedia.org/wiki/Representational_state_transfer</li>
        <li> Rest Api - resource naming: https://restfulapi.net/resource-naming/</li>
        <li> https://docs.oracle.com/javaee/7/tutorial/jaxrs002.htm</li>
        <li> https://javaee.github.io/tutorial/jsonp005.html</li>
        <li> Jersey: https://jersey.github.io/documentation/latest/index.html</li>
        <li> Web JWT:https://jwt.io/introduction/</li>
        <li> JJWT (Java JWT: JSON Web Token for Java and Android): https://github.com/jwtk/jjwt</li>
        <li> Postman: https://www.getpostman.com/</li>
</ul>

Enunciado General
Se pide desarrollar realizar el backend de una aplicación de música mediante un web
service Restful que implementa la arquitectura Rest.
Particularmente se pide desarrollar la funcionalidad CRUD de paylists por parte de los
usuarios.
El backend debe implementar el standard de autenticacion JWT.

El backend debe permitir a los usuarios:
<ul>
        <li> Autenticarse mediante su email y password.</li>
        <li> Consultar las canciones disponibles, pudiendo ser filtradas por autor y genero.</li>
        <li> Crear paylists</li>
        <li> Agregar y quitar canciones a un playlist</li>
        <li> Cambiar el nombre de una playlist existente</li>
        <li> Consultar las playlists creadas</li>
        <li> Consultar las canciones de una playlist</li>
        <li> Borrar una playlist.</li>
</ul>

La aplicación debe implementar las siguientes clases:
<ul>
        <li> User (email:String,password:String)</li>
        <li> PlayList (name:String,user:User,songs:List<Song>)</li>
        <li> Song (name:String,author:String,genre:Genre)</li>
        <li> Genre (enum:rock,techno,pop,jazz,folk,classical)</li>
</ul>
El modelo de la base de datos debe tener las siguientes tablas:
        ● users(id:integer, email:varchar, password:varchar)
        ● playlists(id:integer, user_id:integer, name:varchar)
        ● playlists_songs(id:integer, playlist_id: integer, song_id:integer)
        ● songs(id:integer,name:varchar,author:varchar,genre:varchar)

Las capas de negocio y DAO se pide utilizar EJB - del modo utlizado en POO -
Para la capa de persistencia vamos utilizar JPA.
El motor de base de datos puede ser MySQL o PostgreSQL.

Definicion de endpoints
El API Rest a desarrollar debe implementar los siguientes endpoints.
Completar los mismos con según el método/verbo de HTTP que corresponda.

Autenticación
Para autenticacion del usuario. En body del resquest deben ir los parametros email y
password. Retorna token que deberá ser incluido en el header de los endpoints que
requieran autenticacion.
● POST http://localhost:8080/mymusic/auth

Lista de canciones
Retorna JSON con el listado de canciones.
● ______ http://localhost:8080/mymusic/songs?author="Spinetta"&genre=rock

Listado de playlist creadas
Retorna JSON con el listado de playlists
● ______ http://localhost:8080/mymusic/playlists/

Crear una playlist
Permite crear una nueva playlist - Requiere estar autenticado y enviar token en header.
En el body se envia los datos del playlist en formato JSON.
● ______ http://localhost:8080/mymusic/playlists/

Información de una playlist
Retorna JSON con todos los datos de un playlist incluyendo la lista de canciones.
● ______ http://localhost:8080/mymusic/playlists/{id}

Actualizar el nombre de una playlist
Permite actualizar el nombre de un playlist. Requiere autenticacion.
Solo el usuario que creo el playlist puede actualizarlo. Se envia en el body el nombre
que debe tomar el playlist.
● ______ http://localhost:8080/mymusic/playlists/{id}

Agregar una canción a una playlist
Permite agregar una cancion a un playlist. Requiere autenticacion.
Solo el usuario que creo el playlist puede realizar la accion. Se envia el id de la cancion
a agregar en el playlist en el body.
● ______ http://localhost:8080/mymusic/playlists/{id}/songs/

Quitar una canción de una playlist
Permite quitar una cacion del playlist. Requiere autenticacion. Solo el usuario que creo
el playlist puede realizar la accion.
● ______ http://localhost:8080/mymusic/playlists/{id}/songs/{song_id}

Borrar una canción de una playlist
Permite eliminar un playlist. Requiere autenticacion. Solo el usuario que creo el playlist
puede borrarla.
● ______ http://localhost:8080/mymusic/playlists/{id}

Criterios de valoración
Se valorará la capacidad de resolver problemas de arquitectura de aplicaciones que se
comunican mediante una capa de servicios, en particular, la implementación
cliente/servidor de un API Rest y como implementar un proceso de autenticación contra
un API mediante JWT
