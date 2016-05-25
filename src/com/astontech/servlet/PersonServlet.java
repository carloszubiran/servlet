package com.astontech.servlet;

import com.astontech.bo.Person;
import com.astontech.dao.PersonDAO;
import com.astontech.dao.mysql.PersonDAOImpl;
import common.helpers.DateHelper;
import common.helpers.ServletHelper;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Carlos Zubiran on 5/24/2016.
 */
public class PersonServlet extends javax.servlet.http.HttpServlet {

    final static Logger logger = Logger.getLogger(PersonServlet.class);
    private static PersonDAO personDAO = new PersonDAOImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        switch (request.getParameter("formName")) {
            case "choosePerson":
                choosePerson(request);
                break;
            case "updatePerson":
                updatePerson(request);
                break;
            default:
                break;
        }

        //notes:    generate person down down using jstl (logic is the same between forms)
        request.setAttribute("personList", personDAO.getPersonList());
        request.getRequestDispatcher("./person.jsp").forward(request, response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setAttribute("personList", personDAO.getPersonList());
        request.setAttribute("selectPerson", generatePersonDropDownHtml(0));
        request.getRequestDispatcher("./person.jsp").forward(request, response);

    }

    private static void choosePerson(HttpServletRequest request) {

        logger.info("Form #1 - Form Name=" + request.getParameter("formName"));
        ServletHelper.logRequestParams(request);

        //notes:    everything comes back from the request as a string
        String selectedPersonId = request.getParameter("selectPerson");

        Person selectedPerson = personDAO.getPersonById(Integer.parseInt(selectedPersonId));

        logger.info("Selected Person Details: " + selectedPersonId.toString());

        personToRequest(request, selectedPerson);

        request.setAttribute("selectPerson", generatePersonDropDownHtml(selectedPerson.getPersonId()));

    }


    private static void updatePerson(HttpServletRequest request) {

        logger.info("Form #2 - Form Name=" + request.getParameter("formName"));
        ServletHelper.logRequestParams(request);

        Person updatePerson = new Person();
        requestToPerson(request, updatePerson);

        logger.info(updatePerson.toString());
        if (personDAO.updatePerson(updatePerson)) {
            request.setAttribute("updateSuccessful", "Person Updated in Database Successfully");
        } else {
            request.setAttribute("updateSuccessful", "Person Update Failed");
        }
        personToRequest(request, updatePerson);

        //notes:    inefficient! extra call to the database.
        updatePerson = personDAO.getPersonById(updatePerson.getPersonId());
        personToRequest(request, updatePerson);

        String personIdString = request.getParameter("personId");
        request.setAttribute("selectPerson", generatePersonDropDownHtml(Integer.parseInt(personIdString)));



    }

    private static String generatePersonDropDownHtml(int selectedPersonId) {

        //<editor-fold desc="html code">
        /*
        <select name="selectPerson">
            <select value='0' name='selectPerson'>(Select Person)</select>
            <option value='[1]'>[Dan]</option>
            <option value="2">James</option>
            <option value="3">Adrian</option>
            <option value="4">Sean</option>
        </select>
        * */
        //</editor-fold>

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<select name='selectPerson'>");
        stringBuilder.append("<option value='0'>(Select Person)</option>");

        for (Person person: personDAO.getPersonList()) {
            if (person.getPersonId() == selectedPersonId) {
                stringBuilder.append("<option selected value='").append(person.getPersonId()).append("'>")
                        .append(person.GetFullName()).append("</option>");
            } else {
                stringBuilder.append("<option value='").append(person.getPersonId()).append("'>")
                        .append(person.GetFullName()).append("</option>");
            }

        }

        stringBuilder.append("</select>");
        return  stringBuilder.toString();
    }

    private static void requestToPerson(HttpServletRequest request, Person person) {
        //notes:    everything comes back from the request as a string
        person.setPersonId(Integer.parseInt(request.getParameter("personId")));
        person.setFirstName(request.getParameter("firstName"));
        person.setMiddleName(request.getParameter("middleName"));
        person.setLastName(request.getParameter("lastName"));
        person.setBirthDate(DateHelper.stringToUtilDate(request.getParameter("birthDate"), "yyyy-MM-dd"));
        person.setSSN(request.getParameter("socialSecurityNumber"));
    }

    private static void personToRequest(HttpServletRequest request, Person person) {

        request.setAttribute("personId", person.getPersonId());
        request.setAttribute("firstName", person.getFirstName());
        request.setAttribute("middleName", person.getMiddleName());
        request.setAttribute("lastName", person.getLastName());
        request.setAttribute("birthDate", DateHelper.utilDateToSqlDate(person.getBirthDate()));
        request.setAttribute("socialSecurityNumber", person.getSSN());

    }

}
