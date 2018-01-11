package gui;
/*
 * @uthor: Vlad Ciobanu
 * @student ID: C15716369
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LottoServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String number1 = request.getParameter("number1");
		 String number2 = request.getParameter("number2");
		 String number3 = request.getParameter("number3");
		 String number4 = request.getParameter("number4");
		 String number5 = request.getParameter("number5");
		 String number6 = request.getParameter("number6");
		 String user = request.getParameter("username");
		 
		 PrintWriter out = response.getWriter();
		 out.println("<html>");
		 out.println("Your 1 pick is: " + number1);
		 out.println("<br/>");
		 out.println("Your 2 pick is: " + number2);
		 out.println("<br/>");
		 out.println("Your 3 pick is: " + number3);
		 out.println("<br/>");
		 out.println("Your 4 pick is: " + number4);
		 out.println("<br/>");
		 out.println("Your 5 pick is: " + number5);
		 out.println("<br/>");
		 out.println("Your 6 pick is: " + number6);
		 out.println("<br/>");
		 out.println("The username is: " + user);
		 out.println("<br/>");
		 ArrayList<String> v = new ArrayList<String>();
		 v.add(number1);
		 v.add(number2);
		 v.add(number3);
		 v.add(number4);
		 v.add(number5);
		 v.add(number6);
		 validation(user,v, out);
		 out.println("</html>");
	}

	public boolean saveInt(String test){
		try {
			Integer.parseInt(test);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void validation(String user, ArrayList<String> v, PrintWriter out){

		if (saveInt(user)){
			out.println("Your name cannot be a number!");
			out.println("<br/>");
			return;
		}
		for (String string : v) {
			if (string.length() == 0) {
				out.println("You must enter at least 6 numbers!");
				out.println("<br/>");
				return;
			} else if (!saveInt(string)) {
				out.println("This entry is not a number: " + string);
				out.println("<br/>");
				return;
			} else if (!(Integer.parseInt(string) > 0 && Integer.parseInt(string) < 48)) {
				out.println("Your pick " + string + "is not between 1 and 47");
				out.println("<br/>");
				return;
			}else if(v.contains(Integer.parseInt(string))){
				System.out.println(v);
				System.out.println(string);
				out.println("You can't pick the same number more than once");
				out.println("<br/>");
				return;
			}
		}
		Collections.sort(v, new Comparator<String>(){
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareTo(arg1);
			}
		});
		out.println("<br/>");
		out.println("Your numbers are: ");
		for (String string : v) {
			out.print(string + ", ");
		}
		out.println("<br/>");
		//====================
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lotto?useSSL=false", "root", "root");
			// here lotto is database name, root is username and password
			
			Statement insert = con.createStatement();
			insert.execute("INSERT INTO Lotto (ID, Username, Numar1, Numar2, Numar3, Numar4, Numar5, Numar6) VALUES (0, '"+user+"', "+v.get(0)+","+v.get(1)+","+v.get(2)+","+v.get(3)+","+v.get(4)+","+v.get(5)+")");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from lotto ORDER BY ID DESC LIMIT 5");

			out.println("Your last 5 entries are: ");
			out.println("<br/>");
			out.println("<br/>");
			while (rs.next()) {
				out.println(rs.getString(2) + "  "
						+ "  " + rs.getInt(3) + "  " + rs.getInt(4) + "  "
						+ rs.getInt(5) + "  " + rs.getInt(6) + "  "
						+ rs.getInt(7) + "  " + rs.getInt(8));
				out.println("<br/>");
			}
			con.close();
		} catch (Exception e){
			System.out.println(e);
		}
		//====================
	}
}
