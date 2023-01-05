package gestioneUtenti;

import gestioneUtenti.service.GestioneUtenteService;
import gestioneUtenti.service.GestioneUtenteServiceImp;
import model.dao.address.SqlAddressDao;
import model.entity.Account;
import model.entity.Address;
import validate.ValidateForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddressServlet", value = "/AddressServlet/*")
public class AddressServlet extends HttpServlet {

    RequestDispatcher  dispatcher;
    SqlAddressDao sqlAddressDao;
    Address address;
    boolean modifica;
    Account account;
    private final GestioneUtenteService gestioneUtenteService = new GestioneUtenteServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {

            case "/":
                break;

            /**viene visualizzata la pagina per la modifica dell'indirizzo**/
            case "/showUpdateAddress":

                account= (Account) request.getSession(false).getAttribute("account");

                if(account.isAdmin()==true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminAddress.jsp");
                    dispatcher.forward(request, response);
                }
                else {

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentAddress.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path) {

            case "/":
                break;

            /**si effettua la modifica dell'indirizzo**/
            case "/updateAddress":

                String citta=request.getParameter("citta");
                boolean a= ValidateForm.validateResidenza(citta);
                String provincia=request.getParameter("provincia");
                boolean b=ValidateForm.validateProvincia(provincia);
                String via=request.getParameter("via");
                boolean c=ValidateForm.validateVia(via);
                int civico= Integer.parseInt(request.getParameter("civico"));
                boolean d=ValidateForm.validateCivico(civico);
                int cap= Integer.parseInt(request.getParameter("cap"));
                boolean e=ValidateForm.validateCap(String.valueOf(cap));

                if( a && b && c && d && e ) {
                    Account account = (Account) request.getSession(false).getAttribute("account");
                    address = new Address();
                    address.setAccount(account);
                    address.setCity(citta);
                    address.setPostalCode(cap);
                    address.setProvince(provincia);
                    address.setStreet(via);
                    address.setStreetNumber(civico);



                    gestioneUtenteService.ModificaDatiIndirizzo(address);

                    modifica = true;
                    request.setAttribute("modificaAddress", modifica);

                    account.setAddress(address);
                    request.getSession(false).setAttribute("account", account);


                    if(account.isAdmin()==true) {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);

                    }
                    else {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                }

                else {

                    if (account.isAdmin() == true) {
                        modifica = false;
                        request.setAttribute("error", modifica);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminAddress.jsp");
                        dispatcher.forward(request, response);

                    } else {

                        modifica = false;
                        request.setAttribute("error", modifica);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentAddress.jsp");
                        dispatcher.forward(request, response);
                    }
                }


                break;


            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }


    }
}
