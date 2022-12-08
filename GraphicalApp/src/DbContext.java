import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbContext {
    public Statement stmt = null;
    public DbContext() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url;
        url = "jdbc:sqlserver://localhost;databaseName=YGTU";
        Connection con = DriverManager.getConnection(url, "gosha", "gosha");
        stmt = con.createStatement();
        GlobalVariables.errorMessage.setText("<html><font color=5EDE45>Соединение с БД установлено</font></html>");
    }
}
