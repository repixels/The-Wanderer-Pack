package servlet;



import dao.AddressJpaController;
import dao.InterestJpaController;
import dao.UserJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Address;
import pojo.Interest;
import pojo.User;

/**
 *
 * @author Salma Ashour
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

   

    public RegisterServlet() {
    }

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        String Name = request.getParameter("name");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String creditNumber = request.getParameter("creditNumber");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String state = request.getParameter("state");
        String addressDetails = request.getParameter("addressDetails");
        String landMark = request.getParameter("landMark");
        String[] interests = request.getParameterValues("interests");

        Integer credit = Integer.parseInt(creditNumber);


        Query query = em.createNamedQuery("User.findByEmail").setParameter("email", email);
        List result = query.getResultList();
        if(result.isEmpty())
        {
            try {
                User user = new User(); //don't set userId 
                Address userAdress = new Address();

                List<User> users = new ArrayList<User>();
                List<Interest> useInterests = new ArrayList<Interest>();

                //set user attributes
                user.setUsername(userName);
                user.setEmail(email);
                user.setPassword(password);
                user.setProfilePicture(new String());
                user.setCredit(credit);

                //set Address attributes
                userAdress.setAddressDetails(addressDetails);
                userAdress.setCountry(country);
                userAdress.setCity(city);
                userAdress.setLandmark(landMark);
                userAdress.setState(state);
                List<Address> userAddresses = new ArrayList<Address>();
                userAddresses.add(userAdress);

                // create address controller
                AddressJpaController addressController = new AddressJpaController(emf);
                addressController.create(userAdress);
                user.setAddressList(userAddresses);

                //InterestJpaController
                Interest interest = null;

                InterestJpaController interestController = new InterestJpaController(emf);
                for (String s : interests) {
                    interest = new Interest();
                    interest.setInterestName(s);
                    interestController.create(interest);

                    out.println(s);
                    useInterests.add(interest);
                }
                user.setInterestList(useInterests);
                users.add(user);
                interest.setUserList(users);
                userAdress.setUserList(users);

                // create user controller
                UserJpaController userController = new UserJpaController(emf);
                userController.create(user);

                //end of data base            
                out.println("<h1>user Created </h1>");
				response.sendRedirect("login.jsp");
                for (int i = 0; i < userAddresses.size(); i++) {
                    out.println(user.getAddressList().get(i).getCity());
                }

            } catch (Exception ex) {
                out.println("<h1>Error " + ex.getMessage() + "</h1>");
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace(out);
            }
        }
        else out.print("<h1>Can't Create ,Duplicate Email </h1>");
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
