/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CategoryJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.Category;
import pojo.Interest;
import pojo.Product;
import pojo.User;

/**
 *
 * @author Ehab
 */
@WebServlet(name = "Shop", urlPatterns = {"/Shop"})
public class Shop extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {

            ServletContext servletContext = getServletContext();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
            EntityManager em = emf.createEntityManager();
            CategoryJpaController controller = new CategoryJpaController(emf);
            List<pojo.Category> categories = controller.findCategoryEntities();

            servletContext.setAttribute("categories", categories);

//--------------------------------------------------------------------------DisplayProduct-------------------------------------------------------------------------------------
            HttpSession httpSessionRef = request.getSession(false);
            String emailGOTTEN = (String) httpSessionRef.getAttribute("email");
          //  out.print("the email is: " + emailGOTTEN);
//------------------getting user n his INTERSTS ------------------------------------------------------------
            User userRef = new User();
            Query q = em.createNamedQuery("User.findByEmail").setParameter("email", emailGOTTEN);
            List<User> results = q.getResultList();
            if (results.size() != 0) {
                userRef = results.get(0);
                // out.println("the db name is :" + userRef.getUsername());
                //  String pwdDB = userRef.getPassword();
                Interest inerestRef = new Interest();
                List<Interest> intersts;
                intersts = userRef.getInterestList();
               // out.print(" <br> size:" + intersts.size());

                for (int i = 0; i < intersts.size(); i++) {
                 //   out.println("<br> intersts are : " + intersts.get(i).getInterestName());
                    //------------------ getting CaTEGORIES  by name-------------------------------------------------------------
                    Category categoryRef = new Category();

                    Query catQ = em.createNamedQuery("Category.findByCategorName").setParameter("categorName", intersts.get(i).getInterestName());
                    List<Category> catResults = catQ.getResultList();
                    if (results.size() != 0) {
                        categoryRef = catResults.get(0);
                      //  out.print("<br> cat name is :" + categoryRef.getCategorName());
                        List<Product> produLists = categoryRef.getProductList();
                        request.setAttribute("productsREQ", produLists);
                        for (int j = 0; j < produLists.size(); j++) {
                            out.print("<br> the products suitable for this user : <br>" + produLists.get(j).getProductName());
                        }
                    }
                }
            }
//--------------------------------------------------------------------------End of DisplayProduct---------------------------------------------------------------------------------

            request.getRequestDispatcher("index.jsp").forward(request, response);
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
