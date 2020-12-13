package deal.register;

import deal.biz.UserBiz;
import deal.bizimpl.UserBizImpl;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class register
 */
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("register_username");
		String password = request.getParameter("register_password");
		String againpassword = request.getParameter("rpassword");
		try {
			UserBiz userBIZ = new UserBizImpl();
			String result = userBIZ.userRegister(username, password, againpassword,request);
			JSONObject data = new JSONObject();
			data.put("msg",result);
			out.print(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
