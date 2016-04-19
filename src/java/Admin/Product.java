/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import dao.CategoryJpaController;
import dao.ProductJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Category;

/**
 *
 * @author Mohammed
 */
@WebServlet(name = "Product", urlPatterns = {"/Admin/Product"})
public class Product extends HttpServlet {

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

        String productMode = request.getParameter("productMode");
        if (productMode != null) {
            if (productMode.equals("add")) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
                CategoryJpaController controller = new CategoryJpaController(emf);
                request.setAttribute("allCategories", controller.findCategoryEntities());
                request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
            } else if (productMode.equals("insert")) {
                insert(request, response);
            }
        }

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

    private void insert(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("productName");
        String desc = request.getParameter("productDescription");
        BigDecimal price = new BigDecimal(request.getParameter("productPrice"));
         String cat=request.getParameter("categories");
         
         pojo.Product product=new pojo.Product();
         product.setProductName(name);
         product.setProductDescription(desc);
         product.setProductPrice(price);
        List<pojo.Category> categories=new ArrayList<>();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Category.findByCategorName");
        query.setParameter("categorName", cat);
        pojo.Category category=(pojo.Category) query.getSingleResult();
         categories.add(category);
         product.setCategoryList(categories);
        
        ProductJpaController controller=new ProductJpaController(emf);
        controller.create(product);
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Category</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Category at " + request.getContextPath() + "</h1>");
            out.println("<h1> name :" + name +  "  is created</h1>");
            
//            out.println("<h1> products :"+category.get+"</h1>");
//            out.println("<h1> subCat :"+subCat+"</h1>");
//            

            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
