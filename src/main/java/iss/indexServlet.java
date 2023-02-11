package iss;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@WebServlet(name = "indexServlet",urlPatterns = "/")
public class indexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        start(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        start(request,response);
    }


    private  void  start(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();

        //第一种方式
//        String type=request.getParameter("type");
//        if(type.equals("add")){
//            //调用添加方法
//            userController userController=new userController();
//            int ret=userController.addUser(request);
//            writer.write(JSON.toJSONString(ret));
//        }
//        if(type.equals("list")){
//            userController userController=new userController();
//            //调用查询方法
//            List<Users> list=userController.getList(request);
//            writer.write(JSON.toJSONString(list));
//
//        }


        // 获取字符串  /webServer/userController/getList
//        String path=request.getRequestURI();

        // 获取字符串  /userController/getList
        String path=request.getServletPath();
        //获取类名称
        String className = path.substring(1, path.lastIndexOf("/"));
        //获取方法名称
        String methodName = path.substring(path.lastIndexOf("/") + 1);

        System.out.println(className + "," + methodName);

        //通过反射调用类的方法
        try {
            //将字符串识别为一个java类
            Class<?> clazz = Class.forName("com.swpu.controller." + className);
            //创建类的对象
            Object instance = clazz.getConstructor().newInstance();
            //将字符串识别为一个方法
            Method method = clazz.getMethod(methodName,HttpServletRequest.class);
            //调用方法
            Object object = method.invoke(instance, request);
            writer.write(JSON.toJSONString(object));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
