import java.sql.*;
import java.util.*;

public class JDBCTest05 {
    public static void main(String[] args) {
        /*
        * 1.处理查询结果集
        * 2.用户登录例子
        * 3.Statement 和 preparedStatement的区别
        *   （1）sql注入
        *   （2）编译次数
        *   （3）安全检查
        *   （4）使用场景：sql拼接用Statement，单纯传值用preparedStatement
        *   （5）preparedStatement的增删改查
        * 4.升序降序的例子（封装）
        * 5.JDBC的事务
        * 6.连接postgreSql
        * */

        /*
        * @TaskId：01
        * @Title：练习处理查询结果集
        * @Author：小于
        * */
        //每日亿遍JDBC六步
        Connection conn = null; //连接对象
        Statement stmt = null; //数据库操作对象
        ResultSet rs = null; //查询结果集

        try{
            //1.注册驱动
            ResourceBundle rb = ResourceBundle.getBundle("JDBC");
            String url = rb.getString("url");
            String user = rb.getString("user");
            String password = rb.getString("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection(url,user,password);
            //3.获取数据库操作对象
            stmt = conn.createStatement();
            //4.执行sql
            String sql = "select * from tb_user";
            rs = stmt.executeQuery(sql); //executeQuery("查询") executeUpdate("删除","添加","修改")
            //5.处理查询结果集
            while(rs.next()){  //rs.next()方法用于读取结果集中的下一行，如果有数据返回ture，相反false
                System.out.print(rs.getInt("id")+"  ");
                System.out.println(rs.getString("name")); //rs.getString("按照字段读取");rs.getString("序号读取")
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //6.关闭资源   从后往前关闭资源 从前往后开启资源
            try{
                if(rs != null){  //关闭查询结果集
                    rs.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(stmt != null){ //关闭数据库操作对象
                    stmt.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn != null){ //关闭数据库连接
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }


    }
}
