var mainUrl = "http://localhost:8000";

setModalConfiguration();
getAllGraphicCard();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllGraphicCard() {
    $.ajax({
        url: mainUrl + "/graphiccard",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setGraphicCardsToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButton();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setGraphicCardsToTable(graphicCard) {
    for (var i = 0; i < graphicCard.length; i++) {
        setGraphicCardToTable(graphicCard[i]);
    }
}

function setGraphicCardToTable(graphicCard) {
    var tableOfGraphicCards = $("#graphicCards");
    tableOfGraphicCards.append('<tr>' +
        '<td>' + graphicCard.id + '</td>' +
        '<td>' + graphicCard.name + '</td>' +
        '<td>' + graphicCard.model + '</td>' +
        '<td>' + graphicCard.typeOfGraphicCard + '</td>' +
        '<td>' + graphicCard.volumeOfMemory + '</td>' +
        '<td>' + graphicCard.typeOfMemory + '</td>' +
        '<td><button class="button" value="' + graphicCard.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + graphicCard.id +'">Update</button></td>' +
        '</tr>');
}


function setActionOnCreateBtn() {
    $("#btnCreateGraphicCard").click(function () {

        var name = $("#name").val();
        var model = $("#model").val();
        var typeOfGraphicCard = $("#typeOfGraphicCard").val();
        var volumeOfMemory = $("#volumeOfMemory").val();
        var typeOfMemory = $("#typeOfMemory").val();
//            if (firstName != null && lastName != null && age != null) {

        var newGraphicCard = {
            "name": name,
            "model":model,
            "typeOfGraphicCard":typeOfGraphicCard,
            "volumeOfMemory":volumeOfMemory,
            "typeOfMemory":typeOfMemory
        };

        $.ajax({
            url: mainUrl + "/graphiccard",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newGraphicCard),
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

//delete process
function setActionOnDeleteButtons() {
    $(".button").each(function (index) {
        $(this).click(function () {
            $.ajax({
                url: mainUrl + "/graphiccard?id=" + $(this).val(),
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
                url: mainUrl + "/graphiccard/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#name").val(dataResponse.name);
                    $("#model").val(dataResponse.model);
                    $("#typeOfGraphicCard").val(dataResponse.typeOfGraphicCard);
                    $("#volumeOfMemory").val(dataResponse.volumeOfMemory);
                    $("#typeOfMemory").val(dataResponse.typeOfMemory);
                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateGraphicCard").click(function () {

                        var name = $("#name").val();
                        var model = $("#model").val();
                        var typeOfGraphicCard = $("#typeOfGraphicCard").val();
                        var volumeOfMemory = $("#volumeOfMemory").val();
                        var typeOfMemory = $("#typeOfMemory").val();
//            if (firstName != null && lastName != null && age != null) {

                        var newGraphicCard = {
                            "name": name,
                            "model":model,
                            "typeOfGraphicCard":typeOfGraphicCard,
                            "volumeOfMemory":volumeOfMemory,
                            "typeOfMemory":typeOfMemory
                        };

                        $.ajax({
                            url: mainUrl + "/graphiccard/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newGraphicCard),
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