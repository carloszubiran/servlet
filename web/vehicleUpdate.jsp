<%--
  Created by IntelliJ IDEA.
  User: carlzubi1
  Date: 5/24/2016
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
    <h2>Update or Delete a Vehicle</h2>
    <div class="border float-left">
        <form name="updateVehicle" action="./vehicle" method="post">

            <%-- this wonk work fix this to be conditional --%>
            <input type="hidden" name="formVehicle" value="updateVehicle">

            <label>Plate</label>
            <label>VIN</label>
            <label>Year</label>
            <label>Color</label>
            <label>Make</label>
            <label>Model</label>

            <hr>

                    <c:forEach var="vehicle" items="${vehicleList}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <div>
                            <input type="text" name="plate" value="${vehicle.licensePlate}">
                            <input type="text" name="vin" value="${vehicle.VIN}">
                            <input type="text" name="year" value="${vehicle.year}">
                            <input type="text" name="color" value="${vehicle.color}">
                            ${vehicle.vehicleId}

                            <select name="selectVehicleMake" id="selectVehicleMake${count}" onchange="returnVehicleModel(${count})">
                                <option value="0">Select Make</option>
                                <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                                <c:choose>
                                    <c:when test="${vehicleMake.vehicleMakeId == vehicle.vehicleMake.vehicleMakeId}">
                                        <option selected value="${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</option>
                                    </c:when>
                                     <c:otherwise>
                                        <option value="${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</option>
                                     </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <select name="selectVehicleModel" id="selectVehicleModel${count}" >
                            <option value="0">Select Model</option>
                            <c:forEach var="vehicleModel" items="${vehicleModelList}">
                            <c:choose>
                                <c:when test="${vehicleModel.vehicleModelId == vehicle.vehicleModel.vehicleModelId}">
                                    <option selected value="${vehicleModel.vehicleModelId}">${vehicleModel.vehicleModelName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${vehicleModel.vehicleModelId}">${vehicleModel.vehicleModelName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </select>
                        <button name="update" value="${count}">Update</button>
                        <form name="deleteVehicle" action="./vehicle" method="post">
                            <input type="hidden" name="formVehicle" value="deleteVehicle">
                            <button name="delete" value="${count}">Delete</button>
                        </form>
                        <hr>
                        </div>
                    </c:forEach>
        </form>
    </div>

