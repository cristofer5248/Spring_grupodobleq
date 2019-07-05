$(document).ready(function () {

    $("#form1").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

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
	const url2 = "http://localhost:8181/cargarflujows/profesor@bolsadeideas.com/pass/12345";
	//populate dropdown list wujuu
	$.getJSON(url2,function(data){
	$.each(data,function(key,entry){
		console.log(entry);
		delegado.append($('<option></option>').attr('value',entry.id).text(entry.id));
	})
});

}