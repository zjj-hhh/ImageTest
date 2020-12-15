package file;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import deal.util.JDBCUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "ImgUploadServlet")
public class ImgUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String uploadPath = "E:\\upload";// 上传文件的目录
    private Connection con = null;
    private Statement st = null;
    private String sql = null;
    private ResultSet rs = null;
    private int count = 0;
    File tempPathFile;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            con = JDBCUtil.getConnection();
            st = con.createStatement();
//            sql = "select money from users where username='" + (String)session.getAttribute("loginUsername") + "'";
//            rs = st.executeQuery(sql);
//            while(rs.next()){
//                count = rs.getInt("money");
//            }
            count++;
            File file =new File(uploadPath + "\\current.txt");
            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }
            //true = append file
            FileWriter fileWritter = new FileWriter(file,true);


            System.out.println(file.getAbsolutePath());

            System.out.println("执行到这里了");
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Set factory constraints
            factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
            factory.setRepository(tempPathFile);// 设置缓冲区目录

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Set overall request size constraint
            upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

            List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
            System.out.println(items);
            Iterator<FileItem> i = items.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                String fileName = fi.getName();
                if (fileName != null) {
                    File fullFile = new File(new String(fi.getName().getBytes(), "utf-8")); // 解决文件名乱码问题
                    File savedFile = new File(uploadPath, fullFile.getName());
                    fi.write(savedFile);
                    String imgname = new String(fi.getName().getBytes(), "utf-8");
                    String imgpath = uploadPath + "//"+ new String(fi.getName().getBytes(), "utf-8");
                    String username = (String)session.getAttribute("loginUsername");
                    sql = "replace into img (imgname,imgpath,username,count) values('" + imgname + "','" + imgpath + "','"+ username +"',"+ count +")";
                    st.executeUpdate(sql);
                    fileWritter.write(new String(fi.getName().getBytes(), "utf-8") + "\r\n");
                    System.out.println(new String(fi.getName().getBytes(), "utf-8"));
                }
            }
            fileWritter.flush();
            fileWritter.close();
            Process proc = Runtime.getRuntime().exec("python  E:\\upload\\ImageTest.py");
            System.out.println("运行至此");
            proc.waitFor();
            //清空 current.txt
            System.out.print("upload succeed");
            fileWritter = new FileWriter(file);
            fileWritter.write("");
            fileWritter.flush();
            fileWritter.close();
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
