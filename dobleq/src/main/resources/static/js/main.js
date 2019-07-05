$(document).ready(function () {

    $("#form1").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();
        

    });

});
function fire_ajax_submit() {

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    alert(username);

    $("#form1").prop("disabled", true);

    let delegado = $('#feedback')
	
	const url2 = "/cargarflujows/"+username+"/pass/"+password;
    console.log(url2);
	//populate dropdown list wujuu
	$.getJSON(url2,function(data){
		var items =[];
		var idt=0;
		var texpres=0;
	$.each(data,function(key,val){
		idt=val.taller.id;
		texpres=val.taller.texpress;
		items.push("<tr>");
		items.push("<td id=''"+key+"''>"+val.id+"</td>");
		items.push("<td id=''"+key+"''>"+val.taller.vehiculo.modelo+"</td>");
		items.push("<td id=''"+key+"''>"+val.estado.nombre+"</td>");
		
		items.push("<td id=''"+key+"''><img alt='No hay' src='/uploads/"+val.foto+"'/></td>");
		items.push("</td>");
	});
	if(items.leght!=0){
		alert(texpres);
		if(texpres==true){
		document.getElementById("buttonplaceform").action = "/taller/activar_express/"+idt;
	     var element = document.createElement("button");
	     element.appendChild(document.createTextNode("Aceptar!"));
	     var page = document.getElementById("buttonplaceform");
	     page.appendChild(element);	     
	     element.addEventListener("click", function(){
	    	 alert("Aceptando...");
	    	 });
		}
	}
	$("<tbody/>",{html: items.join("")}).appendTo("table");
});

}