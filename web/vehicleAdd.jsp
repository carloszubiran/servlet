<%--
  Created by IntelliJ IDEA.
  User: carlzubi1
  Date: 5/24/2016
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>




    <h2>Add Vehicle Form</h2>
    <div class="border add-vehicle float-left">
        <form name="addVehicle" action="./vehicle" method="post">
            <input type="hidden" name="formVehicle" value="addVehicle">

            <label for="selectVehicleMake0">
                Make
                <select class="updateOnLoad" name="selectVehicleMake" id="selectVehicleMake0" onchange="returnVehicleModel(0)">
                    <option value="0">Select Make</option>
                    <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                        <c:choose>
                        <c:when test="${vehicleMake.vehicleMakeId == vehicleMakeId}">
                            <option selected value="${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</option>
                        </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </label>
            <label for="selectVehicleModel0">
                Model
                <select name="selectVehicleModel" id="selectVehicleModel0">
                    <option value="0">Select Model</option>
                </select>
            </label>
            <label for="vin">
                VIN
                <input name="vin" value="" id="vin">
            </label>
            <label for="plate">
                License Plate
                <input name="plate" value="" id="plate">
            </label>
            <label for="year">
                Year
                <input name="year" value="" id="year">
            </label>
            <label for="color">
                Color
                <input name="color" value="" id="color">
            </label>

            <button type="submit">Add Vehicle</button>

        </form>
    </div>

<hr>

