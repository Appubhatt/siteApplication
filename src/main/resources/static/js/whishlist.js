$(document).ready(function(){
    if(!localStorage.userId){
        window.location ="http://localhost:8080/Html/test.html"
    }
    else{
    $.ajax({
        url:"http://localhost:8080/getWhishlist",
        data:{userId:localStorage.userId},
        type:"get",
        success: function(response){
            htmlbuilder='';
            console.log(response);
            var json = response;
            if(json.length==0){
                htmlbuilder='Whishlist is Empty';
                $("#whishlist-container").html("<h2>"+htmlbuilder+"</h2>");
            }
            else{

                for (let index = 0; index < json.length; index++) {
                   var jobj = json[index];
                   htmlbuilder+='<div class="card" >';
                   htmlbuilder+='<div class="row " >'
                   htmlbuilder+='<div class="col-md-7 px-3">';
                   htmlbuilder+='<div class="card-block px-6">';
                   htmlbuilder+='<h4 class="card-title">'+jobj['productName']+' </h4>';
                   htmlbuilder+='<p class="card-text">'+jobj['shortDesc']+' </p>';
                   htmlbuilder+='<p class="card-text">';
                   htmlbuilder+='<h3> <b>&#x20b9; '+jobj['price']+' </b></h3>';
                   htmlbuilder+='</p><br>';
                   htmlbuilder+='<button type="button" class="btn btn-primary" onclick="sendIdFunction('+jobj['productId']+')">Read More</button>';
                   htmlbuilder+=' <div class="button-container">';
                   htmlbuilder+=' <button class="btn btn-md" onclick="removeFromList('+jobj['productId']+','+localStorage.userId+')">';
                   htmlbuilder+='<img src="/photos/delete.jpg" width="25" />';
                   htmlbuilder+='</button>';
                   htmlbuilder+=" </div> </div> </div>";
                   htmlbuilder+='<div class="col-md-5">';
                   htmlbuilder+='<div id="CarouselTest" class="carousel slide" data-ride="carousel">';
                   htmlbuilder+='<div class="carousel-inner">';
                   htmlbuilder+=' <div class="carousel-item active">';
                   htmlbuilder+=' <img class="d-block" src="/photos/user/'+jobj['fileCode']+'" alt="" height="290" width="330">';
                   htmlbuilder+='</div></div></div></div></div></div><br>';                 
                }
                $("#whishlist-container").html(htmlbuilder);
                
                
            }
        }
    })}
})

function sendIdFunction(id){
    window.location="http://localhost:8080/Html/product-detail.html?id="+id;
  }

function removeFromList(productId,userId){
    var jobj={
        "userId":userId,
        "productId":productId
    }
    $.ajax({
        url :"http://localhost:8080/removeFromList",
        data : JSON.stringify(jobj),
        contentType:"application/json",
        type:"post",
        processData:false,
        success: function(response){
            //alert(response);
            location.reload();
        }
    })
}  