var mainUrl = "http://localhost:8000";

getAllRams();
setModalConfiguration();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllRams() {
    $.ajax({
        url: mainUrl + "/ram",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setRamsToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButton();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setRamsToTable(rams) {
    for (var i = 0; i < rams.length; i++) {
        setRamToTable(rams[i]);
    }
}

function setRamToTable(ram) {
    var tableOfRams = $("#rams");
    tableOfRams.append('<tr>' +
        '<td>' + ram.id + '</td>' +
        '<td>' + ram.name + '</td>' +
        '<td>' + ram.typeOfMemory + '</td>' +
        '<td>' + ram.volumeOfMemory + '</td>' +
        '<td>' + ram.workingFrequency + '</td>' +
        '<td><button class="button" value="' + ram.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + ram.id +'">Update</button></td>' +
        '</tr>');
}


function setActionOnCreateBtn() {
    $("#btnCreateRam").click(function () {

        var name = $("#name").val();
        var typeOfMemory = $("#typeOfMemory").val();
        var volumeOfMemory = $("#volumeOfMemory").val();
        var workingFrequency = $("#workingFrequency").val();
//            if (firstName != null && lastName != null && age != null) {

        var newRam = {
            "name": name,
            "typeOfMemory": typeOfMemory,
            "volumeOfMemory": volumeOfMemory,
            "workingFrequency": workingFrequency
        };

        $.ajax({
            url: mainUrl + "/ram",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newRam),
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
                url: mainUrl + "/ram/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#name").val(dataResponse.name);
                    $("#typeOfMemory").val(dataResponse.typeOfMemory);
                    $("#volumeOfMemory").val(dataResponse.volumeOfMemory);
                    $("#workingFrequency").val(dataResponse.workingFrequency);

                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateRam").click(function () {

                        var name = $("#name").val();
                        var typeOfMemory = $("#typeOfMemory").val();
                        var volumeOfMemory = $("#volumeOfMemory").val();
                        var workingFrequency = $("#workingFrequency").val();
//            if (firstName != null && lastName != null && age != null) {

                        var newRam = {
                            "name": name,
                            "typeOfMemory": typeOfMemory,
                            "volumeOfMemory": volumeOfMemory,
                            "workingFrequency": workingFrequency
                        };

                        $.ajax({
                            url: mainUrl + "/ram/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newRam),
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
                url: mainUrl + "/ram?id=" + $(this).val(),
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