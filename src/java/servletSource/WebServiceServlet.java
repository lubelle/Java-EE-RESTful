/*
    Date: 11/22/2016
*/
package servletSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import wsClientSource.wsClient;
import wsSource.Products;

public class WebServiceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        wsClient client = new wsClient();
        String url = "/index.html";
        String action = request.getParameter("action");
        if (action == "") {
            action = "ViewAll";
        }
        switch (action) {
            case "ViewAll":
                GenericType<ArrayList<Products>> gType = new GenericType<ArrayList<Products>>() {
                };
                ArrayList<Products> allProducts = client.findAll_JSON(gType);
                if (allProducts != null) {
                    request.setAttribute("list", allProducts);
                } else {
                    request.setAttribute("result", "No record found!");
                }
                url = "/ViewAll.jsp";
                break;
            case "Update":
                Integer updatedQuantity = 0;
                Double updatedPrice = 0.0;
                String updatedName = "";
                String pid = request.getParameter("pid").trim();
                String pname = request.getParameter("pname").trim();
                String pqty = request.getParameter("pqty").trim();
                String pprice = request.getParameter("pprice").trim();

                if (pname.isEmpty()) {
                    updatedName = "";
                } else {
                    updatedName = pname;
                }
                if (!pqty.isEmpty()) {
                    try {
                        updatedQuantity = Integer.parseInt(pqty);
                    } catch (NumberFormatException e) {
                        request.setAttribute("result", "Input error!");
                    }
                }
                if (!pprice.isEmpty()) {
                    try {
                        updatedPrice = Double.parseDouble(pprice);
                    } catch (NumberFormatException e) {
                        request.setAttribute("result", "Input error!");
                    }
                }

                if (pid.equals("")) {
                    request.setAttribute("result", "Input error!");
                } else {
                    Products jsonUpdate = client.find_JSON(Products.class, pid);
                    if (jsonUpdate == null) {
                        request.setAttribute("result", "No record found. Update failed!");
                    } else {
                        jsonUpdate.setPname(updatedName);
                        jsonUpdate.setPprice(updatedPrice);
                        jsonUpdate.setPqty(updatedQuantity);

                        client.edit_JSON(jsonUpdate, pid);
                        request.setAttribute("result", "Update successful!");
                    }
                }
                url = "/Update.jsp";
                break;
            case "Add":
                Integer newQuantity = 0;
                Double newPrice = 0.0;
                String newName = "";
                String newPid = request.getParameter("pid").trim();
                String newPname = request.getParameter("pname").trim();
                String newPqty = request.getParameter("pqty").trim();
                String newPprice = request.getParameter("pprice").trim();
                if (newPname.isEmpty()) {
                    newName = "";
                } else {
                    newName = newPname;
                }
                if (newPqty.isEmpty()) {
                    newQuantity = 0;
                } else {
                    try {
                        newQuantity = Integer.parseInt(newPqty);
                    } catch (NumberFormatException e) {
                        request.setAttribute("result", "Input error!");
                    }
                }
                if (newPprice.isEmpty()) {
                    newPrice = 0.0;
                } else {
                    try {
                        newPrice = Double.parseDouble(newPprice);
                    } catch (NumberFormatException e) {
                        request.setAttribute("result", "Input error!");
                    }
                }
                if (newPid.isEmpty()) {
                    request.setAttribute("result", "Input error!");
                } else {
                    Products jsonObject = client.find_JSON(Products.class, newPid);
                    if (jsonObject != null) {
                        request.setAttribute("result", "Record already exist! Create failed!");
                    } else {
                        Products newProduct = new Products();
                        newProduct.setPid(newPid);
                        newProduct.setPname(newName);
                        newProduct.setPqty(newQuantity);
                        newProduct.setPprice(newPrice);

                        client.create_JSON(newProduct);
                        request.setAttribute("result", "Create successful!");
                    }
                }
                url = "/Create.jsp";
                break;
            case "Delete":
                String discontinued_pid = request.getParameter("pid").trim();
                if (discontinued_pid.isEmpty()) {
                    request.setAttribute("result", "Input error!");
                } else {
                    Products discontinuedProduct = client.find_JSON(Products.class, discontinued_pid);
                    if (discontinuedProduct != null) {
                        client.remove(discontinued_pid);
                        request.setAttribute("result", "Delete successful!");
                    } else {
                        request.setAttribute("result", "No record found. Delete failed!");
                    }
                }
                url = "/Delete.jsp";
                break;
        }
        client.close();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
