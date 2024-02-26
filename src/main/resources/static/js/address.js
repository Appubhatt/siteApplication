$(function(){
    $('form').submit(function(event){
        var address = $("#address").val();
        var area =$("#area").val();
        var landmark = $("#landmark").val();
        var city = $("#city").val();
        var state = $("#state").val();
        var pincode = $("#pincode").val();
        var saveAs = $("#saveAs").val();

        submitAddress(address,area,landmark,city,state,pincode,saveAs,event);
    })
})

function submitAddress(address,area,landmark,city,state,pincode,saveAs,event){
    event.preventDefault();
    var jobj={
        "address":address,
        "area":area,
        "landmark":landmark,
        "city":city,
        "state":state,
        "pincode" : pincode,
        "saveAs" : saveAs,       
    }
    $.ajax({
        url:"http://localhost:8080/saveAddress",
        data:JSON.stringify(jobj),
        type:"post",
        contentType:"application/json",
        processData:false,
        success : function(response){
            alert(response);
        }
    })

}