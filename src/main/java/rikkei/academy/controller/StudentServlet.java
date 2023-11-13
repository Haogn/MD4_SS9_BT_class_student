package rikkei.academy.controller;

import rikkei.academy.dto.StudentDTO;
import rikkei.academy.model.entity.ClassRoom;
import rikkei.academy.model.service.ClassRoomService;
import rikkei.academy.model.service.StudentService;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private StudentService studentService = null ;
    private ClassRoomService classRoomService = null;
    @Override
    public void init() {
        studentService = new StudentService();
        classRoomService = new ClassRoomService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") ;
        if (action == null) {
            getAll(request,response);
        }

        switch (action) {
            case "add" :
                // lấy ra danh sách lơps học
                List<ClassRoom> list = classRoomService.findAll();
                request.setAttribute("list", list);
                request.getRequestDispatcher("views/student-add.jsp").forward(request,response);
                break;
            case "edit" :
                List<ClassRoom> list1 = classRoomService.findAll();
                request.setAttribute("list",list1);
                int id_edit = Integer.parseInt(request.getParameter("id"));
                StudentDTO student_edit = studentService.findById(id_edit);
                request.setAttribute("student_edit", student_edit);
                request.getRequestDispatcher("views/student-edit.jsp").forward(request,response);
                break;
            case "delete":
                int id_delete = Integer.parseInt(request.getParameter("id")) ;
                studentService.delete(id_delete);
                response.sendRedirect("/student");
                break;
            default:
                break;
        }

    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentDTO> list = studentService.findAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("views/student-list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        // lấy các giá trị ở ô input
        String name = request.getParameter("name");
        Integer classId = Integer.parseInt(request.getParameter("classId"));
        String dateStr = request.getParameter("birthday");
        // convert dateStr từ String sang kiểu dữ liệu sql.date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate;
        try {
            java.util.Date utilDate  = format.parse(dateStr);
            sqlDate = new java.sql.Date(utilDate.getTime()) ;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (action!=null) {
            switch (action) {
                case "add" :
                    StudentDTO student_add = new StudentDTO();
                    student_add.setName(name);
                    student_add.setClassID(classId);
                    student_add.setBirthday(sqlDate);
                    studentService.create(student_add);
                    response.sendRedirect("/student");
                    break;
                case "edit":
                    int id_edit = Integer.parseInt(request.getParameter("id"));
                    StudentDTO student_edit = new StudentDTO(name,sqlDate,classId) ;
                    studentService.update(student_edit,id_edit);
                    response.sendRedirect("/student");
                    break;
                default:
                    break;
            }
        }



    }

    @Override
    public void destroy() {

    }


}