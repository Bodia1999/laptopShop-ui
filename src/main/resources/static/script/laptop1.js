var mainUrl = "http://localhost:8000";

getAllLaptops();
setModalConfiguration();
setActionOnCreateBtn();


//start when load page PS reload page for triggered http request
function getAllLaptops() {
    $.ajax({
        url: mainUrl + "/laptop",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            console.log(dataResponse);
            setLaptopsToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButton();
        },
        error: function (error) {
            alert("Something went wrong");
        }
    });
}

function setLaptopsToTable(laptops) {
    for (var i = 0; i < laptops.length; i++) {
        setLaptopToTable(laptops[i]);
    }
}

function setLaptopToTable(laptop) {
    var tableOfLaptops = $("#laptops");
    tableOfLaptops.append('<tr>' +
        '<td>' + laptop.id + '</td>' +
        '<td>' + laptop.model + '</td>' +
        '<td>' + laptop.makeId + '</td>' +
        '<td>' + laptop.graphicCardId + '</td>' +
        '<td>' + laptop.ramId + '</td>' +
        '<td>' + laptop.corpsId + '</td>' +
        '<td>' + laptop.memoryId + '</td>' +
        '<td>' + laptop.processorId + '</td>' +
        '<td>' + laptop.screenId + '</td>' +
        '<td>' + laptop.availabilityOfWIFI + '</td>' +
        '<td>' + laptop.availabilityOfBluetooth + '</td>' +
        '<td>' + laptop.availabilityOfUSBTypeC + '</td>' +
        '<td>' + laptop.availabilityOfUSBSecondGeneration + '</td>' +
        '<td>' + laptop.availabilityOfUSBThirdGeneration + '</td>' +
        '<td>' + laptop.availabilityOfHDMI + '</td>' +
        '<td>' + laptop.availabilityOfLAN + '</td>' +
        '<td>' + laptop.availabilityOfAUX + '</td>' +
        '<td>' + '<span>/img/</span>'+laptop.imageDirection + '</td>' +
        '<td><button class="button" value="' + laptop.id + '">Delete</button></td>' +
        '<td><button class="buttonToUpdate" value="' + laptop.id +'">Update</button></td>' +
        '</tr>');
}

function setActionOnCreateBtn() {
    $("#btnCreateLaptop").click(function () {
        //console.log("I am here");


        var file = document.getElementById("imageDirection").files[0];
        var model = $("#model").val();

        var makeId = $("#makeId").val();
        var graphicCardId = $("#graphicCardId").val();
        var ramId = $("#ramId").val();
        var corpsId = $("#corpsId").val();
        var memoryId = $("#memoryId").val();
        var processorId = $("#processorId").val();
        var screenId = $("#screenId").val();
        var availabilityOfWIFI = $("input[name='availabilityOfWIFI']:checked").val();
        var availabilityOfBluetooth = $("input[name='availabilityOfBluetooth']:checked").val();
        var availabilityOfUSBTypeC = $("input[name='availabilityOfTypeC']:checked").val();

        var availabilityOfUSBSecondGeneration = $("input[name='availabilityOfUSBSecondGeneration']:checked").val();

        var availabilityOfUSBThirdGeneration = $("input[name='availabilityOfUSBThirdGeneration']:checked").val();
        var availabilityOfHDMI = $("input[name='availabilityOfHDMI']:checked").val();
        var availabilityOfLAN = $("input[name='availabilityOfLAN']:checked").val();
        var availabilityOfAUX = $("input[name='availabilityOfAUX']:checked").val();
//            if (firstName != null && lastName != null && age != null) {




        var newLaptop = {
            "model": model,
            "makeId":makeId,
            "graphicCardId":graphicCardId,
            "ramId":ramId,
            "corpsId":corpsId,
            "memoryId":memoryId,
            "processorId":processorId,
            "screenId":screenId,
            "availabilityOfWIFI":availabilityOfWIFI,
            "availabilityOfBluetooth":availabilityOfBluetooth,
            "availabilityOfUSBTypeC":availabilityOfUSBTypeC,
            "availabilityOfUSBSecondGeneration":availabilityOfUSBSecondGeneration,
            "availabilityOfUSBThirdGeneration":availabilityOfUSBThirdGeneration,
            "availabilityOfHDMI":availabilityOfHDMI,
            "availabilityOfLAN":availabilityOfLAN,
            "availabilityOfAUX":availabilityOfAUX,
           "imageDirection":gettingFile(file)

        };

        $.ajax({
            url: mainUrl + "/laptop",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newLaptop),
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
function gettingFile(file) {
    getBase64(file).then(data => {

        //work with data as src of file
        let request = {
            //fileName: "someCustomFileName",
            data: data
        };

        $.ajax({
            url: "http://localhost:8000/photo/upload",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(request),
            success: function (dataResponse) {
                return dataResponse;

            },
            error: function (error) {
                alert(error.message);
            }
        });
    });
}

//delete process
function setActionOnDeleteButtons() {
    $(".button").each(function (index) {
        $(this).click(function () {
            $.ajax({
                url: mainUrl + "/laptop?id=" + $(this).val(),
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
                url: mainUrl + "/laptop/findOne?id=" + $(this).val(),
                type: "POST",
                contentType: "application/json",
                success: function (dataResponse) {
                    // var parse = JSON.parse(dataResponse);
                    $("#name").val(dataResponse.name);
                    $("#model").val(dataResponse.model);
                    $("#makeId").val(dataResponse.makeId);
                    $("#graphicCardId").val(dataResponse.graphicCardId);
                    $("#ramId").val(dataResponse.ramId);
                    $("#corpsId").val(dataResponse.corpsId);
                    $("#memoryId").val(dataResponse.memoryId);
                    $("#processorId").val(dataResponse.processorId);
                    $("#screenId").val(dataResponse.screenId);
                    // $("#availabilityOfWIFI").val(dataResponse.availabilityOfWIFI);
                    // $("#availabilityOfBluetooth").val(dataResponse.availabilityOfBluetooth);
                    // $("#availabilityOfUSBTypeC").val(dataResponse.availabilityOfUSBTypeC);
                    // $("#availabilityOfUSBSecondGeneration").val(dataResponse.availabilityOfUSBSecondGeneration);
                    // $("#availabilityOfUSBThirdGeneration").val(dataResponse.availabilityOfUSBThirdGeneration);
                    // $("#availabilityOfHDMI").val(dataResponse.availabilityOfHDMI);
                    // $("#availabilityOfLAN").val(dataResponse.availabilityOfLAN);
                    // $("#availabilityOfAUX").val(dataResponse.availabilityOfAUX);
                    var elementById = document.getElementById("myModal");
                    elementById.style.display="block";
                    $("#btnUpdateLaptop").click(function () {

                        var model = $("#model").val();
                        var makeId = $("#makeId").val();
                        var graphicCardId = $("#graphicCardId").val();
                        var ramId = $("#ramId").val();
                        var corpsId = $("#corpsId").val();
                        var memoryId = $("#memoryId").val();
                        var processorId = $("#processorId").val();
                        var screenId = $("#screenId").val();
                        var availabilityOfWIFI = $("input[name='availabilityOfWIFI']:checked").val();

                        var availabilityOfBluetooth = $("input[name='availabilityOfBluetooth']:checked").val();

                        var availabilityOfUSBTypeC = $("input[name='availabilityOfTypeC']:checked").val();
                        var availabilityOfUSBSecondGeneration = $("input[name='availabilityOfUSBSecondGeneration']:checked").val();
                        var availabilityOfUSBThirdGeneration = $("input[name='availabilityOfUSBThirdGeneration']:checked").val();
                        var availabilityOfHDMI = $("input[name='availabilityOfHDMI']:checked").val();
                        var availabilityOfLAN = $("input[name='availabilityOfLAN']:checked").val();
                        var availabilityOfAUX = $("input[name='availabilityOfAUX']:checked").val();
                        var file = document.getElementsByClassName("getFile").files[0];
                        getBase64(file).then(data => {

                            //work with data as src of file
                            let request = {
                                //fileName: "someCustomFileName",
                                data: data
                            }
                        });
//            if (firstName != null && lastName != null && age != null) {

                        var newLaptop = {
                            "model": model,
                            "makeId":makeId,
                            "graphicCardId":graphicCardId,
                            "ramId":ramId,
                            "corpsId":corpsId,
                            "memoryId":memoryId,
                            "processorId":processorId,
                            "screenId":screenId,
                            "availabilityOfWIFI":availabilityOfWIFI,
                            "availabilityOfBluetooth":availabilityOfBluetooth,
                            "availabilityOfUSBTypeC":availabilityOfUSBTypeC,
                            "availabilityOfUSBSecondGeneration":availabilityOfUSBSecondGeneration,
                            "availabilityOfUSBThirdGeneration":availabilityOfUSBThirdGeneration,
                            "availabilityOfHDMI":availabilityOfHDMI,
                            "availabilityOfLAN":availabilityOfLAN,
                            "availabilityOfAUX":availabilityOfAUX,
                            "imageDirection":createImagePath(file)

                        };

                        $.ajax({
                            url: mainUrl + "/laptop/" + identifier,
                            type: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify(newLaptop),
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



// function createImagePath(fileName){
//     return '/img/' + fileName;
// }

function getBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
});
}

function setModalConfiguration() {
    // Get the modal
    var modal = document.getElementById('myModal');



    // Get the button that opens the modal
    var btn = document.getElementById('openModal');



    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal



    btn.onclick = function(){
        modal.style.display = "block";
    };


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