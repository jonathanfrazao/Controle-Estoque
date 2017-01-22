$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget)
	var codigoProduto = button.data('codigo')
	var nomeProduto = button.data('nome')
	
	var modal = $(this)
	var form = modal.find('form')
	var action = form.data('url-base')
	if(!action.endsWith('/')){
		action += '/'
	}
	
	form.attr('action', action + codigoProduto)
	form.find('.modal-body span').html('<span>Deseja excluir o produto <strong>' + nomeProduto + '</strong>?</span>')
}) 