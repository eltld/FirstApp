package com.sun.querysalary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import com.sun.querysalary.bean.CompanyBean;
import com.sun.querysalary.bean.JobBean;
import com.sun.querysalary.db.DbManager;
/**
 * 具体某一公司某一个岗位的详情
 * @author sunqm
 *
 */
public class JobDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JobDetailServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject object = null;
		try {
			object = Json(query(request));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
	
		if(null==object){
			out.println("{code:-1}");
		}else{
			out.println(object);
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		dbManager = new DbManager();
	}

	private DbManager dbManager = null;
	
	private List<JobBean> query(HttpServletRequest request) throws SQLException{
		String jobId = request.getParameter("jobId");
		String companyId = request.getParameter("companyId");
		String sql = "select salary from job left join " +
				"company on company.id = job.companyId where job.id = '"+jobId+"' and companyId = '"+companyId+"'";
		System.out.print(sql);
		ResultSet rs = dbManager.doQuery(sql);
		List<JobBean> list = new ArrayList<JobBean>();
		JobBean job = null;
		while(rs.next()){
			job = new JobBean();
			job.setSalary(rs.getInt("salary"));
			list.add(job);
		}
		return list;
	}
	
	private JSONObject Json(List<JobBean> list) throws JSONException{
		JSONObject object = new JSONObject();
		object.put("size", list.size());
		object.put("code", 0);
		JSONArray array = new JSONArray();
		JSONObject item = null;
		for(int i = 0;i<list.size();i++){
			item = new JSONObject();
			item.put("salary",list.get(i).getSalary());
			array.put(item);
		}
		object.put("list", array);
		
		return object;
	}
	
}
