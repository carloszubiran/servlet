package com.astontech.servlet;

import com.astontech.bo.Vehicle;
import com.astontech.dao.VehicleDAO;
import com.astontech.dao.VehicleMakeDAO;
import com.astontech.dao.VehicleModelDAO;
import com.astontech.dao.mysql.VehicleDAOImpl;
import com.astontech.dao.mysql.VehicleMakeDAOImpl;
import com.astontech.dao.mysql.VehicleModelDAOImpl;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by Carlos Zubiran on 5/24/2016.
 */
public class VehicleServlet extends javax.servlet.http.HttpServlet {

    final static Logger logger = Logger.getLogger(VehicleServlet.class);

    private static Vehicle vehicle = new Vehicle();
    private static VehicleDAO vehicleDAO = new VehicleDAOImpl();
    private static VehicleMakeDAO vehicleMakeDAO = new VehicleMakeDAOImpl();
    private static VehicleModelDAO vehicleModelDAO = new VehicleModelDAOImpl();

    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        switch (request.getParameter("formVehicle")) {
            case "updateVehicle":
                updateVehicle(request);
                break;
            case "deleteVehicle":
                deleteVehicle(request);
                break;
            case "addVehicle":
                addVehicle(request);
                break;
            default:
                break;
        }

        //notes:    generate person down down using jstl (logic is the same between forms)
        request.setAttribute("vehicleList", vehicleDAO.getVehicleList());
        request.setAttribute("vehicleMakeList", vehicleMakeDAO.getVehicleMakeList());

        //notes:    send the vehicle page back to the user.
        request.getRequestDispatcher("./vehicle.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //  get list of items and send it to the view

        request.setAttribute("vehicleList", vehicleDAO.getVehicleList());
        request.setAttribute("vehicleMakeList", vehicleMakeDAO.getVehicleMakeList());
        request.getRequestDispatcher("./vehicle.jsp").forward(request,response);

    }

    protected void addVehicle(HttpServletRequest request) throws ServletException, IOException {

        //  get all the values from the parameters of the request and put them into vehicle
        vehicle.getVehicleMake().setVehicleMakeName(request.getParameter("selectVehicleMake").toString());
        vehicle.getVehicleModel().setVehicleModelName(request.getParameter("selectVehicleModel").toString());
        vehicle.setLicensePlate(request.getParameter("plate").toString());
        vehicle.setVIN(request.getParameter("vin").toString());
        vehicle.setYear(Integer.parseInt(request.getParameter("year").toString()));
        vehicle.setColor(request.getParameter("color").toString());

        //insert the vehicle into the database
        vehicleDAO.insertVehicle(vehicle);

    }


    protected void deleteVehicle(HttpServletRequest request) throws ServletException, IOException {

        System.out.println(request.getParameter("delete"));
        vehicleDAO.deleteVehicle(Integer.parseInt(request.getParameter("delete")));

    }


    private static void updateVehicle(HttpServletRequest request) {


        vehicle.setVehicleId(Integer.parseInt(request.getParameter("update")));
        vehicle.getVehicleModel().setVehicleModelId(Integer.parseInt(request.getParameter("selectVehicleModel")));
        vehicle.setLicensePlate(request.getParameter("plate"));
        vehicle.setVIN(request.getParameter("vin"));
        vehicle.setYear(Integer.parseInt(request.getParameter("year")));
        vehicle.setColor(request.getParameter("color"));

        System.out.println(vehicle);

        vehicleDAO.updateVehicle(vehicle);

    }

}
