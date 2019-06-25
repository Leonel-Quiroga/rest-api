package ar.edu.unnoba.filters;

import javax.security.auth.Subject;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;
import java.util.logging.Logger;

public class SecurityContextAuthorizer implements SecurityContext {

	final static Logger logger = Logger.getLogger(SecurityContextAuthorizer.class.getName());
    private Principal principal;
    private javax.inject.Provider<UriInfo> uriInfo;

    public SecurityContextAuthorizer(final javax.inject.Provider<UriInfo> uriInfo, final Principal principal) {
        this.principal = principal;
        if (principal == null) {
            this.principal = new Principal() {
                @Override
                public String getName() {
                    return "anonymous";
                }

                @Override
                public boolean implies(Subject subject) {
                    return true;
                }
            };
        }
        this.uriInfo = uriInfo;
    }

    public Principal getUserPrincipal() {
        return this.principal;
    }

    public boolean isSecure() {
        return "https".equals(uriInfo.get().getRequestUri().getScheme());
    }

    public String getAuthenticationScheme() {
        return SecurityContext.DIGEST_AUTH;
    }

	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}
}
