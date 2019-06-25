package ar.edu.unnoba.mappers;



import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ar.edu.unnoba.domain.GenericErrorMessage;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<NotAuthorizedException> {

    @Override
    public Response toResponse(NotAuthorizedException exception) {

        GenericErrorMessage e = new GenericErrorMessage();
        e.setMessage(exception.getMessage());
        e.setCode(Status.UNAUTHORIZED.getStatusCode());
        e.setChallenges(exception.getChallenges());

        return Response
                .status(Status.UNAUTHORIZED)
                .type("application/json")
                .entity(e)
                .build();
    }
    
}
