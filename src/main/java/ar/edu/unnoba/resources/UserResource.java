package ar.edu.unnoba.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.sun.research.ws.wadl.Request;

import ar.edu.unnoba.dao.UserDAO;
import ar.edu.unnoba.domain.User;
import ar.edu.unnoba.exceptions.EntityNotFoundException;
import ar.edu.unnoba.util.Response;

@Path("/users")
public class UserResource {
	
	@Context
	private UserDAO dao;
	
	@Context
	private Request reqest;
	
	@Context
	private SecurityContext securityContext;
	
	Response<String> response = new Response<String>();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(@PathParam("email") String email) throws EntityNotFoundException {
	    User user = dao.getUser(email);
	    if (user.getEmail().equals(securityContext.getUserPrincipal().getName())) {
	        return user;
	    } else {
	        throw new NotAllowedException("Not allowed ");
	    }
	}
}
