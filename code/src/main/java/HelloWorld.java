
import java.sql.*;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
public class HelloWorld {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try{
            //JDBC六步
            //1.注册驱动。com.mysql.jdbc.Driver()是java.sql.Driver的实现类，java.sql.driver中的Driver是个接口
            Driver driver = new com.mysql.cj.jdbc.Driver(); //多态
            DriverManager.registerDriver(driver); //参数要java.sql.Driver接口的实现类对象
            //2.获取连接对象。（java进程和数据库进程通信）
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
            System.out.println(conn);
            //3.获取数据库操作对象
            stmt = conn.createStatement();
            //4.执行sql
            //插入---insert
//            String sql = "insert into tb_user values(2,'zhangsna')";
//            stmt.executeUpdate(sql);
            //查询---select
//            String sql = "select * from tb_user";
//            stmt.executeQuery(sql);
            //删除---delete
//            String sql = "delete from tb_user where name='zhangsna'";
//            stmt.executeUpdate(sql);
            //修改---update
            String sql = "update tb_user set id = '4', name = '小于' where id='1'";
            stmt.executeUpdate(sql);
            //5.处理查询结果集
            try{
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    System.out.println(rs.getString("name"));
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{   //6.关闭资源
            try{
                if(stmt!=null){
                    stmt.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }


    }
}
