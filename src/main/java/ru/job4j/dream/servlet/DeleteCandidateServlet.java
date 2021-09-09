package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.MemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeleteCandidateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        File folder = new File("c:\\images\\");
        Candidate currentCandidate = MemStore.instOf().findCandidateById(id);
        if (currentCandidate.getPhoto() != null) {
            Files.deleteIfExists(Paths.get(folder + File.separator
                    + currentCandidate.getPhoto().getFileName()));
        }
        MemStore.instOf().deleteCandidateByID(id);
        doGet(req, resp);
    }
}