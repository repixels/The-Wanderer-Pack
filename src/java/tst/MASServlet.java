/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tst;

import dao.CategoryJpaController;
import dao.ProductJpaController;
import dao.UserJpaController;
import dao.UserOrderJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import pojo.Category;
import pojo.Product;
import pojo.User;
import pojo.UserOrder;


/**
 *
 * @author Mohammed
 */
@WebServlet(name = "MASServlet", urlPatterns = {"/MASServlet"})
public class MASServlet extends HttpServlet {

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MASServlet</title>");
            out.println("</head>");
            out.println("<body>");
          //  <editor-fold defaultstate="collapsed" desc="create new product withCategory ">  
            
            Product product1=new Product();
            product1.setProductName("product4 ");
            product1.setProductPrice(new BigDecimal(10));
          
            CategoryJpaController categoryController=new CategoryJpaController(emf);
            
            ArrayList<Category> categoriesList=new ArrayList<Category>();
            Category foundCategory1=em.find(Category.class, 1);
            Category foundCategory2=em.find(Category.class, 2);
          
            categoriesList.add(foundCategory1);
            categoriesList.add(foundCategory2);
            product1.setCategoryList(categoriesList);
            
            ProductJpaController productController=new ProductJpaController(emf);  
           productController.create(product1);
           
          //</editor-fold>
          
            
            
          //  <editor-fold defaultstate="collapsed" desc="create new category ">  
//            CategoryJpaController categoryController=new CategoryJpaController(emf);
//            
//            //create new category
//            Category category =new Category();
//            category.setCategorName("cat1");
//            category.setCategoryDescription("first description");
//          
//            categoryController.create(category);
//            
//            //finding category
//            Category foundCategory=categoryController.findCategory(1);
//            
//            //create new category with foundCategory as parent category 
//            Category category2 =new Category();
//            category2.setCategorName("cat2");
//            category2.setCategoryDescription("second description");
//            category2.setCategoryParentId(foundCategory);
//            categoryController.create(category2);
//            
//            </editor-fold>
//            
            
          //  <editor-fold defaultstate="collapsed" desc="create new user">
//            try {
//                User user = new User(); //don't set userId 
            
//                //set user attributes
//                user.setUsername("mas"); 
//                user.setEmail("mas@mas.com");
//                user.setPassword("mas");
//            
//                // create user controller
//                UserJpaController userController = new UserJpaController(emf);
//                userController.create(user);
//                  //end of data base            
//                out.println("<h1>user Created </h1>");
//            } catch (Exception ex) {
//                out.println("<h1>Error " + ex.getMessage() + "</h1>");
//                Logger.getLogger(MASServlet.class.getName()).log(Level.SEVERE, null, ex);
//                ex.printStackTrace(out);
//            }

//            out.println("<h1>Servlet MASServlet at " + request.getContextPath() + "</h1>");
            //</editor-fold>

     //  <editor-fold defaultstate="collapsed" desc="create new order">
//            // create order
//            UserOrder order=new UserOrder();
//            
//            //format date to match database date
//            SimpleDateFormat formater=new SimpleDateFormat("MM-dd-yyyy");
//            String d=formater.format(new Date());
//            Date myDate=formater.parse(d);
//            order.setOrderDate(myDate);
//            
//            order.setOrderStatus("t");
//            order.setOrderAmount(new BigDecimal(2.0));
//            
//            //create list of product in order
//            Product p1=em.find(Product.class, 1);
//            Product p2=em.find(Product.class, 2);
//            Product p3=em.find(Product.class, 3);
//            Product p4=em.find(Product.class, 4);
//            
//            ArrayList<Product> productList=new ArrayList<Product>();
//            productList.add(p1);
//            productList.add(p2);
//            productList.add(p3);
//            productList.add(p4);
//            order.setProductList(productList);
//            
//            //add user
//            User user=em.find(User.class, 1);
//            ArrayList<User> userList=new ArrayList<User>();
//            userList.add(user);
//            order.setUserList(userList);
//            
//            //create order
//            UserOrderJpaController orderController=new UserOrderJpaController(emf);
//            orderController.create(order);
//</editor-fold>
            
              out.println("<h1> Done </h1>");
            out.println("</body>");
            out.println("</html>");
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

}
