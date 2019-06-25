package ar.edu.unnoba.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UberExceptionMapper implements ExceptionMapper<Exception> {
	
    @Override
    public Response toResponse(Exception exception) {

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
    
}
