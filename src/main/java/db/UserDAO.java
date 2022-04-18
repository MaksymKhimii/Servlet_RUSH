package db;

import db.entity.User;
import db.enums.UserRole;

import java.sql.*;
import java.util.ArrayList;

/** RU: слой ДАО для взаимодействия программы с таблицей user в базе дынных,
 *      которая хранит информацию о всех пользователях
 * ENG: DAO layer for program interaction with the user table in the database,
 *      which stores information about all users
 */
public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "Maks_Khimii";
    private static final String PASSWORD = "makskhimiy24112003";


    /** RU: метод для создания соединения между базой данных и программой
     * ENG: method to create connection between database and program
     * @return Connection
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/epam?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "root", "1234567h");
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return con;
    }

    /** RU: метод для валидации юзера при входе в систему
     * ENG: method for validating a user at login
     * @param name user's name
     * @param pass password
     * @return status(validation)
     */
    public static boolean validate(String name,String pass) {
        boolean status=false;
        try{
            String GETUSER="select * from user where username=? and password=?";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps=con.prepareStatement(GETUSER);
            ps.setString(1,name);
            ps.setString(2,pass);
            try (ResultSet rs=ps.executeQuery()){
                status=rs.next();
            }
        }catch(SQLException | ClassNotFoundException e){e.printStackTrace();}
        return status;
    }

    /** RU: метод получения роли пользователя
     * ENG: user role get method
     * @param username username
     * @param password password
     * @return user's role
     */
    public static String getRole(String username, String password) {
        String RoleQuery = "SELECT role FROM user WHERE username=? AND password = ?;";
        String role = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(RoleQuery);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                role = rs.getString("role");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return role;
    }

    /** RU: метод добавляет нового пользователя
     * ENG: method adds new user
     * @param username username
     * @param password password
     * @param role role
     * @return status
     */
    public static Boolean addUser(String username, String password, String role) throws SQLException, ClassNotFoundException {
        boolean status= false;
        String ADDUSER="insert into user(username,password,role) values(?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement ps=con.prepareStatement(ADDUSER);
        ps.setString(1,username);
        ps.setString(2,password);
        ps.setString(3, role);
        ps.execute();
       if(UserDAO.validate(username, password)){
           status=true;
       }
       return status;
    }

    /** RU: метод получает информацию о пользователе
     * ENG: method gets information about the user
     * @return All information about user
     */
    public static ArrayList<User> getAllUsers(){
        ArrayList<User> users= new ArrayList<>(){};
        String RoleQuery = "SELECT id, username, password, role FROM user;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = con.prepareStatement(RoleQuery);
                ResultSet rs=pstmt.executeQuery();
                boolean found= false;
                while (rs.next()){
                    User AllUsers = new User();
                    AllUsers.setId(rs.getInt("id"));
                    AllUsers.setLogin(rs.getString("username"));
                    AllUsers.setPassword(rs.getString("password"));
                    AllUsers.setRole(UserRole.valueOf(rs.getString("Role")));
                    users.add(AllUsers);
                    found= true;
                }
                rs.close(); pstmt.close();
                if(found){return users;}
                else {return null;}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return users;
    }
}
