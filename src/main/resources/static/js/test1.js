$(function(){
    $("#btnSubmit").click(function (event){
       event.preventDefault();
       var form = new FormData();
       // Create an FormData object 
       var fileData = $('#files')[0].files[0];
       var data = $("#txtName").val();
       
       console.log(typeof(data));
       form.append('file',fileData);
        jQuery.ajax({
            url: 'http://localhost:8080/uploadFile',
            data: form,
            cache: false,
            contentType: false,
            processData: false,
            method: 'POST',
            enctype: 'multipart/form-data',
            type: 'POST', 
            success: function(response){
                alert("Done");
            }
            
        });

        $.ajax({
            url: 'http://localhost:8080/uploadData',
            data:{"data":data}  ,
          
            type: 'Post', 
            success: function(response){
                alert(response);
            }
        })
    })
})