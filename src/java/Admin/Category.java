/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import dao.CategoryJpaController;
import dao.exceptions.NonexistentEntityException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import pojo.Product;

/**
 *
 * @author Mohammed
 */
@WebServlet(name = "Category", urlPatterns = {"/Admin/Category"})
@MultipartConfig
public class Category extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String categoryMode = request.getParameter("categoryMode");
        
        PrintWriter out = response.getWriter();
        
        if (categoryMode != null) {
            if (categoryMode.equals("insert")) {
                createNewCategory(request, response);
            } else if (categoryMode.equals("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                editCategory(request, response,id);
                
                out.print(categoryMode);
            } else if (categoryMode.equals("update")) {
                updateCategory(out, categoryMode, request);
            } else if (categoryMode.equals("delete")) {
                out.print(categoryMode);
                int id = Integer.parseInt(request.getParameter("id"));
                delete(request,response,id);
                
                
            }
            else if (categoryMode.equals("showAll")){
            
                showAll(request,response);
            }
        }

    }

    public void updateCategory(PrintWriter out, String categoryMode, HttpServletRequest request) throws IOException, ServletException, NumberFormatException {
        out.print(categoryMode);

        pojo.Category category = populateCategory(request);
        int id = Integer.parseInt(request.getParameter("categoryId"));

        category.setCategoryId(id);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        CategoryJpaController categoryController = new CategoryJpaController(emf);
        try {
            //to ne replaced with real data from user
            category.setProductList(new ArrayList<Product>());
            category.setCategoryList(new ArrayList<>());

            categoryController.edit(category);
        } catch (Exception ex) {
            out.print("Error");
            ex.printStackTrace(out);
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.print("</br>");

    }

    public void createNewCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        pojo.Category category = populateCategory(request);

        //  <editor-fold defaultstate="collapsed" desc="upload image "> 
//        Part part = request.getPart("categoryImage");
//        log("part:  "+part.getSubmittedFileName());
//        File path=new File("C:\\Users\\Mohammed\\Desktop\\upload\\");
//        path.mkdirs();
//        File file= new File(path.getAbsoluteFile()+"\\"+part.getSubmittedFileName());
//
//                
//        part.write(file.getAbsolutePath());
        //</editor-fold>
        //  <editor-fold defaultstate="collapsed" desc="create new category ">
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        CategoryJpaController categoryController = new CategoryJpaController(emf);

        //create new category
        categoryController.create(category);

//            </editor-fold>
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Category</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Category at " + request.getContextPath() + "</h1>");
            out.println("<h1> name :" + category.getCategorName() + "</h1>");
            out.println("<h1> desc :" + category.getCategoryDescription() + "</h1>");
            out.println("<h1> image :" + category.getCategoryImage() + "</h1>");
//            out.println("<h1> products :"+category.get+"</h1>");
//            out.println("<h1> subCat :"+subCat+"</h1>");
//            

            out.println("</body>");
            out.println("</html>");
        }
    }

    public pojo.Category populateCategory(HttpServletRequest request) throws IOException, ServletException {
        String name = request.getParameter("categorName");
        String desc = request.getParameter("categoryDescription");
        String products = request.getParameter("products");
        String subCat = request.getParameter("categoryList");

//        //TODO  add the saved folder of images to path of image
        Part part = request.getPart("categoryImage");
        String image = part.getSubmittedFileName();
        pojo.Category category = new pojo.Category();
        category.setCategorName(name);
        category.setCategoryDescription(desc);
        category.setCategoryImage(image);
        return category;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void editCategory(HttpServletRequest request, HttpServletResponse response,int id) throws IOException, ServletException {

        //  <editor-fold defaultstate="collapsed" desc="create new category ">
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        CategoryJpaController categoryController = new CategoryJpaController(emf);

        //create new category
        pojo.Category category = categoryController.findCategory(id);
        request.setAttribute("category", category);

        request.getRequestDispatcher("AddCategory.jsp").forward(request, response);

//            </editor-fold>
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Category</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Category at " + request.getContextPath() + "</h1>");

            out.println("</body>");
            out.println("</html>");
        }

    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
            EntityManager em = emf.createEntityManager();
            CategoryJpaController controller=new CategoryJpaController(emf);
            out = response.getWriter();
            for (pojo.Category cat : controller.findCategoryEntities()) {
                out.println("id : "+cat.getCategoryId()+"</br>");
                out.println("name : "+cat.getCategorName()+"</br>");
                out.println("desc : "+cat.getCategoryDescription()+"</br>");
            }
        request.setAttribute("allCategories", controller.findCategoryEntities());
        try {
            request.getRequestDispatcher("ShowAllCategories.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } catch (IOException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response, int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        CategoryJpaController controller=new CategoryJpaController(emf);
        try {
            controller.destroy(id);
            response.getWriter().println(" category with id : "+id +" is deleted");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
