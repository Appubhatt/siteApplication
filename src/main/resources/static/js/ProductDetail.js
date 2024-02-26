$(function() {
	$(document).ready(function() {
		var urlParams = new URLSearchParams(window.location.search);
		var id = urlParams.get('id');
		console.log(id);
		var productId;
		var userId = localStorage.userId;
		$.ajax({
			/*       <img src="/photos/mouse/hp-mouse1.jpg" id="product-image">
			   <div id="product-detail"><br>
				 <h3> <b> <div id="price">&#x20b9; 550</div> </b></h3>
			    
				 <h2 id="title">  HP 150 Wireless Mouse</h2>
				 <p id="mid-desc">Lorem ipsum dolor sit amet consectetur adipisicing elit. Molestias laborum ipsam deserunt recusandae itaque dolore dolorem minima sed modi officia nostrum quia necessitatibus eaque laudantium quasi praesentium, ducimus minus. Accusantium.</p>
				 <p id="large-desc">Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente quia omnis at aspernatur! Assumenda alias cum vero nemo officiis debitis repudiandae eum aliquam, fugiat vitae fuga accusantium eius. Quam eos quo a cumque sit. Quae molestias deserunt voluptatibus nisi dicta quia ratione exercitationem alias quibusdam, laboriosam, dolorum laudantium rerum quo iusto saepe in id, mollitia tempore numquam sapiente eaque voluptatem! Fugit deleniti laborum quas repudiandae numquam culpa. Doloremque enim illo inventore voluptate maxime! Similique, explicabo alias? Vitae, ducimus. Ullam tempore deserunt impedit, sunt dignissimos officiis minus reprehenderit praesentium veritatis magni rem omnis assumenda aut quos debitis iure harum ipsam eius voluptate repellendus, autem perferendis suscipit doloribus quam! Ex atque, illum hic repudiandae natus provident illo non aspernatur officia, autem aliquam voluptatum? Temporibus labore incidunt omnis veritatis ipsa quaerat, sit autem laborum magni vel deserunt, voluptas optio dolorem! Maxime quod architecto voluptas cum animi explicabo necessitatibus eveniet enim, nulla eos deserunt!</p>
				 <input class="btn btn-primary" type="submit" value="Buy"><br><br>
				 <div id="review-section">
				   <textarea id="review" name="review" rows="4" cols="50" placeholder="write a review"></textarea><br>
				   <input class="btn btn-primary" type="submit" value="Submit">
				 </div>
			   </div>*/
			//   <div class="button-container">
			//   <button class="btn btn-md">
			//     <img src="/photos/whishlist.png" alt="Wishlist" id="whishlistid">
			//   </button>
			// </div>
			url: "http://localhost:8080/get-product",
			type: "get",
			data: { id: id },
			success: function(response) {
				productId = response['productId'];

				console.log(response);
				var htmlbuilder = '';
				htmlbuilder += '<img src="/photos/user/' + response['fileCode'] + '" id="product-image" height=500px></img>';
				htmlbuilder += '<div id="product-detail"><br>';
				htmlbuilder += '<h3> <b> <div id="price">&#x20b9; ' + response['price'] + '</div> </b></h3>';
				htmlbuilder += '<h2 id="title">' + response['productName'] + '</h2>';
				htmlbuilder += ' <p id="mid-desc">' + response['shortDesc'] + '</p>';
				htmlbuilder += '<br><b>About Product</b>:<br>'
				htmlbuilder += ' <p id="large-desc">' + response['longDesc'] + '</p>';
				htmlbuilder += ' <button class="btn btn-primary" id="buybtn" onclick="buyProduct('+productId+')">Buy</button>&nbsp;&nbsp;';
				htmlbuilder += '<button class="btn btn-md" id="whishlistid">';

				htmlbuilder += '<img src="/photos/whishlist.png" alt="Wishlist" height="50px">';
				htmlbuilder += '</button>';
				htmlbuilder += ' <div id="review-section">'
				htmlbuilder += '<textarea id="review" name="review" rows="4" cols="50" placeholder="write a review"></textarea><br>'
				htmlbuilder += '<input class="btn btn-primary" type="submit" value="Submit">';

				htmlbuilder += '</div>'
				htmlbuilder += '</div>'
				$("#product-detail-container").html(htmlbuilder);
				$("#whishlistid").click(function() {
					console.log("HII");
					if (localStorage.role == 'admin') {
						alert("You dont have that permission");
					}
					else { addToWhishlist(productId, userId); }

				})
				$("#buybtn").click(function(){
					window.location="http://localhost:8080/Html/buy-product.html?id="+productId;
				});
			}
		})
		// $('#whishlistid').click(function() {
		//   console.log("Hii");
		//   if(localStorage.role=='admin'){
		//     alert("You dont have that permission");
		//   }
		//   addToWhishlist(productId,userId);
		//  // alert(productId+" "+userId);
		// })

	})


})

function addToWhishlist(productId, userId) {
	var jsonObj = {
		"userId": userId,
		"productId": productId
	}
	console.log(userId);
	if (!userId) {
		alert("Please signin to add item in whishlist");
		// window.location="http://localhost:8080/Html/test.html";
	}
	else {
		$.ajax({
			url: "http://localhost:8080/setWhishlist",
			type: "post",
			data: JSON.stringify(jsonObj),
			contentType: "application/json",
			processData: false,
			success: function(response) {
				console.log(response);
				alert("Product been added to whihslist");
			},
			error: function(response) {
				console.log(response);
				alert("There is some problem to add data")
			}
		})
	}
}

