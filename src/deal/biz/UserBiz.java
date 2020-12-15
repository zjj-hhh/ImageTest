package deal.biz;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface UserBiz {

	//用户登录
	String userLogin(String username, String password, HttpServletRequest request);

	//用户注册
	String userRegister(String username, String password, String againpassword,String mailbox,String fullname,String verifycode,HttpServletRequest request);

	//用户修改密码
	String userResetPassword(String accountNumber,String passwordOne, String passwordTwo,String verifyCode, HttpServletRequest request);

	//删除用户
	String deleteUser(String userID);

	//更改用户
	String updateUser(String userID,String userName,String userPassword,String userAuthority);
}