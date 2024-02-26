$(function(){
    $("#add-productForm").submit(function(event){
        event.preventDefault();
        var proName = $("#product-name").val();
        console.log(proName);
        var proCategory = $("#select-product").val();
        var smallDesc = $("#small-description").val();
        var mainDesc = $("#main-description").val();
        var proPrice = $("#product-price").val();
        var stock = $("#stock").val();
        var fileData = $('#formFile')[0].files[0];
       
        submitData(proName,proCategory,smallDesc,mainDesc,proPrice,stock,fileData,event);
    })
})

function submitData(proName,proCategory,smallDesc,mainDesc,proPrice,stock,fileData,event){
 
    var jsonObj={
        "productName" : proName,
        "productCategory" : proCategory,
        "shortDesc" : smallDesc,
        "longDesc" : mainDesc,
        "stock" : stock,
        "price" : proPrice
    }
    var data = JSON.stringify(jsonObj);
    var formData = new FormData();
    formData.append('files',fileData);
    formData.append('data',data);
    $.ajax({
        data :formData,
        url : "http://localhost:8080/save-product",
        type : "post",
        processData: false,
        contentType :false,
        success : function(resp){
            console.log(resp);
            alert("Product been added");
           // window.location = "http://127.0.0.1:5500/Html/add-product.html";
        }
    })
}