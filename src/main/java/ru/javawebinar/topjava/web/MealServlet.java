package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    //private static final Logger log = getLogger(MealServlet.class);
    private static final org.slf4j.Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceededByCycle(meals,LocalTime.of(0,0), LocalTime.of(23,0),2000);
        req.setAttribute("mealss", mealWithExceeds);
     //   resp.sendRedirect("meals.jsp");

        // чтобыForward (перенаправить) запрос к jstl_core_example01.jsp
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/meals.jsp");

        // Forward (перенаправить) запрос, чтобы отобразить данные на странице JSP.
        dispatcher.forward(req, resp);
        //super.doGet(req, resp);
    }
}
