var mainUrl = "http://localhost:8000";

getAllMemories();
setModalConfiguration();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllMemories() {
    $.ajax({
        url: mainUrl + "/memory",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setMemoriesToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButton();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setMemoriesToTable(memories) {
    for (var i = 0; i < memories.length; i++) {
        setMemoryToTable(memories[i]);
    }
}

function setMemoryToTable(memory) {
    var tableOfMemories = $("#memories");
    tableOfMemories.append('<tr>' +
        '<td>' + memory.id + '</td>' +
        '<td>' + memory.name + '</td>' +
        '<td>' + memory.typeOfMemory + '</td>' +
        '<td>' + memory.volumeOfMemory + '</td>' +
        '<td><button class="button" value="' + memory.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + memory.id +'">Update</button></td>' +
        '</tr>');
}


function setActionOnCreateBtn() {
    $("#btnCreateMemory").click(function () {

        var name = $("#name").val();
        var typeOfMemory = $("#typeOfMemory").val();
        var volumeOfMemory = $("#volumeOfMemory").val();
//            if (firstName != null && lastName != null && age != null) {

        var newMemory = {
            "name": name,
            "typeOfMemory": typeOfMemory,
            "volumeOfMemory": volumeOfMemory
        };

        $.ajax({
            url: mainUrl + "/memory",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newMemory),
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
                url: mainUrl + "/memory/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#name").val(dataResponse.name);
                    $("#typeOfMemory").val(dataResponse.typeOfMemory);
                    $("#volumeOfMemory").val(dataResponse.volumeOfMemory);
                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateMemory").click(function () {

                        var name = $("#name").val();
                        var typeOfMemory = $("#typeOfMemory").val();
                        var volumeOfMemory = $("#volumeOfMemory").val();
//            if (firstName != null && lastName != null && age != null) {

                        var newMemory = {
                            "name": name,
                            "typeOfMemory":typeOfMemory,
                            "volumeOfMemory":volumeOfMemory
                        };

                        $.ajax({
                            url: mainUrl + "/memory/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newMemory),
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
                url: mainUrl + "/memory?id=" + $(this).val(),
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