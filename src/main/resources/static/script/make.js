var mainUrl = "http://localhost:8000";

getAllMakes();
setModalConfiguration();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllMakes() {
    $.ajax({
        url: mainUrl + "/make",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            console.log(dataResponse);
            setMakesToTable(dataResponse);
            //setMakesToTable(JSON.parse(dataResponse));
            //console.log(JSON.parse(dataResponse.data));
            //  var message = dataResponse.data;
            //  console.log(message.toString());
            setActionOnDeleteButtons();
            setActionOnUpdateButton();

            // setActionOnUpdateButtons();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setMakesToTable(makes) {
    for (var i = 0; i < makes.length; i++) {
        setPersonToTable(makes[i]);
    }
}

function setPersonToTable(make) {
    var tableOfMakes = $("#makes");
    tableOfMakes.append('<tr>' +
        '<td>' + make.id + '</td>' +
        '<td>' + make.name + '</td>' +
        '<td><button class="button" value="' + make.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + make.id +'">Update</button></td>' +
        '</tr>');
}


function setActionOnCreateBtn() {
    $("#btnCreateMake").click(function () {

        var name = $("#name").val();
//            if (firstName != null && lastName != null && age != null) {

        var newMake = {
            "name": name
        };

        $.ajax({
            url: mainUrl + "/make",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newMake),
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

//delete process
function setActionOnDeleteButtons() {
    $(".button").each(function (index) {
        $(this).click(function () {
            $.ajax({
                url: mainUrl + "/make/" + $(this).val(),
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

function setActionOnUpdateButton() {

    $(".buttonToUpdate").each(function (index) {
        var identifier;
        identifier = $(this).val();
        $(this).click(function () {
            $.ajax({
                url: mainUrl + "/make/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#name").val(dataResponse.name);
                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateMake").click(function () {

                        var name = $("#name").val();
//            if (firstName != null && lastName != null && age != null) {

                        var newMake = {
                            "name": name
                        };

                        $.ajax({
                            url: mainUrl + "/make/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newMake),
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