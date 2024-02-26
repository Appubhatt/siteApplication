$(function(){
    $(document).ready(function(){
      
        $.ajax({
            url:"http://localhost:8080/fetch-products",
            type:"get",
            data:{data:"processor"},
            success: function(response){
                var htmlbuilder='';
                for (let i = 0; i < response.length; i++) {
                    const jobj = response[i];
                    htmlbuilder+= '<div class="card" style="width: 18rem;">'
                    htmlbuilder+= '<img class="card-img-top" src="/photos/processor/'+jobj["fileCode"]+'" alt="Card image cap">'
                    htmlbuilder+= '<div class="card-body">'
                    htmlbuilder+= '<h5 class="card-title">'+jobj["productName"]+'</h5>'
                    htmlbuilder+= '<p class="card-text">'+jobj["shortDesc"]+'</p>'
                    htmlbuilder+= ' <button type="button" class="btn btn-primary" onclick="sendIdFunction('+jobj['productId']+')">More Info</button>'
                    htmlbuilder+= '</div></div>'                       
                }
                $("#product-container").html(htmlbuilder);

            }
        })
    })
})
function sendIdFunction(id){
    window.location="http://localhost:8080/Html/product-detail.html?id="+id;
}