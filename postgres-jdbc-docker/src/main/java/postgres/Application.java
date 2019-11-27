package postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Application {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/springdata";
        String user = "hjmin";
        String pw = "hjmin";

        //try with resources : java 7 부터 추가됨
        try(Connection con = getConnection(url, user, pw)){
            System.out.println("Connection created : " + con);

            //String sql = "CREATE TABLE ACCOUNT ( id int, username varchar(255), password varchar(255) )";
            String sql = "INSERT INTO ACCOUNT VALUES(1, 'hjmin', 'hjmin')";
            try(PreparedStatement psmt = con.prepareStatement(sql)){
                psmt.execute();
            }

            System.out.println("================================");
        }
    }
}
