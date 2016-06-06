package com.astontech.servlet;

import com.astontech.bo.VehicleModel;
import com.astontech.dao.VehicleMakeDAO;
import com.astontech.dao.VehicleModelDAO;
import com.astontech.dao.mysql.VehicleMakeDAOImpl;
import com.astontech.dao.mysql.VehicleModelDAOImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by Carlos Zubiran on 6/3/2016.
 */
public class VehicleModelToMakeGson extends HttpServlet{

    private static VehicleModelDAO vehicleModelDAO = new VehicleModelDAOImpl();


    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        sendGsonToJS(request,response);

    }

    protected void sendGsonToJS(HttpServletRequest request, HttpServletResponse response) {

        String aMakeId = request.getParameter("aMakeId");
        String json = new Gson().toJson(vehicleModelDAO.getVehicleModelByMakeIdList(Integer.parseInt(aMakeId)));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //notes: return a json object to the browser
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
