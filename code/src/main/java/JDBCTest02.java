
import java.sql.*;

public class JDBCTest02 {
    public static void main(String[] args) throws Exception {
        //JDBC 链接 数据库 六步
        //1.注册驱动
        Driver driver = new com.mysql.cj.jdbc.Driver(); //多态 父引用指向子对象( =左边是接口，=右边是接口的实现类对象)
        DriverManager.registerDriver(driver);   //com.mysql.cj.jdbc.Driver实现了Java.sql.Driver接口
        //2.获取链接对象
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url,user,password);
//        3.获取数据库操作对象
        Statement stmt = conn.createStatement();
//        4.执行sql
        String sql = "select * from tb_user";
        stmt.executeQuery(sql);
//        5.处理查询结果集
        while(stmt.getResultSet().next()){
            System.out.println(stmt.getResultSet().getString(2));
        }
//        6.释放资源
        stmt.close();
        conn.close();
    }


}
