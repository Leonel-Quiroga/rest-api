package ar.edu.unnoba.mappers;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ar.edu.unnoba.domain.GenericErrorMessage;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

    public Response toResponse(EntityNotFoundException ex) {

        GenericErrorMessage e = new GenericErrorMessage();
        e.setCode(Status.NOT_FOUND.getStatusCode());
        e.setMessage(ex.getMessage());

        return Response
                .status(Status.NOT_FOUND)
                .type("application/json")
                .entity(e)
                .build();

    }
}