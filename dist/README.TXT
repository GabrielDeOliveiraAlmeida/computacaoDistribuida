Trabalho Pr�tico de Computa��o Distribu�da 
Gabriel de Oliveira Almeida
Jo�o Victor Silva Menezes
Milton Hirokazu Shimabukuro
DMC - FCT/UNESP - Presidente Prudente - SP

A aplica��o depende da biblioteca mXparser, portanto deve-se executar somente dentro deste diretorio.


RESUMO: Al�m de descrever como utilizar a aplica��o traz detalhes de sua implementa��o.
APLICA��O
A aplica��o traz consigo 3 fun��es vinda do c�lculo num�rico tais como: Resolu��o de sistema lineares atrav�s do m�todo de Gauss-Seidal, determinar o valor para derivadas em dado ponto e encontrar o valor de uma integral definida, ambos para qualquer fun��o, para tal feito utilizou-se a biblioteca mXparser, respons�vel para realizar an�lise sint�tica da express�o introduzida pelo o usu�rio e tamb�m em realizar o c�lculo, por fim, h� tamb�m o c�lculo aproximado do valor de p utilizando o algoritmo de Monte Carlo, no qual consiste em gerar n�meros aleat�rios e utilizar apenas uma fra��o desse conjunto para satisfazer determinada aplica��o, no caso, deve-se aceitar os valores que est�o contido na circunfer�ncia de raio 1, assim, tem-se �rea=p, por�m, j� que as linguagens de programa��o geralmente oferecem fun��es n�meros reais e aleat�rios entre 0 e 1, faz o uso apenas do primeiro quadrante mas no fim � multiplicado por 4 para obter o valor aproximado de p.
Para inicializa��o de toda a aplica��o e possibilitar o uso de todas as fun��es, deve-se executar o .jar principal para iniciar os servidores e clientes, sendo que cada um s�o diferentes processos em execu��o, desse modo, necessita rodar novamente para abrir outra processo. Primeiramente, deve-se inicializar o servidor RMI, que ser� respons�vel pelo o processamento do objeto remoto enviado pelo servidor TCP, o qual cont�m a l�gica de programa��o implementado, que neste caso e que tamb�m far� a comunica��o com o cliente, por isso, o servidor TCP deve ser inicializado em seguida. Finalmente, o cliente pode ser iniciado sem que erros e exce��es ocorra, uma vez que, haver� conex�o com o servidor podendo utilizar suas fun��es.
IMPLEMENTA��O
O projeto foi dividido por cada tipo de conex�o entre os processos e sua respectiva interface gr�fica, para separar as suas funcionalidades dentre elas est�o Cliente, Servidor TCP e Servidor RMI, a seguir s�o apresentadas suas respectivas implementa��es.
Cliente
O Cliente cont�m todos os m�todos para responder todas as exig�ncias feita pelo o usu�rio a partir da interface gr�fica, al�m de logo na inicializa��o do cliente, realizar a conex�o TCP com o Servidor TCP, onde um Socket � instanciado na porta 12345 e no endere�o localhost.
Para a comunica��o entre Cliente e Servidor utiliza-se Classes em comum, onde cont�m o construtor e alguns atributos �teis para computar as fun��es desejadas, por isso, surge a necessidade de enviar e receber Objetos atrav�s de m�todos da classe ObjectOutputStream e ObjectInputStream, para saber de qual classe o objeto enviado/recebido se trata utiliza o operador instanceof.
O Cliente cont�m uma Thread para tratar o recebimento das mensagens via TCP pelo Servidor TCP, para permitir diversas requisi��es ao cliente antes de se obter a resposta de cada uma.
Caso haja desconex�o as exce��es s�o respons�veis em fechar o Socket e manter a estabilidade da execu��o da aplica��o, por�m n�o haver� conex�o novamente com o cliente, devendo assim, reinicia-lo
Servidor TCP
    O Servidor logo ao inicializar possui uma Thread para tratar a conex�o de novos clientes, mantendo para cada cliente uma conex�o persistente, ou seja, a conex�o TCP consiste at� que haja a desconex�o pelo o pr�prio Cliente. Assim um ServerSocket deve sempre ficar escutando a porta 1234, e para cada conex�o com o cliente o seu Socket � salvo em um atributo e tamb�m h� inicializa��o da Classe RMIClient, respons�vel pela comunica��o com o ServidorRMI.
    A classe ServerTCPCliente trata de todas as requisi��es feita pelo Cliente, onde h� um loop e uma Thread respons�vel pela execu��o n�o-sequencial e o recebimento das mensagens enviada pelo o cliente e para cada mensagem recebida deve analisar seu tipo (instanceof) para que seja solicitado corretamente ao RMIClient instanciar objetos remotos e requisitar ao ServidorRMI comput�-las.
    A cada cliente conectado ao ServidorTCP inst�ncia uma nova conex�o ao link respons�vel pelo conex�o com o ServidorRMI, criando os canais de comunica��o e o registro RMI atrav�s do comando LocateRegistry.getRegistry, onde os par�metros s�o o endere�o localhost e porta padr�o do RMI, 1099, e se conecta com o Servidor Remoto (ServidorRMI) utilizando o lookup, devendo assim chamar o m�todo da interface Application para que seja poss�vel a execu��o do objeto remoto.
    Cada aplica��o possui sua pr�pria classe, onde h� seus atributos e m�todos j� implementado, todos implementam a classe Serializable para que seja poss�vel o envio do objeto e a interface AppTask devendo assim sobrescrever seu m�todo calculate(), onde h� a chamada da opera��es e o retorno necess�rio para tal aplica��o.
    Ao receber mensagem do ServidorRMI, preenche o atributo resultado do objeto recebido pelo o Cliente e encaminha de volta.
    Caso haja desconex�o do cliente, a exce��o do programa fecha o Socket, por�m ainda continua executando normalmente a espera de um novo cliente.
    A interface gr�fica � composta por um JTextArea que recebe todos os comandos oriundo do console da Virtual Machine Java, inclu�dos mensagens da aplica��o, exce��es e erros.
Servidor Remoto
Nessa aplica��o ocorre a execu��o do Servidor Remoto, onde � feita a computa��o dos objetos remotos, ou seja, processo onde os c�lculos s�o processados. Para isso deve-se criar um registro RMI no endere�o localhost e porta 1099 e nela tamb�m implementa a interface Application, que possui o m�todo para a execu��o do objeto e a interface deve extender a Classe Remote.
Desse modo, o servidor remoto exporta o objeto remoto e em seguida j� o executa para que em seguida forne�a uma resposta ao ServidorTCP.
A interface gr�fica � composta por um JTextArea que recebe todos os comandos oriundo do console da Virtual Machine Java, inclu�dos mensagens da aplica��o, exce��es e erros.
    






