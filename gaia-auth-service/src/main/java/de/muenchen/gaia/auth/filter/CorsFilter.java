package de.muenchen.gaia.auth.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter to enable preflight request.
 * https://spring.io/blog/2015/01/20/the-resource-server-angular-js-and-spring-security-part-iii#cors-negotiation
 * Created by huningd on 14.01.16.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, x-auth-token, x-requested-with");
        response.setHeader("Access-Control-Expose-Headers", "Location");
        if (req instanceof HttpServletRequest) {
            if(!"OPTIONS".equals(((HttpServletRequest) req).getMethod())){
                chain.doFilter(req, res);
            }
        }
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}

