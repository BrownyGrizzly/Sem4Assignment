package com.example.unidemo.controller;

import com.example.unidemo.entity.Subject;
import com.example.unidemo.service.SubjectService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SubjectController", value = "/subject")
public class SubjectController extends HttpServlet {
    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        super.init();
        subjectService = new SubjectService();
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
                deleteSubject(request, response);
                break;
            default:
                listSubjects(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("ADD".equals(action)) {
            addSubject(request, response);
        } else if ("EDIT".equals(action)) {
            updateSubject(request, response);
        }
    }

    // List subjects
    private void listSubjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Subject> subjects = subjectService.findAll();
            request.setAttribute("subjects", subjects);
            RequestDispatcher dispatcher = request.getRequestDispatcher("subject-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log("Error retrieving subjects", e);
            request.setAttribute("errorMessage", "Error retrieving subjects");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Show form to add a subject
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
        dispatcher.forward(request, response);
    }

    // Show form to edit a subject
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Subject subject = subjectService.findById(id);
            if (subject == null) {
                response.sendRedirect("error.jsp?message=Subject not found");
                return;
            }
            request.setAttribute("subject", subject);
            RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=Error retrieving subject for editing");
        }
    }

    // Add a new subject
    private void addSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            int hours = Integer.parseInt(request.getParameter("hours"));

            Subject subject = new Subject();
            subject.setName(name);
            subject.setHours(hours);

            subjectService.save(subject);
            response.sendRedirect("subject?action=LIST");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error adding subject");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Update an existing subject
    private void updateSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int hours = Integer.parseInt(request.getParameter("hours"));

            Subject subject = subjectService.findById(id);
            if (subject == null) {
                response.sendRedirect("error.jsp?message=Subject not found");
                return;
            }

            subject.setName(name);
            subject.setHours(hours);
            subjectService.update(subject);

            response.sendRedirect("subject?action=LIST");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error updating subject");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Delete a subject
    private void deleteSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Subject subject = subjectService.findById(id);
            if (subject == null) {
                response.sendRedirect("error.jsp?message=Subject not found");
                return;
            }
            subjectService.delete(id);
            response.sendRedirect("subject?action=LIST");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=Error deleting subject");
        }
    }
}
