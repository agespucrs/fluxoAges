	$('#addUserBtn').click(function() {
		var novoIntegrante = $('.addUser')[0].outerHTML;
		$('#addUserSection').append(novoIntegrante);
	});

	$('#removeUserBtn').click(function() {

		$('#addUserSection').find('.removeUser').parent().remove();
	});