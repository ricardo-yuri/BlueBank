# Projeto BlueBank - SQUAD DEVCOMPILERS

### API para gerenciar transações financeiras. O Sistema permite cadastro de clientes com seus dados pessoais, cadastro de conta bancária e registra todas as transações efetuadas pelo cliente em sua respectiva conta.


### Participantes da SQUAD


<p> Ricardo Alves </p>
<p> Ricardo Yuri </p>
<p>  Ramon Reis </p>
<p>  Matheus Vieira </p>
<p> Luis Otávio Batista </p>


## Começando
Para executar o projeto, será necessário instalar os seguintes programas:

<p> JDK 11: Necessário para executar o projeto Java. </p>
<p> Apache MAVEN 4.0.0: Necessário para realizar o build do projeto Java. </p>



## Endpoints da aplicação 

<table class="demo">
	<caption></caption>
	<thead>
	<tr>
		<th>Método HTTP
</th>
		<th>Endpoint</th>
		<th>Objetivo</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>GET</td>
		<td>/api/client/getAll</td>
		<td>Listar todos os clientes</td>
	</tr>
	<tr>
		<td>Post</td>
		<td>/api/client</td>
		<td>Criar um novo cliente</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/client/{id}</td>
		<td>Retornar o cliente por id</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/client/cep/{cep}</td>
		<td>Retornar as informações de endereço através do CEP</td>
	</tr>
	<tr>
		<td>DELETE</td>
		<td>/api/client/delete/{id}</td>
		<td>Deletar um cliente</td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/api/client/update</td>
		<td>Atualiza clients</td>
	</tr>
	<tr>
		<td>POST</td>
		<td>/api/account</td>
		<td>Criar uma nova conta</td>
	</tr>
	<tr>
		<td>Delete</td>
		<td>/api/account/delete/{id}</td>
		<td>Deletar uma conta </td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/api/account/deposit/{idAccount}/{value}</td>
		<td>Depositar uma conta</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/account/findById/{id}</td>
		<td>Retornar uma conta por Id</td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/api/account/withdraw/{idConta}/{value}</td>
		<td>Sacar valor de uma conta
	</td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/api/account/transfer/{value}</td>
		<td>Transferir Valor de uma conta</td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/api/account/update</td>
		<td>Atualizar uma conta</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/historic/{id}</td>
		<td>Retornar um histórico por ID</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/historic/findall</td>
		<td>Retornar todos os logs da aplicação</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/historic/list/{id}</td>
		<td>Retornar todo o histórico por id da conta</td>
	</tr>
	
	
</table>
