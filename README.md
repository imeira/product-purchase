## Author
Igor Meira de Jesus<br>
meira.igor@gmail.com

## Documentação da Bibioteca Utilizada
### https://docs.spring.io


## Descrição do APP
#### Compra de um produto
Endpoint para que possamos simular a compra de um produto, deve retornar uma lista de parcelas, acrescidas de juros com base na taxa SELIC de 1.15% ao mês (se possível consultar a taxa em tempo real), somente quando o número de parcelas for superior a 06 (seis) parcelas.
<br><br>
Esse endpoint deve receber os seguintes dados:
<br><br>
Request da requisição
<br>
Produto -> código, nome e valor.
<br>
Condição de Pagamento -> valor de entrada e quantidade de parcelas.
{ "produto": { "codigo": 123, "nome": "Nome do Produto", "valor": 9999.99 }, "condicaoPagamento": { "valorEntrada": 9999.99, "qtdeParcelas": 999 } }
<br><br>
Response da requisição
<br>
Lista de parcelas -> número da parcela, valor e taxa de juros ao mês (se houver).
[{ "numeroParcela": 1, "valor": 9999.99, "taxaJurosAoMes": 9999 }]
<br>

## Funcionamento / Instruções
Startar o projeto SpringBoot conforme comando abaixo:
<br>
mvn spring-boot:run (linux) 
<br>ou <br>
mvnw spring-boot:run (windows)
<br><br>
Após procedimento acima, acessar a documentação da API via link abaixo.
<br>
## Documentação da API
http://localhost:8080/swagger-ui.html
<br>
