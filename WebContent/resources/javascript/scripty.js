var arrayIdsElementsPage = new Array;

function invalidarSession(context, pagina) {
	document.location = (context + pagina + ".jsf");
}

function validarSenhaLogin() {

	j_username = document.getElementById("j_username").value;
	j_password = document.getElementById("j_password").value;

	if (j_username === null || j_username.trim() === '') {
		alert("Informe o Login.");
		$('#j_username').focus();
		return false;
	}

	if (j_password === null || j_password.trim() === '') {
		alert("Informe a Senha.");
		$('#j_password').focus();
		return false;
	}

	return true;

}

function logout(contextPath) {
	
	var post = 'invalidar_session';
	
	$.ajax({
		type:"POST",
		url:post
	}).always(function(resposta) {
		document.location = contextPath + '/j_spring_security_logout';		
	});
	
}

function abrirmenupop() {
	$("#menupop").show('slow').mouseleave(function() {
		fecharMenuPop();
	});	
}

function fecharMenuPop() {
	if($("#menupop").is(":visible")) {
		$("#menupop").hide("slow");
	}
}

function recirecionarPagina(context, pagina) {
	
	pagina = pagina + ".jsf";
	
	document.location = context + pagina;
	
}

/**
 * Carrega um array global com os ids de todos os componenstes da pagina para ter facilidades em obtenção de valores dos componentes
 * bem como trabalhar com ajax
 */

function carregarIdElementosPagina() {
	arrayIdsElementsPage = new Array;
	
	for(form = 0; form <= document.forms.length; form++) {
		var formAtual = document.forms[form];
		
		if(formAtual != undefined) {
			for(i = 0; i< document.forms[form].elements.length; i++) {
				
				if(document.forms[form].elements[i].id != '') {
					arrayIdsElementsPage[i] = document.forms[form].elements[i].id
				}
			}
		}
	}
}

/**
 * Retorno o valor do id do component dentro do documento html passando como parametro a descrição do id declarada em jsf
 * @param id
 * @returns
 */


function getValorElementPorId(id) {
	carregarIdElementosPagina();
	
	for(i = 0; i < arrayElementsPage.length; i++) {
		var valor = "" + arrayIdsElementsPage[i];
		
		if(valor.indexOf(id) > -1) {
			return valor;
		}
	}
	return idundefined;
}

function localeData_pt_br() {
	PrimeFaces.locales['pt'] = {
		closeText : 'Fechar',
		prevText : 'Anterior',
		nextText : 'Próximo',
		currentText : 'Começo',
		monthNames : [ 'Janeiro', 'Fevereiro', 'Marcio', 'Abril', 'Maio',
				'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro',
				'Dezembro' ],
		monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul',
				'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
		dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
				'Sexta', 'Sábado' ],
		dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
		dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
		weekHeader : 'Semana',
		firstDay : 0,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : '',
		timeOnlyTitle : 'São Horas',
		timeText : 'Tempo',
		hourText : 'Hora',
		minuteText : 'Minuto',
		secondText : 'Segundo',
		ampm : false,
		month : 'Mês',
		week : 'Semana',
		day : 'Dia',
		allDayText : 'Todo o Dia'
	};

}

function initTamplete() {
	$(document).ready(function() {
		  $('#menupop').hide();
		  $('#barramenu').hide();
		  $('#barramenu').css("left", "-200px");
		  $('#iniciarmenu').click(function() {
		  	if ($('#barramenu').is(':visible')) {
		  	  ocultarMenu();
		  	} else {
		  	  $('#barramenu').show();
		  	  $('#barramenu').animate({"left":"0px"}, "slow");	
		  	}
		  });
		});
	}

function ocultarMenu() {
	  $('#barramenu').animate({"left":"-200px"}, "slow", function() {
	  	$('#barramenu').hide();
	  });
	}