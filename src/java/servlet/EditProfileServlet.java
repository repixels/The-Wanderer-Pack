package servlet;

import com.google.gson.Gson;
import dao.AddressJpaController;
import dao.InterestJpaController;
import dao.UserJpaController;
import dao.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.Address;
import pojo.Interest;
import pojo.User;

/**
 *
 * @author Salma Ashour
 */
@WebServlet(name = "EditProfileServlet", urlPatterns = {"/EditProfileServlet"})
public class EditProfileServlet extends HttpServlet {
    
    static Vector<String> myVector = new Vector<String>();
    
    static String[] interests ;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
      

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        PrintWriter out = response.getWriter();

        
           
        UserJpaController userController = new UserJpaController(emf);
        AddressJpaController addressController = new AddressJpaController(emf);
        InterestJpaController interestController = new InterestJpaController(emf);

        String uemail = request.getParameter("email");
      
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String creditNumber = request.getParameter("creditNumber");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String state = request.getParameter("state");
        String addressDetails = request.getParameter("addressDetails");
        String landMark = request.getParameter("landMark");
        interests = request.getParameterValues("interests");
        Integer credit = Integer.parseInt(creditNumber);
        List<Interest> useInterests = new ArrayList<Interest>();
        List<Address> userAddressess = new ArrayList<Address>();
        Address userAdress = new Address();
        Interest interest = null;

        Integer uId = null;

        Query q = em.createNamedQuery("User.findByEmail").setParameter("email", uemail);
        List<User> results = q.getResultList();

        if (results.size() != 0) {
            User user2 = new User();
            user2 = results.get(0);
            Integer uid = user2.getUserId();

            user2.setUsername(userName);
            user2.setEmail(uemail);
            user2.setPassword(password);
            user2.setProfilePicture(new String());
            user2.setCredit(credit);

            q = em.createNamedQuery("Address.findByAddressId");
            q.setParameter("addressId", user2.getAddressList().get(0).getAddressId());
            userAdress = (Address) q.getSingleResult();

            if (interests.length == user2.getInterestList().size())
            for (int i = 0; i < user2.getInterestList().size(); i++) {
                Query q2 = em.createNamedQuery("Interest.findByInterestId");
                q2.setParameter("interestId", user2.getInterestList().get(i).getInterestId());
                interest = new Interest();
                interest = (Interest) q2.getSingleResult();

                interest.setInterestName(interests[i]);
                try {
                    interestController.edit(interest);
                    out.print(interest.getInterestId());
                } catch (Exception ex) {
                    Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace(out);
                }
            }
            if (interests.length > user2.getInterestList().size() || interests.length < user2.getInterestList().size()) {
                int x = interests.length - user2.getInterestList().size();
                for (int j = 0; j < x; j++) {
                    interest = new Interest();
                    interest.setInterestName(interests[user2.getInterestList().size() + j]);
                    interestController.create(interest);
                    useInterests.add(interest);
                }
                user2.setInterestList(useInterests);
            }

            userAdress.setAddressDetails(addressDetails);
            userAdress.setCountry(country);
            userAdress.setCity(city);
            userAdress.setLandmark(landMark);
            userAdress.setState(state);
            userAddressess.add(userAdress);
            user2.setAddressList(userAddressess);

            try {
                userController.edit(user2);
                addressController.edit(userAdress);
                out.print("Updated");
                out.println(userAdress.getAddressId());
            } catch (Exception ex) {
                Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace(out);
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
        PrintWriter out = response.getWriter();
        out = response.getWriter();
        out.print(buildGsonObjectfromArray(interests));

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

    
    private String buildGsonObjectfromArray(String[] s) {

        Gson gson = new Gson();
        return gson.toJson(s);
    }
}
