package com.example.unidemo.controller;

import com.example.unidemo.entity.Classroom;
import com.example.unidemo.service.ClassroomService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClassroomController", value = "/classroom")
public class ClassroomController extends HttpServlet {
    private ClassroomService classroomService;

    @Override
    public void init() throws ServletException {
        super.init();
        classroomService = new ClassroomService();
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
                addClassroom(request, response);
                break;
            case "EDIT":
                editClassroom(request, response);
                break;
            case "DELETE":
                deleteClassroom(request, response);
                break;
            default:
                listClassrooms(request, response);
                break;
        }
    }

    // List classrooms
    private void listClassrooms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Classroom> classrooms = classroomService.findAll();
            request.setAttribute("classrooms", classrooms);
            RequestDispatcher dispatcher = request.getRequestDispatcher("classroom-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Log the error and redirect to an error page
            log("Error retrieving classrooms", e);
            request.setAttribute("errorMessage", "Error retrieving classrooms");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Show the form to add a new classroom
    private void addClassroom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("classroom-form.jsp");
        dispatcher.forward(request, response);
    }

    // Edit an existing classroom
    private void editClassroom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Classroom classroom = classroomService.findById(id); // Fetch classroom by ID
            if (classroom == null) {
                response.sendRedirect("error.jsp?message=Classroom not found");
                return;
            }
            request.setAttribute("classroom", classroom);
            RequestDispatcher dispatcher = request.getRequestDispatcher("classroom-form.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=Error retrieving classroom for editing");
        }
    }

    // Delete a classroom
    private void deleteClassroom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Classroom classroom = classroomService.findById(id);
            if (classroom == null) {
                response.sendRedirect("error.jsp?message=Classroom not found");
                return;
            }
            classroomService.delete(id); // Delete the classroom
            response.sendRedirect("classroom?action=LIST"); // Redirect to the list after deletion
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=Error deleting classroom");
        }
    }
}
