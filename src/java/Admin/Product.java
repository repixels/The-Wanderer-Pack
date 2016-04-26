/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.google.gson.Gson;
import dao.CategoryJpaController;
import dao.ProductJpaController;
import dao.exceptions.NonexistentEntityException;
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
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.jpa.criteria.CriteriaQueryImpl;
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
        PrintWriter out = response.getWriter();
        
        if (productMode != null) {
            if (productMode.equals("add")) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
                CategoryJpaController controller = new CategoryJpaController(emf);
                request.setAttribute("allCategories", controller.findCategoryEntities());
                request.getRequestDispatcher("add-product.jsp").forward(request, response);
            } else if (productMode.equals("insert")) {
                insert(request, response);
            }else if (productMode.equals("showAll")){
            
                showAll(request,response);
            }
            else if (productMode.equals("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                showEditPage(request, response,id);
                
                
            }
            else if (productMode.equals("update")) {
                updateProduct( request,response);
            } 
            else if (productMode.equals("delete")) {
                
                int id = Integer.parseInt(request.getParameter("id"));
                delete(request,response,id);
                
                
            }
            else if(productMode.equals("search")){
                String name=request.getParameter("name");
                search(request,response,name);
            }
            else{
            response.getWriter().print(productMode);
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

    private void insert(HttpServletRequest request, HttpServletResponse response) 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        pojo.Product product = populateProduct(request);
        ProductJpaController controller=new ProductJpaController(emf);
        controller.create(product);

    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out;
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
            EntityManager em = emf.createEntityManager();
            ProductJpaController controller=new ProductJpaController(emf);
            request.setAttribute("allProducts", controller.findProductEntities());
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void showEditPage(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
            EntityManager em = emf.createEntityManager();
            ProductJpaController prController = new ProductJpaController(emf);
            
            //create new category
            pojo.Product product = prController.findProduct(id);
            request.setAttribute("product", product);
            
            CategoryJpaController catController = new CategoryJpaController(emf);
                request.setAttribute("allCategories", catController.findCategoryEntities());
                
            request.getRequestDispatcher("add-product.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    private void updateProduct( HttpServletRequest request, HttpServletResponse response) {
              pojo.Product product = populateProduct(request);
        int id = Integer.parseInt(request.getParameter("productId"));

        product.setProductId(id);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        ProductJpaController controller = new ProductJpaController(emf);
        
        try {
            //to ne replaced with real data from user
            controller.edit(product);
            showAll(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        

    }

    private pojo.Product populateProduct(HttpServletRequest request) {
         String name = request.getParameter("productName");
        String desc = request.getParameter("productDescription");
        BigDecimal price = new BigDecimal(request.getParameter("productPrice"));
         int cat= new Integer(request.getParameter("categories"));
         
         
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Category.findByCategoryId");
        query.setParameter("categoryId", cat);
        pojo.Category category=(pojo.Category) query.getSingleResult();
        List<pojo.Category> categories=new ArrayList<>();
         categories.add(category);
         
         
         pojo.Product product=new pojo.Product();
         product.setProductName(name);
         product.setProductDescription(desc);
         product.setProductPrice(price);
         product.setCategoryList(categories);
         return product;
        
    }

    private void delete(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
            ProductJpaController controller =new ProductJpaController(emf);
            controller.destroy(id);
            showAll(request, response);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void search(HttpServletRequest request, HttpServletResponse response, String name) {
        
        try {
            PrintWriter out = response.getWriter();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
            EntityManager em = emf.createEntityManager();

            Query query = em.createNativeQuery("SELECT * FROM Product p WHERE p.product_name LIKE '%"+name+"%'");
            Gson gson=new Gson();
            String result=gson.toJson(query.getResultList());
            out.print(result);
        } catch (IOException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
