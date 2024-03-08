package utils;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyDAO {
    public void insertText(Connection connection, String text) throws SQLException {
        String sql = "INSERT INTO my_table (text_column) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Clob clob = connection.createClob();
            clob.setString(1, text);
            statement.setClob(1, clob);
            statement.executeUpdate();
        }
    }
}

