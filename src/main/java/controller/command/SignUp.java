package controller.command;

import db.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/** RU: в этом сервлете обработка процесса регистрации нового пользователя
 * ENG: processing the new user registration process in this servlet
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
    private static final Logger log = Logger.getLogger(SignUp.class.getName());
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/common/sign-up.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        try {
            if (UserDAO.validate(username, password)) {
                log.error("Such user already exists");
                request.getRequestDispatcher("/WEB-INF/common/sign-up.jsp").forward(request, response);
            } else {
                if (UserDAO.addUser(username, password, role)){
                    log.info("New user has been registered");
                    request.getRequestDispatcher("/Login").forward(request, response);
                }
            }
        } catch (SQLException | ClassNotFoundException | ServletException e){
            e.printStackTrace();
        }
        out.close();
    }
}