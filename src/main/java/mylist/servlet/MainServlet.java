package mylist.servlet;

import mylist.manager.CategoryManager;
import mylist.manager.ListManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/home")
public class MainServlet extends HttpServlet {

    private ListManager listManager = new ListManager();
    private CategoryManager categoryManager = new CategoryManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String catIdStr = req.getParameter("catId");
        List<mylist.model.List> items;
        if (catIdStr == null || catIdStr.equals("")) {
            items = listManager.getLast20Items();

        } else {
            int catId = Integer.parseInt(catIdStr);
            items = listManager.getLast20ItemsByCategory(catId);
        }
        req.setAttribute("items", items);
        req.setAttribute("categories", categoryManager.getAllCategories());
        req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);

    }
}
