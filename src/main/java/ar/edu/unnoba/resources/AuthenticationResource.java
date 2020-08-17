package ar.edu.unnoba.resources;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.unnoba.dao.UserDAO;
import ar.edu.unnoba.domain.Token;
import ar.edu.unnoba.domain.User;
import ar.edu.unnoba.exceptions.EntityNotFoundException;
import ar.edu.unnoba.mappers.AuthenticationExceptionMapper;
import ar.edu.unnoba.util.TokenUtil;

@Path("/auth")
public class AuthenticationResource {

    private final static Logger logger = Logger.getLogger(AuthenticationResource.class.getName());

    @Inject
    UserDAO dao;

    @Context
    Key key;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(User user) {
    	Date expiry = null;
    	Token token = null;
    	String jwtString = null;
    	try {
            expiry = getExpiryDate(15);
            this.authenticate(user.getEmail(), user.getPassword());
            jwtString = TokenUtil.getJWTString(user.getEmail(), user.getPassword(), expiry, key);
            token = new Token();
            token.setAuthToken(jwtString);
            token.setExpires(expiry);	
		} catch (NotAuthorizedException e) {
			return new AuthenticationExceptionMapper().toResponse(e);
			//logger.info("ERROR: authenticateUser - " + e.getMessage());
		}
        return Response.ok(token).build();
    }

    private Date getExpiryDate(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    private void authenticate(String email, String password) throws NotAuthorizedException {
        User user = null;
        try {
            user = dao.getUser(email);
        } catch (EntityNotFoundException e) {
            logger.info("ERROR: authenticate - " + email);
            throw new NotAuthorizedException("Mail Invalido " + email);
        }
       
        if (user.getPassword().equals(password)) {
            logger.info("USER AUTENTICADO");
        } else {
            logger.info("USER NO AUTENTICADO");
            throw new NotAuthorizedException("USUARIO O CONTRASEÑA INCORRRECTOS");
        }
    }
}
