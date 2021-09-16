package ru.job4j.dream.servlet;

import org.json.JSONObject;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Store store = PsqlStore.instOf();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        JSONObject cities = new JSONObject();
        for (City city : store.findAllCities()) {
            cities.put(Integer.toString(city.getId()), city.getName());
        }
        try (PrintWriter out = resp.getWriter()) {
            out.print(cities);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 