var mainUrl = "http://localhost:8000";

getAllCorps();
setModalConfiguration();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllCorps() {
    $.ajax({
        url: mainUrl + "/corps",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setCorpsToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButton();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setCorpsToTable(corps) {
    for (var i = 0; i < corps.length; i++) {
        setCorpToTable(corps[i]);
    }
}

function setCorpToTable(corp) {
    var tableOfCorps = $("#corps");

    tableOfCorps.append('<tr>' +
        '<td>' + corp.id + '</td>' +
        '<td>' + corp.colorOfCorps+ '</td>' +
        '<td>' + corp.materialOfCorps+ '</td>' +
        '<td>' + corp.dimensions+ '</td>' +
        '<td>' + corp.weight+ '</td>' +
        '<td><button class="button" value="' + corp.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + corp.id +'">Update</button></td>' +
        '</tr>');}


function setActionOnCreateBtn() {
    $("#btnCreateCorps").click(function () {

        var colorOfCorps = $("#colorOfCorps").val();
        var materialOfCorps = $("#materialOfCorps").val();
        var dimensions = $("#dimensions").val();
        var weight = $("#weight").val();
//            if (firstName != null && lastName != null && age != null) {

        var newCorps = {
            "weight":weight,
            "dimensions":dimensions,
            "colorOfCorps": colorOfCorps,
            "materialOfCorps":materialOfCorps
        };

        $.ajax({
            url: mainUrl + "/corps",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newCorps),
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
                url: mainUrl + "/corps/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#colorOfCorps").val(dataResponse.colorOfCorps);
                    $("#materialOfCorps").val(dataResponse.materialOfCorps);
                    $("#dimensions").val(dataResponse.dimensions);
                    $("#weight").val(dataResponse.weight);
                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateCorps").click(function () {

                        var colorOfCorps = $("#colorOfCorps").val();
                        var materialOfCorps = $("#materialOfCorps").val();
                        var dimensions = $("#dimensions").val();
                        var weight = $("#weight").val();
//            if (firstName != null && lastName != null && age != null) {

                        var newCorps = {
                            "colorOfCorps": colorOfCorps,
                            "materialOfCorps":materialOfCorps,
                            "dimensions":dimensions,
                            "weight":weight
                        };

                        $.ajax({
                            url: mainUrl + "/corps/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newCorps),
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
                url: mainUrl + "/corps?id=" + $(this).val(),
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