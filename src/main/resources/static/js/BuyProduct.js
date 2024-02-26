$(function() {
	var urlParams = new URLSearchParams(window.location.search);
	var id = urlParams.get('id');
	console.log(id);

	/*   <img src="https://dummyimage.com/300x300/000/fff" alt="">
                <div class="proText">
                    <h1>Name: Logitech M90</h1>
                    <div>
                        <h1>Description</h1>
                        <p> Lorem ipsum dolor sit, amet consectetur adipisicing elit. Magni quos natus iure repellat id
                            ut cum sequi voluptatum velit nihil, harum enim consectetur porro, veritatis repudiandae
                            repellendus minus ducimus ipsam itaque in assumenda adipisci! Blanditiis ad molestiae
                            incidunt, error commodi voluptatum explicabo esse. Expedita laborum culpa voluptatem quas
                            neque vitae. </p>
                    </div>
                    <div class="quantity-input">
                        <button class="decrease-btn">-</button>
                        <input type="number" value="1" min="1" style="text-align: center;">
                        <button class="increase-btn">+</button>
                    </div>
                    <h1>Total Price : 0</h1>
                    <div id="btn">
                        <button>Add to Cart</button>
                        <button id="buyNow">Buy Now</button>
                    </div>
                </div>*/
	$.ajax({
		url: "http://localhost:8080/buyProduct",
		type: "get",
		data: { id: id },

		success: function(response) {
			
			var htmlAddressBuilder = '';
			var htmlProductBuilder = '';
			console.log(response);
			var addressResp = response['addressList']
			for (var i = 0; i < addressResp.length; i++) {
				htmlAddressBuilder += '<div class="box">' +
					'<div class="add1">' +
					'<input type="radio" name="address" value="'+addressResp[i].addressId+'" id="rdAdd">' +
					'<div class="boxText">' +
					' <h1><B>' + addressResp[i]['saveAs'] + '</B></h1>' +
					'<p>' + addressResp[i]['address'] + 
					',' + addressResp[i]['area'] + 
					',' + addressResp[i]['landmark'] + 
					',' + addressResp[i]['city'] + 
					',' + addressResp[i]['state'] +
					 ',' + addressResp[i]['pincode'] +
					  '' + '</p> <br>' +
					'</div></div></div></br>';
			}
			$("#address-container").html(htmlAddressBuilder);
			var productResp = response['product'];
			var price=Number(productResp['price']);
			htmlProductBuilder+='<img src="/photos/user/'+productResp['fileCode']+'" style="width: 300px; height: 300px;" alt="productImg">';
			htmlProductBuilder+=' <div class="proText">';
			htmlProductBuilder+=' <h1>Name: '+productResp['productName']+'</h1>'+
								'<div>'+
								'<h1>Description</h1>'+
								'<p>'+productResp['shortDesc']+'</p>'+
								'</div>'+
								' <div class="quantity-input">'+
                        		'<button class="decrease-btn">-</button>'+
                        		'<input type="number" value="1" min="1" style="text-align: center;" id="quantity">'+
                        		'<button class="increase-btn">+</button>'+
                    			'</div>'+
                    			'<h1 id="total">Total Price : '+price+'</h1>'+
                				'</div>';
            $(".proCont").html(htmlProductBuilder);
            var qnt=$("#quantity").val();
            $("#quantity").click(function(){
				qnt=$("#quantity").val();
				price=Number(productResp['price']);
				price=	qnt*price;
				$("#total").html("Total Price :"+price);
				console.log(price);
			});
			$("#deliverbtn").click(function(){
				var address =$("input[type='radio'][name='address']:checked").val(); ;
				if(!address){
					alert("Please select an address");
				}
				else{
					deliverItem(address,localStorage.userId,id,price,qnt);
				}
				console.log(address);
				console.log(localStorage.userId);
				console.log(price);
				console.log(qnt);
			});
		},
		error: function(response) {
			console.log(response);

		}
	})
});

function deliverItem(addressId,userId,productId,price,qnt){
	var json={
		"addressId":addressId,
		"userId" : userId,
		"productId" :productId,
		"price" : price,
		"qnt" :qnt
	}
	$.ajax({
		url:"http://localhost:8080/deliver-product",
		data:JSON.stringify(json),
		type: "post",
		contentType:"application/json",
		processData:false,
		success:function(response){
			alert(response);
			//window.location="htthttp://localhost:8080/Html/myOrders.html";
		}
		
	})
}