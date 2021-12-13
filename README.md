# Projeto BlueBank - SQUAD DEVCOMPILERS

### API para gerenciar transações financeiras. O Sistema permite cadastro de clientes com seus dados pessoais, cadastro de conta bancária e registra todas as transações efetuadas pelo cliente em sua respectiva conta.


### Participantes da SQUAD

<p>
  <a href="https://www.linkedin.com/in/ricardo14231/" rel="nofollow noreferrer">
    <img src="https://i.stack.imgur.com/gVE0j.png" alt="linkedin"> Ricardo Alves Farias
  </a> &nbsp; 
	</p>
  <p> <a href="https://www.linkedin.com/in/ricardoyuri/" rel="nofollow noreferrer">
    <img src="https://i.stack.imgur.com/gVE0j.png" alt="linkedin"> Ricardo Yuri
  </a> &nbsp; </p>
  
<p> <a href="https://www.linkedin.com/in/matheus-alan/" rel="nofollow noreferrer">
    <img src="https://i.stack.imgur.com/gVE0j.png" alt="linkedin"> Matheus Alan Vieira
  </a> &nbsp; </p>
  
  <p> <a href="https://www.linkedin.com/in/ramon-reis-57aa0177/" rel="nofollow noreferrer">
    <img src="https://i.stack.imgur.com/gVE0j.png" alt="linkedin"> Ramon Reis
  </a> &nbsp; </p>
  
  <p> <a href="https://www.linkedin.com/in/luisotaviobatistadev/" rel="nofollow noreferrer">
    <img src="https://i.stack.imgur.com/gVE0j.png" alt="linkedin"> Luis Otávio Batista
  </a> &nbsp; </p>
		


## Exucação do Projeto
Para executar o projeto, será necessário instalar os seguintes programas:

<p> JDK 11: Necessário para executar o projeto Java. </p>
<p> Apache MAVEN 4.0.0: Necessário para realizar o build do projeto Java. </p>


## Modelo Entidade Relacionamento

![alt text](https://github.com/ricardo-yuri/BlueBank/blob/main/database/diagrama_entidade_relacionamento.png)



## Lista de Endpoints da aplicação 

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


### Por Dentro da Aplicação.

### Cadastrando um um cliente

![alt text](https://github.com/ricardo-yuri/BlueBank/blob/main/imgs/cadastrarcCliente.png)

### Listando um cliente por id

![alt text](https://github.com/ricardo-yuri/BlueBank/blob/main/imgs/Listando-Cliente-Por-ID.png)

### Atualizando um cliente

#### No caso de exemplo acima, foi digitado o e-mail de um outro cliente no campo de e-mail do Sr Ocimar Ferreira. Para atualizar devemos fazer da seguinte maneira

![alt text](https://github.com/ricardo-yuri/BlueBank/blob/main/imgs/Atualizando%20Cliente.png)

### Deletando um cliente


![alt text](https://github.com/ricardo-yuri/BlueBank/blob/main/imgs/DeletandoCliente.png)


### Criando uma conta. Necessário cliente já estar cadastrado. Não é necessário preencher o número da conta, pois ela será preenchida automaticamente quando for criada.

![alt text](https://github.com/ricardo-yuri/BlueBank/blob/main/imgs/criandoContaClienteOcimar.png)

### Fazendo depósito de R$ 800 reais na conta de Id 1

![alt text](https://github.com/ricardo-yuri/BlueBank/blob/main/imgs/deposito.png)

### 

Toda a documentação da API está localizada no seguinte endereço pelo Swagger.

http://ec2-52-15-238-46.us-east-2.compute.amazonaws.com:5000/swagger-ui.html#/account-controller/transferAccountUsingPUT



## Deploy na AWS

### Ferramentas utilizadas

<p> Instância EC2 - Sistema Operacional Amazon linux 2 </p>

<p> Docker - Imagens Open Jdk e Mysql 8 <p/>

<p> Pipeline - Instalação feita diretamente no servidor Anazon linux 2. <p/>


	

	

