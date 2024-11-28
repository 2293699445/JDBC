import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest03 {
    public static void main(String[] args) {
        //jdbc 六步
        //1.注册驱动
            /*
            * 由于java.sql.Driver的实现类com.mysql.cj.jdbc.Driver中的
            * 静态代码块执行了DriverManager.registerDriver(driver)注册驱动，
            * 因此只需要加载这个类，让静态代码块在加载的时候执行去加载驱动即可。
            * 这里需要用到反射机制
            * */
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "123456";
        String driver = "com.mysql.cj.jdbc.Driver";
        Statement stmt = null;
        Connection conn = null;
        try{
            //1.注册驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");//反射机制 ----参数是字符串，可以写到配置文件当中
            Class.forName(driver);
            //2.获取数据库链接
            conn = DriverManager.getConnection(url,user,password);
            //3.获取数据库操作对象
            stmt =  conn.createStatement();
            //4.执行sql
            String sql = "insert into tb_user values(3,'舒镐')";
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
