var mainUrl = "http://localhost:8000";

getAllProcessors();
setModalConfiguration();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllProcessors() {
    $.ajax({
        url: mainUrl + "/processor",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setProcessorsToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButton();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setProcessorsToTable(processors) {
    for (var i = 0; i < processors.length; i++) {
        setProcessorToTable(processors[i]);
    }
}

function setProcessorToTable(processor) {
    var tableOfProcessors = $("#processors");
    tableOfProcessors.append('<tr>' +
        '<td>' + processor.id + '</td>' +
        '<td>' + processor.name + '</td>' +
        '<td>' + processor.model + '</td>' +
        '<td>' + processor.workingFrequency + '</td>' +
        '<td>' + processor.quantityOfCores + '</td>' +
        '<td><button class="button" value="' + processor.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + processor.id +'">Update</button></td>' +
        '</tr>');
}


function setActionOnCreateBtn() {
    $("#btnCreateProcessor").click(function () {

        var name = $("#name").val();
        var model = $("#model").val();
        var workingFrequency = $("#workingFrequency").val();
        var quantityOfCores = $("#quantityOfCores").val();
//            if (firstName != null && lastName != null && age != null) {

        var newProcessor = {
            "name": name,
            "model": model,
            "workingFrequency": workingFrequency,
            "quantityOfCores": quantityOfCores
        };

        $.ajax({
            url: mainUrl + "/processor",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newProcessor),
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
                url: mainUrl + "/processor/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#name").val(dataResponse.name);
                    $("#model").val(dataResponse.model);
                    $("#workingFrequency").val(dataResponse.workingFrequency);
                    $("#quantityOfCores").val(dataResponse.quantityOfCores);

                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateProcessor").click(function () {

                        var name = $("#name").val();
                        var model = $("#model").val();
                        var workingFrequency = $("#workingFrequency").val();
                        var quantityOfCores = $("#quantityOfCores").val();
//            if (firstName != null && lastName != null && age != null) {

                        var newProcessor = {
                            "name": name,
                            "model": model,
                            "workingFrequency": workingFrequency,
                            "quantityOfCores": quantityOfCores
                        };

                        $.ajax({
                            url: mainUrl + "/processor/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newProcessor),
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
                url: mainUrl + "/processor?id=" + $(this).val(),
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