$(document).ready(function () {

    $("#form1").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();
        alert('hola3');

    });

});
function fire_ajax_submit() {
/*
    var login = {}
    login["username"] = $("#username").val();
    login["password"] = $("#password").val();

    $("#form1").prop("disabled", true);*/

    let delegado = $('#feedback2')
	delegado.empty();
	delegado.append('<option selected="true" disabled>Selecciona</option>');
	delegado.prop('selectedIndex',0);
	const url2 = "/cargarflujows/profesor@bolsadeideas.com/pass/12345";
	//populate dropdown list wujuu
	$.getJSON(url2,function(data){
		console.log('hola2');
	$.each(data,function(key,entry){
		console.log(entry);
		delegado.append($('<option></option>').attr('value',entry.id).text(entry.estado.nombre));
	})
});

}