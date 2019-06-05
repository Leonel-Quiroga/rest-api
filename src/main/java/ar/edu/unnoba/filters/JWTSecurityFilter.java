package ar.edu.unnoba.filters;

import java.io.IOException;
import java.security.Key;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerRequest;

import ar.edu.unnoba.dao.UserDAO;
import ar.edu.unnoba.domain.User;
import ar.edu.unnoba.exceptions.EntityNotFoundException;
import ar.edu.unnoba.util.TokenUtil;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTSecurityFilter implements ContainerRequestFilter {

	final static Logger logger = Logger.getLogger(JWTSecurityFilter.class.getName());

	@Context
	UserDAO userDAO;

	@Context
	Key key;

	@Inject
	javax.inject.Provider<UriInfo> uriInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Obtengo tipo de metodo
		String method = requestContext.getMethod().toLowerCase();

		// Obtengo path
		String path = ((ContainerRequest) requestContext).getPath(true).toLowerCase();

		// Si es un metodo GET dejo que recupere los datos si es un POST para
		// autenticar tambien lo permitpo
		if ("get".equals(method) || ("post".equals(method) && "auth".equals(path))) {
			requestContext.setSecurityContext(new SecurityContextAuthorizer(uriInfo, () -> "anonymous"));
			return;
		}

		// Obtego datos de la cabecera
		String autHeader = ((ContainerRequest) requestContext).getHeaderString("auth");

		// Si no hay datos lanzo una excepción ya que en esta instancia es
		// necesario tenerlos
		if (autHeader == null || autHeader.isEmpty()) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}

		// Obtengo mi Token con el metodo que defini abajo
		String strToken = extractJwtTokenFromAuthorizationHeader(autHeader);

		// Valido
		if (TokenUtil.isValid(strToken, key)) {
			String name = TokenUtil.getName(strToken, key);
			// Si existe permito hacer toda la wea
			if (name != null) {
				User user = null;
				try {
					user = userDAO.getUser(name);
				} catch (EntityNotFoundException e) {
					logger.info("Usuario no encontrado " + name);
				}
				if (user != null) {
					requestContext.setSecurityContext(new SecurityContextAuthorizer(uriInfo, () -> name));
					return;
				} else {
					logger.info("Entity User Null");
				}
			} else {
				logger.info("No se encontro el mail");
			}
		} else {
			logger.info("El Token es invalido");
		}
		throw new WebApplicationException(Response.Status.UNAUTHORIZED);
	}

	// Extraigo el token de la cabecera
	public static String extractJwtTokenFromAuthorizationHeader(String auth) {
		// Reemplaza Bearer que se encuentra al inicio de Token por ""
		return auth.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r] ", "").replace(" ", "");
	}

}
