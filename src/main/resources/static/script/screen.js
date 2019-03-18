var mainUrl = "http://localhost:8000";

getAllScreens();
setModalConfiguration();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllScreens() {
    $.ajax({
        url: mainUrl + "/screen",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setScreensToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButton();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setScreensToTable(screens) {
    for (var i = 0; i < screens.length; i++) {
        setScreenToTable(screens[i]);
    }
}

function setScreenToTable(screen) {
    var tableOfScreens = $("#screens");
    tableOfScreens.append('<tr>' +
        '<td>' + screen.id + '</td>' +
        '<td>' + screen.type + '</td>' +
        '<td>' + screen.resolution + '</td>' +
        '<td>' + screen.size + '</td>' +
        '<td><button class="button" value="' + screen.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + screen.id +'">Update</button></td>' +
        '</tr>');
}


function setActionOnCreateBtn() {
    $("#btnCreateScreen").click(function () {

        var type = $("#type").val();
        var resolution = $("#resolution").val();
        var size = $("#size").val();
//            if (firstName != null && lastName != null && age != null) {

        var newScreen = {
            "type": type,
            "resolution": resolution,
            "size": size
        };

        $.ajax({
            url: mainUrl + "/screen",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newScreen),
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
                url: mainUrl + "/screen/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#type").val(dataResponse.type);
                    $("#resolution").val(dataResponse.resolution);
                    $("#size").val(dataResponse.size);

                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateScreen").click(function () {

                        var type = $("#type").val();
                        var resolution = $("#resolution").val();
                        var size = $("#size").val();
//            if (firstName != null && lastName != null && age != null) {

                        var newScreen = {
                            "type": type,
                            "resolution": resolution,
                            "size": size
                        };

                        $.ajax({
                            url: mainUrl + "/screen/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newScreen),
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
                url: mainUrl + "/screen?id=" + $(this).val(),
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