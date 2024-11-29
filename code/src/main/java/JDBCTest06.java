import com.sun.security.jgss.GSSUtil;

import java.sql.*;
import java.util.*;

public class JDBCTest06 {
    public static void main(String[] args) {
        /*
         * 1.处理查询结果集
         * 2.用户登录例子
         * 3.Statement 和 preparedStatement的区别-------sql拼接用Statement,读取用
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
         * @TaskId：02
         * @Title：练习用户登录例子
         * @分析：用户输入账号和密码，去数据库中查询是否有这个账号和密码，如果有返回登录成功（先用Statement,尝试sql注入带来的影响）
         * @Author：小于
         * */
        Map<String,String> userInfo = initUI();  //获取存放用户名和密码的map集合
        boolean flag = login(userInfo);
        System.out.println(flag ? "登录成功" : "账号或密码错误，请重新输入！");

    }

    public static Map<String , String> initUI(){ //调用方法，获取存放用户名和密码的Map集合

        Scanner sc = new Scanner(System.in);

        System.out.print("请输入用户名：");
        String name = sc.nextLine();
        System.out.print("请输入密码：");
        String psswd = sc.nextLine();

        Map<String,String> userInfo = new HashMap<String , String>();
        userInfo.put("name",name);
        userInfo.put("password",psswd);
        return userInfo;

    }

    public static boolean login(Map<String,String> userInfo){
        boolean flag = false;
        //JDBC六步代码
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");
        String name = userInfo.get("name");
        String psswd = userInfo.get("password");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url,user,password);

            stmt = conn.createStatement();

            String sql = "select * from tb_user where name = '"+name+"' and password = '"+psswd+"'";
            rs = stmt.executeQuery(sql);

            if(rs.next()){
               flag = true;
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return flag;
        }
    }
}
