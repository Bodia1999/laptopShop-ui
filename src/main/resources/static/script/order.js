var mainUrl = "http://localhost:8000";

getAllOrders();
setModalConfiguration();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllOrders() {
    $.ajax({
        url: mainUrl + "/order",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setOrdersToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButton();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setOrdersToTable(orders) {
    for (var i = 0; i < orders.length; i++) {
        setOrderToTable(orders[i]);
    }
}

function setOrderToTable(order) {
    var tableOfOrders = $("#orders");
    tableOfOrders.append('<tr>' +
        '<td>' + order.id + '</td>' +
        '<td>' + order.customerId + '</td>' +
        '<td><button class="button" value="' + order.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + order.id +'">Update</button></td>' +
        '</tr>');
}


function setActionOnCreateBtn() {
    $("#btnCreateOrder").click(function () {

        var customerId = $("#customerId").val();
//            if (firstName != null && lastName != null && age != null) {

        var newOrder = {
            "customerId": customerId
        };

        $.ajax({
            url: mainUrl + "/order",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newOrder),
            success: function (data) {
                location.reload();
            },
            error: function (error) {
                alert(error.message);
            }
        });
//            } else {
//                alert("Всі поля повинні бути заповнені")
//            }
    });
}

function setActionOnUpdateButton() {

    $(".buttonToUpdate").each(function (index) {
        var identifier;
        identifier = $(this).val();
        $(this).click(function () {
            $.ajax({
                url: mainUrl + "/order/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#customerId").val(dataResponse.customerId);

                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateOrder").click(function () {

                        var customerId = $("#customerId").val();
//            if (firstName != null && lastName != null && age != null) {

                        var newOrder = {
                            "customerId": customerId
                        };

                        $.ajax({
                            url: mainUrl + "/order/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newOrder),
                            success: function (data) {
                                location.reload();
                            },
                            error: function (error) {
                                alert("Something went wrong");
                            }
                        });
//            } else {
//                alert("Всі поля повинні бути заповнені")
//            }
                    });
                }
            });
        })
    });

}

//delete process
function setActionOnDeleteButtons() {
    $(".button").each(function (index) {
        $(this).click(function () {
            $.ajax({
                url: mainUrl + "/order?id=" + $(this).val(),
                type: "DELETE",
                success: function (data) {
                    location.reload();
                },
                error: function (error) {
                    alert(error.message);
                }
            });

        })
    })

}









function setModalConfiguration() {
    // Get the modal
    var modal = document.getElementById('myModal');



    // Get the button that opens the modal
    var btn = document.getElementById("openModal");



    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal



    btn.onclick = function(){
        modal.style.display = "block";
    }


    span.onclick = function () {
        modal.style.display = "none";
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";

        }
    };
}