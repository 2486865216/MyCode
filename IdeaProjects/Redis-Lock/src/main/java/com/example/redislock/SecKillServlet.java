package com.example.redislock;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "SecKillServlet", value = "/SecKillServlet")
public class SecKillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = new Random().nextInt(50000)+"";
        String prodid = request.getParameter("prodid");

        boolean isSuccess = SocKill_redis.doSecKill(userid, prodid);
        response.getWriter().print(isSuccess);
    }
}
