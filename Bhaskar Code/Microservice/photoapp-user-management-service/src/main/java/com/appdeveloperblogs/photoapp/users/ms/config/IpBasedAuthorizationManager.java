package com.appdeveloperblogs.photoapp.users.ms.config;

import org.springframework.core.env.Environment;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import jakarta.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

public class IpBasedAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private Environment environment;

    public IpBasedAuthorizationManager(Environment environment) {
        this.environment = environment;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        HttpServletRequest request = context.getRequest();

        // Get the real client IP from X-Forwarded-For header (set by gateway)
        String clientIp = null;
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }

        String allowedIp = environment.getProperty("gateway.ip");

        System.out.println("Client IP: " + clientIp);
        System.out.println("Allowed IP: " + allowedIp);
        System.out.println("X-Forwarded-For: " + request.getHeader("X-Forwarded-For"));
        System.out.println("Remote Addr: " + request.getRemoteAddr());

        boolean allowed = clientIp.equals(allowedIp);
        System.out.println("Authorization allowed: " + allowed);

        return new AuthorizationDecision(allowed);
    }
}
