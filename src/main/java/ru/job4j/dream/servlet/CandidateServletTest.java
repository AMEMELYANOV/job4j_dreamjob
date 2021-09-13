package ru.job4j.dream.servlet;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.Store;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.MemStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class CandidateServletTest {

    @Test
    public void whenDoGetWork() throws IOException, ServletException {
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        PowerMockito.when(req.getSession()).thenReturn(session);
        PowerMockito.when(req.getRequestDispatcher("candidates.jsp")).thenReturn(dispatcher);

        new CandidateServlet().doGet(req, resp);

        Mockito.verify(req, Mockito.times(1)).getRequestDispatcher("candidates.jsp");
        Mockito.verify(dispatcher, Mockito.times(1)).forward(req, resp);
    }

    @Test
    public void whenDoPostWork() throws IOException, ServletException {
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        PowerMockito.when(req.getParameter("id")).thenReturn("0");
        PowerMockito.when(req.getParameter("name")).thenReturn("n");

        new CandidateServlet().doPost(req, resp);

        Candidate result = store.findAllCandidates().iterator().next();
        Assert.assertThat(result.getName(), Is.is("n"));
    }
}