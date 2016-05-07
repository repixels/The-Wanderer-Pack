/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import dao.UserJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.User;
import java.util.List;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Deu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

            String FlagLogin = request.getParameter("flagLogin");

            String emailStrLogin = request.getParameter("emailLogin");
            String passStrLogin = request.getParameter("pwdLogin");

            UserJpaController controller = new UserJpaController(emf);
            // User user1 =controller.findUser(1);

            //     out.println("name: " + emailStrLogin);
            //     out.println("password: " + passStrLogin);
            if (FlagLogin.equals("0")) {
                HttpSession httpSessionRef = request.getSession(true);
                httpSessionRef.setAttribute("email", "");
                httpSessionRef.setAttribute("userName", "");
                response.sendRedirect("index.jsp");
            } else {
                User userRef = new User();
                Query q = em.createNamedQuery("User.findByEmail").setParameter("email", emailStrLogin);
                List<User> results = q.getResultList();
                if (results.size() != 0) {
                    userRef = results.get(0);
                    // out.println("the db name is :" + userRef.getUsername());
                    String pwdDB = userRef.getPassword();
                    String nameDB = userRef.getUsername();

                    if (pwdDB.equals(passStrLogin)) {
                        HttpSession httpSessionRef = request.getSession(true);
                        httpSessionRef.setAttribute("email", emailStrLogin);
                        httpSessionRef.setAttribute("userName", nameDB);
//                     response.sendRedirect("index.jsp");
//                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
//                    dispatcher.forward(request, response);
                        out.print("okay");
                    } else {
                        out.print("Sorry");
                        //     response.sendRedirect("index.jsp");
                        //    out.print("<html><body onload=\"alert('Hello World')\"></body></html>");
                    }

                } else {
                    out.println("no");

                    //response.sendRedirect("login.html");
                    //   out.print("Sorry, either your email or password is wrong");
                }

//            Query query = em.createQuery("SELECT email FROM user u");
//            List<User> results = query.getResultList();
//            userRef=results.get(0);
//            out.println("names are :  "+userRef.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();

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
