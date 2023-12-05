package Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.sachDAO;
import model.sach;

/**
 * Servlet implementation class themAnhController
 */
public class themAnhController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public themAnhController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String url = "";
			String folder = getServletContext().getRealPath("avatar");
			
			File file;
			
			int maxfilesize = 5000*1024;
			int maxmemfile = 5000*1024;
			
			String contentType = request.getContentType();
			if(contentType.indexOf(contentType) >= 0) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(maxmemfile);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				upload.setSizeMax(maxfilesize);
				
				List<FileItem> files = upload.parseRequest(request);
				for(FileItem item : files) {
					if(!item.isFormField()) {
						String filename = System.currentTimeMillis() + item.getName();
						String part = folder + "//" + filename;
						
						file = new File(part);
						
						item.write(file);
						
						HttpSession session = request.getSession(false);
						sach s = (sach)session.getAttribute("sach");
						s.setDuongdananh(filename);
						
						sachDAO.getInstance().update(s);
						
						session.removeAttribute("sach");
						
						
					}
					else {
						System.out.println(item.getFieldName() +" : "+ item.getString());
					}
				}
				url = "/sachFE/themsach.jsp";
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
