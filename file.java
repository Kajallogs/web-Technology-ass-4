first.java

import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  


public class FirstServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  

    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  

    String n=request.getParameter("username");  
    String p=request.getParameter("userpass");  

    if(LoginDao.validate(n, p)){  
        RequestDispatcher rd=request.getRequestDispatcher("servlet2");  
        rd.forward(request,response);  
    }  
    else{  
        out.print("Sorry username or password error");  
        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
        rd.include(request,response);  
    }  

    out.close();  
    }  
}  




connection

import java.sql.*;  

public class LoginDao {  
public static boolean validate(String name,String pass){  
boolean status=false;  
try{  
Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  

PreparedStatement ps=con.prepareStatement(  
"select * from userreg where name=? and pass=?");  
ps.setString(1,name);  
ps.setString(2,pass);  

ResultSet rs=ps.executeQuery();  
status=rs.next();  

}catch(Exception e){System.out.println(e);}  
return status;  
}  
}  



WelcomeServlet.java

import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

public class WelcomeServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  

    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  

    String n=request.getParameter("username");  
    out.print("Welcome "+n);  

    out.close();  
    }  

}  