# rest-api

CONSIGNA:

<p>Presentación y objetivos</p>
<p>Se desea aplicar en forma práctica los conceptos de Web Services adquiridos en teoría.
El objetivo de esta actividad es implementar el backend de una aplicación de software
mediante un web service Restful que implementa la arquitectura Rest.</p>

<p>Materiales y recursos:</p>
<p>Para el desarrollo de la actividad deberán utilizarse los siguientes materiales y recursos:</p>
<ul>
        <li> HTTP: https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol</li>
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

<p>Enunciado General</p>
<p>Se pide desarrollar realizar el backend de una aplicación de música mediante un web
service Restful que implementa la arquitectura Rest.
Particularmente se pide desarrollar la funcionalidad CRUD de paylists por parte de los
usuarios.
El backend debe implementar el standard de autenticacion JWT.</p>

<p>El backend debe permitir a los usuarios:</p>
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

<p>La aplicación debe implementar las siguientes clases:</p>
<ul>
        <li> User (email:String,password:String)</li>
        <li> PlayList (name:String,user:User,songs:List<Song>)</li>
        <li> Song (name:String,author:String,genre:Genre)</li>
        <li> Genre (enum:rock,techno,pop,jazz,folk,classical)</li>
</ul>

<p>El modelo de la base de datos debe tener las siguientes tablas:</p>
<ul>
        <li> users(id:integer, email:varchar, password:varchar) </li> 
        <li> playlists(id:integer, user_id:integer, name:varchar) </li>
        <li> playlists_songs(id:integer, playlist_id: integer, song_id:integer)</li>
        <li> songs(id:integer,name:varchar,author:varchar,genre:varchar)</li>
</ul>
<p>Las capas de negocio y DAO se pide utilizar EJB - del modo utlizado en POO -
Para la capa de persistencia vamos utilizar JPA.
El motor de base de datos puede ser MySQL o PostgreSQL.</p>

<p>Definicion de endpoints: </p>
El API Rest a desarrollar debe implementar los siguientes endpoints.
Completar los mismos con según el método/verbo de HTTP que corresponda.</p>

<p>Autenticación:</p>
<p>Para autenticacion del usuario, en body del resquest deben ir los parametros email y
password. Retorna token que deberá ser incluido en el header de los endpoints que
requieran autenticacion.</p>
<ul>
  <li>POST http://localhost:8080/mymusic/auth</li>
</ul>

<p>Lista de canciones:</p>
<p>Retorna JSON con el listado de canciones.</p>
<ul>
  <li>______ http://localhost:8080/mymusic/songs?author="Spinetta"&genre=rock</li>
</ul>

<p>Listado de playlist creadas:</p>
<p>Retorna JSON con el listado de playlists</p>
<ul>
  <li>______ http://localhost:8080/mymusic/playlists/</li>
</ul>

<p>Crear una playlist:</p>
<p>Permite crear una nueva playlist - Requiere estar autenticado y enviar token en header.
En el body se envia los datos del playlist en formato JSON.</p>
<ul>
  <li>______ http://localhost:8080/mymusic/playlists/</li>
</ul>

<p>Información de una playlist:</p>
Retorna JSON con todos los datos de un playlist incluyendo la lista de canciones.</p>
<ul>
  <li>______ http://localhost:8080/mymusic/playlists/{id}</li>
</ul>

<p>Actualizar el nombre de una playlist:</p>
<p>Permite actualizar el nombre de un playlist. Requiere autenticacion.
Solo el usuario que creo el playlist puede actualizarlo. Se envia en el body el nombre
que debe tomar el playlist.</p>
<ul>
 <li> ______ http://localhost:8080/mymusic/playlists/{id}</li>
</ul>

Agregar una canción a una playlist:</p>
<p>Permite agregar una cancion a un playlist. Requiere autenticacion.
Solo el usuario que creo el playlist puede realizar la accion. Se envia el id de la cancion
a agregar en el playlist en el body.</p>
<ul>
 <li> ______ http://localhost:8080/mymusic/playlists/{id}/songs/</li>
</ul>

<p>Quitar una canción de una playlist:</p>
Permite quitar una cacion del playlist. Requiere autenticacion. Solo el usuario que creo
el playlist puede realizar la accion.</p>
<ul>
 <li> ______ http://localhost:8080/mymusic/playlists/{id}/songs/{song_id}</li>
</ul>

<p>Borrar una canción de una playlist:</p>
<p>Permite eliminar un playlist. Requiere autenticacion. Solo el usuario que creo el playlist
puede borrarla.</p>
<ul>
 <li> ______ http://localhost:8080/mymusic/playlists/{id}</li>
</ul>

<p>Criterios de valoración:</p>
<p>Se valorará la capacidad de resolver problemas de arquitectura de aplicaciones que se
comunican mediante una capa de servicios, en particular, la implementación
cliente/servidor de un API Rest y como implementar un proceso de autenticación contra
un API mediante JWT</p>
