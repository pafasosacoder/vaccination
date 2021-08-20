package ec.com.vaccination.backend.config;

import ec.com.vaccination.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter  extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/api/login") || request.getRequestURI().equals("/api/signup")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            final String token = header.split(" ")[1].trim();
            if (!jwtTokenUtil.validate(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            if (!userService.hasUserRolePermission(jwtTokenUtil.getUsername(token), request.getRequestURI())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
