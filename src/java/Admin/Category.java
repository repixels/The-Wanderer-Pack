/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;


import dao.CategoryJpaController;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        String insertOrEditOrView =request.getParameter("insertOrEditOrView");
        
        PrintWriter out = response.getWriter();
//        out.print(insertOrEditOrView);
        
        if(insertOrEditOrView.equals("insert")){
            createNewCategory(request, response);
        }
        else if(insertOrEditOrView.equals("edit")){
            editCategory(request,response);
            out.print(insertOrEditOrView);
        }
        else if(insertOrEditOrView.equals("view")){
            out.print(insertOrEditOrView);
        }
        
        
    }

    public void createNewCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name=request.getParameter("categorName");
        String desc=request.getParameter("categoryDescription");
       
        String products=request.getParameter("products");
        String subCat=request.getParameter("categoryList");
        
//        //TODO  add the saved folder of images to path of image
       
        
        Part part = request.getPart("categoryImage");
        String image=part.getSubmittedFileName();
        
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
        CategoryJpaController categoryController=new CategoryJpaController(emf);
        
        //create new category
        pojo.Category category =new pojo.Category();

        category.setCategorName(name);
        category.setCategoryDescription(desc);
        category.setCategoryImage(image);
        
        
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
            out.println("<h1> name :"+name+"</h1>");
            out.println("<h1> desc :"+desc+"</h1>");
            out.println("<h1> image :"+image+"</h1>");
            out.println("<h1> products :"+products+"</h1>");
            out.println("<h1> subCat :"+subCat+"</h1>");
            
            
            
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

    private void editCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      
        
        //  <editor-fold defaultstate="collapsed" desc="create new category ">
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("The_Wanderer_PackPU");
        EntityManager em = emf.createEntityManager();
        CategoryJpaController categoryController=new CategoryJpaController(emf);
        
        
        //create new category
        pojo.Category category =categoryController.findCategory(1);
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

}
