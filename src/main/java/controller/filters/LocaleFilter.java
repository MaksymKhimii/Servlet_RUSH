package controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/** RU: Фильтр, используемый для интернационализации проекта (английский и русский языки)
   ENG: Locale Filter used for internationalisation of project (English and Russian languages)
*/
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    //private static final Logger log = Logger.getLogger(LocaleFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) {
        //log.debug("Filter initialization starts");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // log.debug("Filter starts");
        final HttpServletRequest request= (HttpServletRequest) servletRequest;
        final HttpServletResponse response=(HttpServletResponse) servletResponse;
        HttpSession session =request.getSession();
        if(request.getParameter("lang")!=null){
            // Set session attribute "lang" to current on the page
            request.getSession().setAttribute("lang",request.getParameter("lang"));
        }
        if (session.getAttribute("lang") != null) {
            // Set resource bundle to current ()
            ResourceBundle resourceBundle = ResourceBundle.getBundle("messages",
                    new Locale((String) session.getAttribute("lang")));
            session.setAttribute("resourceBundle", resourceBundle);
        } else {
            // Set default resource bundle EN
            ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("messages");
            session.setAttribute("resourceBundle", defaultResourceBundle);
        }
        filterChain.doFilter(request,response);
        // log.debug("Filter finished");
    }
    @Override
    public void destroy() {
      //  log.debug("Filter destruction starts");
        // do nothing
    //    log.debug("Filter destruction finished");
    }
}