<%--
  Created by IntelliJ IDEA.
  User: carlzubi1
  Date: 6/6/2016
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>

<%-- add a javascript function to properly display the values for the make
  depending on the model --%>
<script>
    $( document ).ready(function () {
        $('.updateOnLoad').change(function () {
            console.log($(this).val());
//            var modelbox =
            var makeId = $(this).val();
            $.getJSON("http://localhost:8080/vehicleModelToMakeGson" + "?aMakeId=" + makeId , function( data ) {
                //notes:    log objects to the browser console
//            console.log(data);

                //notes:    empty all the elements of the select dropdown if there are any
                $(this).empty();

                //notes:    create an empty array to build a string later
                var items = [];
                //notes:
                $.each( data, function( key, val ) {
                    items.push( "<option id='" + val.VehicleModelName +
                            "' value='" + val.VehicleMakeId + "'>" + val.VehicleModelName + "</option>" );
//                console.log(val.VehicleMakeId.toString());
//                console.log(val.VehicleModelName.toString());
                });

                $(this).append(items.join());

            });

        });

    });
    function returnVehicleModel(modelNumber) {
        var selectTag = document.getElementById("selectVehicleMake" + modelNumber);
//        var selectTag = this;
        //notes:    get the element that is selected, its text and its value
//        console.log("selectedText: " + selectTag.options[selectTag.selectedIndex].text);
//        console.log("selectedValue: " + selectTag.options[selectTag.selectedIndex].value);



        //notes:    make a connection request using GET method with
        //a given URL with Parameter called aMakeId with a value
        //the there is also an anonymous function the a parameter of data IE. the JSON objects
        $.getJSON("http://localhost:8080/vehicleModelToMakeGson" + "?aMakeId=" + selectTag.options[selectTag.selectedIndex].value, function( data ) {
            //notes:    log objects to the browser console
//            console.log(data);

            //notes:    empty all the elements of the select dropdown if there are any
            $("#selectVehicleModel" + modelNumber).empty();

            //notes:    create an empty array to build a string later
            var items = [];
            //notes:
            $.each( data, function( key, val ) {
                items.push( "<option id='" + val.VehicleModelName +
                        "' value='" + val.VehicleMakeId + "'>" + val.VehicleModelName + "</option>" );
//                console.log(val.VehicleMakeId.toString());
//                console.log(val.VehicleModelName.toString());
            });

            $("#selectVehicleModel" + modelNumber).append(items.join());

        });
    }

</script>