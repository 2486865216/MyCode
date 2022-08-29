package com.example.cookie_session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "Session", value = "/session")
public class Session extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createSession(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void createSession(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        boolean isnew = session.isNew();
        String id = session.getId();
        session.setAttribute("user","hello");
        Object user = session.getAttribute("user");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(isnew+id+user);
    }
}
