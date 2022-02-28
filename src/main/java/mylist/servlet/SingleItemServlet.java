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


@WebServlet(urlPatterns = "/singleItem")
public class SingleItemServlet extends HttpServlet {

    private ListManager listManager = new ListManager();
    private CategoryManager categoryManager = new CategoryManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List itemById = listManager.getItemById(id);
        if (itemById == null) {
            resp.sendRedirect("/home");
        }else {
            req.setAttribute("item", itemById);
            req.setAttribute("categories", categoryManager.getAllCategories());
            req.getRequestDispatcher("/WEB-INF/singleItem.jsp").forward(req, resp);

        }

    }
}
