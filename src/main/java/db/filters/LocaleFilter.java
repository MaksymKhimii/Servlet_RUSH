package db.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * RU: Фильтр, используемый для интернационализации проекта (английский и русский языки)
 * ENG: Locale Filter used for internationalisation of project (English and Russian languages)
 */
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (request.getParameter("lang") != null) {
            request.getSession().setAttribute("lang", request.getParameter("lang"));
        }
        if (session.getAttribute("lang") != null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("messages",
                    new Locale((String) session.getAttribute("lang")));
            session.setAttribute("resourceBundle", resourceBundle);
        } else {
            ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("messages");
            session.setAttribute("resourceBundle", defaultResourceBundle);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}