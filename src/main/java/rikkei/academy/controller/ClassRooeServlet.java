package rikkei.academy.controller;

import rikkei.academy.model.entity.ClassRoom;
import rikkei.academy.model.entity.Student;
import rikkei.academy.model.service.ClassRoomService;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ClassRooeServlet", value = "/class")
public class ClassRooeServlet extends HttpServlet {
    private ClassRoomService classRoomService = null;

    @Override
    public void init() {
        classRoomService = new ClassRoomService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ( action == null) {
            List<ClassRoom> classRooms = classRoomService.findAll();
            request.setAttribute("list", classRooms);
            request.getRequestDispatcher("views/class-list.jsp").forward(request,response);
        }
        switch (action) {
            case "detail" :
                Integer id_detail = Integer.parseInt(request.getParameter("id"));
                List<Student> list = classRoomService.findStudentByClassId(id_detail);
                request.setAttribute("class_detail", list);
                request.getRequestDispatcher("views/class-detail.jsp").forward(request,response);
                break;
            case "create":
                break;
            case "edit" :
                break;
            case "delete" :
                break;
            default:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }

}