import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.*;

public class JDBCTest04 {
    public static void main(String[] args) {
        //jdbc 六步
        ResourceBundle bundle = ResourceBundle.getBundle("JDBC"); //读取配置文件
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        String driver = bundle.getString("driver");
        Statement stmt = null;
        Connection conn = null;
        try{
            //1.注册驱动
            Class.forName(driver);
            //2.获取数据库链接
            conn = DriverManager.getConnection(url,user,password);
            //3.获取数据库操作对象
            stmt =  conn.createStatement();
            String sql = "insert into tb_user values(8,'lw')";
            //4.执行sql
            stmt.executeUpdate(sql);
            //5.处理查询结果集
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //6.关闭资源 从后往前关闭
            try{
                stmt.close();
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println("插入数据成功！");
        }
    }
}
