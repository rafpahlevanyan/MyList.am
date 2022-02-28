package mylist.servlet;


import mylist.manager.CategoryManager;
import mylist.manager.ListManager;
import mylist.manager.UserManager;
import mylist.model.Category;
import mylist.model.List;
import mylist.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(fileSizeThreshold = 1048 * 1048,
        maxFileSize = 1048 * 1048 * 5,
        maxRequestSize = 1048 * 1048 * 5 * 5)
public class AddItemServlet extends HttpServlet {

    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();
    private ListManager listManager = new ListManager();
    private static final String IMAGE_PATH = "C:\\Users\\User\\IdeaProjects\\MyList.am\\img\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryManager.getAllCategories());
        req.getRequestDispatcher("WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        Part filePart = req.getPart("picture");
        String fileName = filePart.getSubmittedFileName();
        String picUrl = System.nanoTime() + "_" + fileName;
        filePart.write(IMAGE_PATH + "_" + picUrl);

        String title = req.getParameter("title");
        Double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        int catId = Integer.parseInt(req.getParameter("cat_id"));
        Category category = categoryManager.getCategoryById(catId);

        List item = List.builder()
                .title(title)
                .price(price)
                .description(description)
                .category(category)
                .user(user)
                .picUrl(picUrl)
                .build();
        listManager.addItem(item);
        resp.sendRedirect("/home");


    }
}
