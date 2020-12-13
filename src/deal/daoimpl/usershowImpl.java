package deal.daoimpl;

import deal.dao.Iuser;
import deal.entity.Page;
import deal.entity.usershow;
import deal.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class usershowImpl implements Iuser {
    public Connection con=null;
    public PreparedStatement pst=null;
    public Statement sm=null;
    public ResultSet rs=null;
    @Override
    public List<usershow> queryUsershowByPage(Page page) throws SQLException {
        List<usershow> arr = new ArrayList();

        try{
			/*Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/test";
			con = DriverManager.getConnection(url, "root", "z9682576");*/
            con = JDBCUtil.getConnection();
            Statement stat = con.createStatement();
            String sql = "select * from users limit ?,?";
            pst=con.prepareStatement(sql);
            pst.setInt(1, page.getIndex()*page.getPageSize());
            pst.setInt(2, page.getPageSize());
            rs=pst.executeQuery();
            while(rs.next()){
                usershow temp = new usershow(rs.getString("userid"),rs.getString("username"),rs.getString("password"),rs.getString("authority"),rs.getString("createTime"),rs.getInt("money"));
                arr.add(temp);
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {//关闭连接
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }
}