package mylist.servlet;

import mylist.manager.CategoryManager;
import mylist.manager.ListManager;
import mylist.model.List;
import mylist.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/myItems")
public class MyItemsServlet extends HttpServlet {

    private ListManager listManager = new ListManager();
    private CategoryManager categoryManager = new CategoryManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        java.util.List<List> items = listManager.getAllItemsByUser(user.getId());

        req.setAttribute("items", items);
        req.setAttribute("categories", categoryManager.getAllCategories());
        req.getRequestDispatcher("/WEB-INF/myItems.jsp").forward(req, resp);

    }
}
