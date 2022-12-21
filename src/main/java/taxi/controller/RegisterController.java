package taxi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Driver;
import taxi.service.DriverService;

public class RegisterController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String license = req.getParameter("license");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password_repeat = req.getParameter("password_repeat");
        if (password.equals(password_repeat)) {
            Driver driver = new Driver();
            driver.setName(name);
            driver.setLicenseNumber(license);
            driver.setLogin(login);
            driver.setPassword(password);
            driverService.create(driver);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("msg", "Wrong data. Try again");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}
