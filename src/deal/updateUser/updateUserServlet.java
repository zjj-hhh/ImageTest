package deal.updateUser;
import deal.biz.UserBiz;
import deal.bizimpl.UserBizImpl;
import deal.entity.User;
import deal.sendMail.sendMail;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class updateUserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            response.setContentType("text/json; charset=utf-8");
            request.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            String userID = request.getParameter("user_id");
            String userName = request.getParameter("user_name");
            String userPassword = request.getParameter("user_password");
            String userAuthority = request.getParameter("user_authority");

            System.out.println(userID);
            System.out.println(userName);
            System.out.println(userPassword);
            System.out.println(userAuthority);

            UserBiz userBiz = new UserBizImpl();
            String result = userBiz.updateUser(userID,userName,userPassword,userAuthority);

            JSONObject returnData = new JSONObject();
            returnData.put("msg",result);
            out.print(returnData);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
