/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ProductJpaController;
import dao.UserJpaController;
import dao.UserOrderJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Product;
import pojo.UserOrder;

/**
 *
 * @author Mohammed
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

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

        String mode = request.getParameter("orderMode");
        if (mode.equals("add")) {

            addToOrder(request, response);
        } else if (mode.equals("check")) {

            checkifProductExistInOrder(request, response);
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

    private void addToOrder(HttpServletRequest request, HttpServletResponse response) {
        int prodId = Integer.parseInt(request.getParameter("prod"));

        int userId = Integer.parseInt(request.getParameter("user"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");

        ProductJpaController productController = new ProductJpaController(emf);
        UserJpaController userController = new UserJpaController(emf);
        UserOrderJpaController orderController = new UserOrderJpaController(emf);

        pojo.User user = userController.findUser(userId);

        pojo.UserOrder userOrder = new pojo.UserOrder();

        List<UserOrder> orderList = user.getOrder1List();

        Product product = productController.findProduct(prodId);

        if (orderList.size() > 0) {
            try {
                for (UserOrder order : orderList) {
                    if (order != null && order.getOrderStatus().equals("open")) {
                        userOrder = order;
                        break;
                    }
                }
                userOrder.getProductList().add(product);
                userOrder.setOrderAmount(userOrder.getOrderAmount().add(product.getProductPrice()));
                orderController.edit(userOrder);

            } catch (Exception ex) {
                try {
                    ex.printStackTrace(response.getWriter());
                } catch (IOException ex1) {
                    Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } else {

            ArrayList<Product> productList = new ArrayList<>();

            productList.add(product);
            userOrder.setProductList(productList);

            ArrayList<pojo.User> userList = new ArrayList<>();
            userList.add(user);
            userOrder.setUserList(userList);

            userOrder.setOrderStatus("open");
            userOrder.setOrderAmount(product.getProductPrice());
            userOrder.setOrderDate(new Date());
            orderController.create(userOrder);

        }

        try (PrintWriter out = response.getWriter()) {
            out.println(userOrder.getOrderAmount().toString());
        } catch (IOException ex) {
            try {
                ex.printStackTrace(response.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }

    private void checkifProductExistInOrder(HttpServletRequest request, HttpServletResponse response) {

        int prodId = Integer.parseInt(request.getParameter("prod"));

        int userId = Integer.parseInt(request.getParameter("user"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");

        UserJpaController userController = new UserJpaController(emf);

        pojo.User user = userController.findUser(userId);

        pojo.UserOrder userOrder = new pojo.UserOrder();

        List<UserOrder> orderList = user.getOrder1List();

        String result = "false";
        if (orderList.size() > 0) {
            for (UserOrder order : orderList) {

                if (order != null && order.getOrderStatus().equals("open")) {
                    userOrder = order;
                    break;

                }
            }

            if (userOrder.getProductList().size() > 0) {
                for (Product orderProduct : userOrder.getProductList()) {
                    if (orderProduct.getProductId() == prodId) {
                        result = "true";
                    }
                }
            }

        }
        try {
            response.getWriter().print(result);
        } catch (IOException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
