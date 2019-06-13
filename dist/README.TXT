Trabalho Prático de Computação Distribuída 
Gabriel de Oliveira Almeida
João Victor Silva Menezes
Milton Hirokazu Shimabukuro
DMC - FCT/UNESP - Presidente Prudente - SP

A aplicação depende da biblioteca mXparser, portanto deve-se executar somente dentro deste diretorio.


RESUMO: Além de descrever como utilizar a aplicação traz detalhes de sua implementação.
APLICAÇÃO
A aplicação traz consigo 3 funções vinda do cálculo numérico tais como: Resolução de sistema lineares através do método de Gauss-Seidal, determinar o valor para derivadas em dado ponto e encontrar o valor de uma integral definida, ambos para qualquer função, para tal feito utilizou-se a biblioteca mXparser, responsável para realizar análise sintática da expressão introduzida pelo o usuário e também em realizar o cálculo, por fim, há também o cálculo aproximado do valor de p utilizando o algoritmo de Monte Carlo, no qual consiste em gerar números aleatórios e utilizar apenas uma fração desse conjunto para satisfazer determinada aplicação, no caso, deve-se aceitar os valores que estão contido na circunferência de raio 1, assim, tem-se Área=p, porém, já que as linguagens de programação geralmente oferecem funções números reais e aleatórios entre 0 e 1, faz o uso apenas do primeiro quadrante mas no fim é multiplicado por 4 para obter o valor aproximado de p.
Para inicialização de toda a aplicação e possibilitar o uso de todas as funções, deve-se executar o .jar principal para iniciar os servidores e clientes, sendo que cada um são diferentes processos em execução, desse modo, necessita rodar novamente para abrir outra processo. Primeiramente, deve-se inicializar o servidor RMI, que será responsável pelo o processamento do objeto remoto enviado pelo servidor TCP, o qual contém a lógica de programação implementado, que neste caso e que também fará a comunicação com o cliente, por isso, o servidor TCP deve ser inicializado em seguida. Finalmente, o cliente pode ser iniciado sem que erros e exceções ocorra, uma vez que, haverá conexão com o servidor podendo utilizar suas funções.
IMPLEMENTAÇÃO
O projeto foi dividido por cada tipo de conexão entre os processos e sua respectiva interface gráfica, para separar as suas funcionalidades dentre elas estão Cliente, Servidor TCP e Servidor RMI, a seguir são apresentadas suas respectivas implementações.
Cliente
O Cliente contém todos os métodos para responder todas as exigências feita pelo o usuário a partir da interface gráfica, além de logo na inicialização do cliente, realizar a conexão TCP com o Servidor TCP, onde um Socket é instanciado na porta 12345 e no endereço localhost.
Para a comunicação entre Cliente e Servidor utiliza-se Classes em comum, onde contém o construtor e alguns atributos úteis para computar as funções desejadas, por isso, surge a necessidade de enviar e receber Objetos através de métodos da classe ObjectOutputStream e ObjectInputStream, para saber de qual classe o objeto enviado/recebido se trata utiliza o operador instanceof.
O Cliente contém uma Thread para tratar o recebimento das mensagens via TCP pelo Servidor TCP, para permitir diversas requisições ao cliente antes de se obter a resposta de cada uma.
Caso haja desconexão as exceções são responsáveis em fechar o Socket e manter a estabilidade da execução da aplicação, porém não haverá conexão novamente com o cliente, devendo assim, reinicia-lo
Servidor TCP
    O Servidor logo ao inicializar possui uma Thread para tratar a conexão de novos clientes, mantendo para cada cliente uma conexão persistente, ou seja, a conexão TCP consiste até que haja a desconexão pelo o próprio Cliente. Assim um ServerSocket deve sempre ficar escutando a porta 1234, e para cada conexão com o cliente o seu Socket é salvo em um atributo e também há inicialização da Classe RMIClient, responsável pela comunicação com o ServidorRMI.
    A classe ServerTCPCliente trata de todas as requisições feita pelo Cliente, onde há um loop e uma Thread responsável pela execução não-sequencial e o recebimento das mensagens enviada pelo o cliente e para cada mensagem recebida deve analisar seu tipo (instanceof) para que seja solicitado corretamente ao RMIClient instanciar objetos remotos e requisitar ao ServidorRMI computá-las.
    A cada cliente conectado ao ServidorTCP instância uma nova conexão ao link responsável pelo conexão com o ServidorRMI, criando os canais de comunicação e o registro RMI através do comando LocateRegistry.getRegistry, onde os parâmetros são o endereço localhost e porta padrão do RMI, 1099, e se conecta com o Servidor Remoto (ServidorRMI) utilizando o lookup, devendo assim chamar o método da interface Application para que seja possível a execução do objeto remoto.
    Cada aplicação possui sua própria classe, onde há seus atributos e métodos já implementado, todos implementam a classe Serializable para que seja possível o envio do objeto e a interface AppTask devendo assim sobrescrever seu método calculate(), onde há a chamada da operações e o retorno necessário para tal aplicação.
    Ao receber mensagem do ServidorRMI, preenche o atributo resultado do objeto recebido pelo o Cliente e encaminha de volta.
    Caso haja desconexão do cliente, a exceção do programa fecha o Socket, porém ainda continua executando normalmente a espera de um novo cliente.
    A interface gráfica é composta por um JTextArea que recebe todos os comandos oriundo do console da Virtual Machine Java, incluídos mensagens da aplicação, exceções e erros.
Servidor Remoto
Nessa aplicação ocorre a execução do Servidor Remoto, onde é feita a computação dos objetos remotos, ou seja, processo onde os cálculos são processados. Para isso deve-se criar um registro RMI no endereço localhost e porta 1099 e nela também implementa a interface Application, que possui o método para a execução do objeto e a interface deve extender a Classe Remote.
Desse modo, o servidor remoto exporta o objeto remoto e em seguida já o executa para que em seguida forneça uma resposta ao ServidorTCP.
A interface gráfica é composta por um JTextArea que recebe todos os comandos oriundo do console da Virtual Machine Java, incluídos mensagens da aplicação, exceções e erros.
    






