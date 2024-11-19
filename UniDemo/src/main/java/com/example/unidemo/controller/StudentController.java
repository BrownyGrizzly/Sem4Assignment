package com.example.unidemo.controller;

import com.example.unidemo.entity.Student;
import com.example.unidemo.service.StudentService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentService();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "LIST";
        }

        switch (action) {
            case "ADD":
                showAddForm(request, response);
                break;
            case "EDIT":
                showEditForm(request, response);
                break;
            case "DELETE":
                deleteStudent(request, response);
                break;
            default:
                listStudents(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "SAVE";
        }

        switch (action) {
            case "SAVE":
                saveStudent(request, response);
                break;
            case "UPDATE":
                updateStudent(request, response);
                break;
            default:
                response.sendRedirect("error.jsp?message=Invalid action");
        }
    }

    // List all students
    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Student> students = studentService.findAll();
            request.setAttribute("students", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log("Error retrieving students", e);
            request.setAttribute("errorMessage", "Error retrieving students");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Show form to add a new student
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    // Show form to edit an existing student
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Student student = studentService.findById(id);
            if (student == null) {
                response.sendRedirect("error.jsp?message=Student not found");
                return;
            }
            request.setAttribute("student", student);
            RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=Error retrieving student for editing");
        }
    }

    // Save a new student
    private void saveStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Student student = new Student();
            student.setName(request.getParameter("name"));
            student.setEmail(request.getParameter("email"));
            student.setPhone(request.getParameter("phone"));
            studentService.save(student);
            response.sendRedirect("student?action=LIST");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=" + e.getMessage());
        }
    }

    // Update an existing student
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = studentService.findById(id);
            if (student == null) {
                response.sendRedirect("error.jsp?message=Student not found");
                return;
            }
            student.setName(request.getParameter("name"));
            student.setEmail(request.getParameter("email"));
            student.setPhone(request.getParameter("phone"));
            studentService.update(student);
            response.sendRedirect("student?action=LIST");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=" + e.getMessage());
        }
    }

    // Delete a student
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Student student = studentService.findById(id);
            if (student == null) {
                response.sendRedirect("error.jsp?message=Student not found");
                return;
            }
            studentService.delete(id);
            response.sendRedirect("student?action=LIST");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=Error deleting student");
        }
    }
}
