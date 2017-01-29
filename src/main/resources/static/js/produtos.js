$('#produtoExclusaoModal').on('show.bs.modal', function(event){
	
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

$('#categoriaExclusaoModal').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget)
	var codigoCategoria = button.data('codigo')
	var descricaoCategoria = button.data('descricao')
	
	var modal = $(this)
	var form = modal.find('form')
	var action = form.data('url-base')
	if(!action.endsWith('/')){
		action += "/"
	}
	
	form.attr('action', action + codigoCategoria)
	form.find('.modal-body span').html('<span>Deseja excluir a categoria <strong>'+ descricaoCategoria +'</strong>?</span>')
})