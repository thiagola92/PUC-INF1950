# Segurança da informação

5 Pilares da segurança da informação:
  * **Integridade** - Ser capaz de verificar que a informação não foi alterada.
  * **Autenticidade** - Ter certeza de quem produziu aquela informação.  
  * **Sigilo** - Esconder a informação para que pessoas sem autoridade não acessem ela.  
  * **Controle de acesso** - Saber se a pessoa tem autoridade para manipular a informação.
  * **Disponibilidade** - Garantir que esteja disponível quando o usuário precisa.  

Existem mais duas coisas que precisa levar em conta quando se trata de *transação eletrônica*:
  * **Irretratabilidade** - Transação feita não pode ser desfeita.
  * **Man-in-the-middle attack** - Uma terceira pessoa no meio da comunicação.  

Já para começar você precisa entender as seguintes coisas:
  * **Funções hash** - Quando falamos de função hash dentro do assunto de criptografia, nós nos referenciamos a *funções hash de criptografia*, elas tem propriedades que tornam elas boas para o assunto.  
  * **Message** - A entrada da função hash normalmente é chamada message ou *chave*.  
  * **Digest** - A saida da função hash, também chamam as vezes de *valor do hash* ou *hash*.  

# Integridade
O básico do básico é ser capaz de verificar se a informação está integra, ou seja, que ela não foi adulterada.  
Sem integridade, já não podemos utilizar a informação para nada pois ela não é confiável.  
A informação pode ter sido comprometida propositalmente ou por erro de transmição da rede mesmo.  

# Autenticidade
Dada que a informação chegou integra, temos que ter certeza que ela veio de quem esperavamos.  
Normalmente essa etapa é feita ao mesmo tempo que a verificação de integridade, por isso essas duas etapas são as mais básicas de segurança da informação.  
Você pode até não estar precupado com sigilo, pois é uma informação pública, mas você quer ter certeza que a informação não foi alterada e que veio de quem você estava esperando.  

# Sigilo
É aqui que se apresenta criptografia, uma maneira de esconder a informação de forma que só pessoas autorizadas consigam enxergar.  
Existem basicamente dois tipos de algoritmos de criptografias: simétrico e assimétrico  

# Controle de acesso
Saber se uma pessoa tem autorização para manipular com a informação. Mesmo que a informação esteja integra e autentica, você precisa saber se o usuário tem permissão para manipular com a informação.  
Basicamente, você tem um arquivo mas não pode alterar ele se não tem permissão para isso.  

# Disponibilidade
Não adianta garantir todos os outros se o sistema não estiver disponível quando precisar.  

---

# Irretratabilidade
Ultima coisa que você não quer numa transação é ter maneira de "volta atrás" nela.  
Uma vez que a operação de pagamento foi feita, você não quer que tenha como desfazer/retirar ela, a negociação precisa ficar registrada.  
Em transação é importante você ter como provar que aquela pessoa aceitou aquela transação, por isso uma vez tratado não pode ser 'destratado'.  

# Temporalidade
Quando você quer que uma mensagem seja apenas válida em um momento, você tem que tratar temporalidade.  
Você pode querer que a mensagem só seja válida a primeira vez que for enviada, ou você quer que só seja válida durante aquela transmissão...

## Man-in-the-middle attack
Quando falamos de comunicação em rede, temos sempre que levar em conta que pode ter alguém no meio dessa comunicação tentando fazer algo com as informação que passamos. Esse ataque é chamado de man-in-the-middle.  
**O que o man-in-the-middle pode fazer?**  
 * Ele pode ter lido as informações passadas por ele
 * Ele pode ter alterado as informações passadas por ele
 * Ele pode ter copiado as informações para mandar numa data futura
 * ...

## Protegendo
Se esse man-in-the-middle estiver copiando sua informação para mandar em uma **data futura**, teremos o problema que a informação que ele está passando vai respeitar os 3 pilares que deveriam proteger nossa informação (integridade, autenticidade, sigilo).  
Por isso contamos com mais dois controles de informação:  
 * **Janela de tempo** - Combinar de que a mensagem tem que chegar em menos de X millisegundos
 * **Sequenciamento** - O destino está esperando primeiro a mensagem X, depois X+1, depois X+2...

### Janela de tempo
*A idéia é o destino (quem está recebendo a mensagem) ser capaz de notar que está recebendo algo velho/antigo e por isso rejeitar essa informação.*  

Para isso vamos trabalhar com janela de tempo:  
No inicio da comunicação é negociado tudo que você vai usar de cifra e isso apenas vai valer por um tempo.  
Quando essa janela de tempo acabar, precisam negociar novamente para continuar a comunicação.  

A idéia é simples,  
A origem negocia uma janela de tempo com o destino,  
A origem envia a mensagem,  
O destino verifica se está dentro da janela de tempo,  
Se estiver aceita, se não recusa.  

Se alguém pegar a mensagem no meio e tentar retransmitir outra hora, não vai funcionar pois vai estar fora da janela de tempo.  
Se alguém tentar alduterar a mensagem, vai gastar tempo alduterando e a janela de tempo deve acabar.  

### Sequenciamento
*A idéia é o destino ser capaz de notar que está recebendo algo velho/antigo e por isso reijeitar essa informação.*  

Nesse caso vamos trabalhar com sequenciamento:  
No inicio da comunicação o destino está esperando a mensagem X. Se receber a mensagem X, vai esperar a mensagem X+1... Por assim vai.  
Se o man-in-the-middle copiar a mensagem X e tentar enviar depois que o destino já recebeu a mensagem X, o destino vai recusar pois não era a mensagem que estava esperando, ele estava esperando X+1.  

Ou seja,  
O destino está esperando X,  
A origem envia X,  
O destino verifica se estava esperando a mensagem X,  
Se estiver, aceitar, se não recusa.  

Se alguém tentar enviar a mensagem fora de ordem que o destino estava esperando, vai ser rejeitada.   
Normalmente sequenciamento e janela de tempo são usados juntos para melhor segurança.  

# Técnicas de controle
As técnicas que podemos usar para justamente fazer o controle dos pilares ou do que mais for preciso.  

Resumidamente iremos usar técnicas criptograficas e resumo de mensagem para conseguir garantir os 3 primeiros pilares (integridade, autenticidade e sigilo).  

## Criptografia
Criptografar informação é reescrever essa informação de forma que oculte o significado original. Note que a informação é ocultada, ou seja, não é destruida.  
Após uma informação ser criptografada, ela tem que ter alguma maneira de voltar a ser a informação original (ela tem que ser descriptografada).  

O algoritmo de criptografia recebe uma informação e devolve a informação criptografada.  
O problema é que apenas usar o algoritmo não garante segurança, se alguém pegar o algoritmo, essa pessoa consegue fazer engenharia reversa.  

O que garante a segurança de não conseguirem fazer a engenharia reversa é a **chave**.  
O algoritmo de criptografia recebe uma informação e uma chave, após isso devolve a informação criptografada.  

Para descriptografar a informação você também vai precisar da chave e saber qual foi o algoritmo de criptografia usado.  

**Chave** - Chave passada ao algoritmo de criptografia para que o resultado não sejá previsível.  
**Cifra** - Uma informação criptografada é chamada de cifra.  
Obs: Em português criptografar é a mesma coisa que cifrar.  

Outros termos utilizados:  
**Texto plano** - A informação antes de ser criptografada.  
**Texto cifrado** - A informação após ter sido criptografada.  

Toda dificuldade em fazer a engenharia reversa se da pelo fato que a pessoa tentando descobrir a informação não conhece a chave.  
Ela teria que fazer todas tentativas de chave, o que custaria muito do computador.  

As chaves giram em torno de uma combinação de bits.  
Uma chave com 1024 bits formaria 2 ^ 1024 combinações, o que é 1,7977×10³⁰⁸.  
O tempo que custaria para fazer todas as combinações seria incrivelmente grande.  

### Algoritmo de chave simétrica
O algoritmo de chave simétrica funciona usando a mesma **chave secreta** para criptografar/descriptografar.  
Tanto a origem quanto o destino conhecem a chave secreta.  

Se apenas eu e você temos a chave secreta então só eu e você podemos criptografar/descriptografar, isso garante que a informação tem **sigilo** e **autenticidade**.  

Obs: Note que em geral o algoritmo que criptografia e de descriptografia são diferentes, pois o de descriptografar tem que fazer a operação inversa do outro. O que eles tem de simétrico é a chave.  

Problema: Como compartilhamos essa **chave secreta** entre origem e destino sem correr risco de outra pessoa pegar?  

### Algoritmo de chave assimétrica
O algoritmo de chave assimétrica funciona usando duas chaves diferentes para criptografar/descriptografar.  
A origem tem uma chave e o destino recebe outra chave.  

A chave que a origem tem é a **chave privada**.  
A chave que o destino recebe é a **chave pública**.  
Ambas podem ser usadas para criptografar, mas cada uma só pode descriptografar mensagem da outra.  

As duas chaves são criadas por mim, mas uma eu não me incomodo em compartihar.  
Eu passo minha chave pública para todos que querem se comunicar comigo e consigo descriptografar com a minha chave privada.  

Quando você quer **sigilo** você criptografa com a chave pública, isso garante que apenas quem tem a chave privada consiga descriptografar.  
Quando você quer **autenticidade** você criptografa com a chave privada, se com a chave pública conseguirem descriptografar então a mensagem realmente é sua.  

Se eu tiver a minha chave privada e a sua chave pública  
E você tiver a sua chave privada e a minha chave pública  
Conseguimos fazer uma comunicação com sigilo e autenticidade.  

Problema: Como compartilhamos **chave privada** e **pública** entre origem e destino sem correr risco de outra pessoa pegar?  

### Compactar
Criptografar é uma operação que custa muito, por isso que em geral compactamos antes os dados que vão ser criptografados.  
Mas não adiantar compactar algo que já é compactado, por exemplo um formato de imagem que já é um padrão compactado.  

Criptografar com a criptografia assimétrica é bem custosa, queremos usar a criptografia simétrica para criptografar os dados pois é bem mais rápido.  

Como a **chave pública** é algo que qualquer um pode ter, eu posso criptografar com a sua chave pública.  
Como a **chave privada** é algo que apenas você tem, você pode descriptografar com a sua chave privada.  
E se eu usar esse método para te enviar a **chave secreta**?  
Agora podemos passar informação de um para o outro usando criptografia simétrica (que é bem menos custosa)


| Dados         |     | Criptografia usada              | Resultado        |
| ---           | --- | ---                             | ---              |
| Dados         | >>  | Criptografa com chave secreta   | Massa cifrada    |  
| Chave secreta | >>  | Criptografa com chave pública   | Envelope digital |

E enviamos massa cifrada + envelope digital.  

### Observação
Até agora garantimos **sigilo** e **autenticidade** mas não garantimos **integridade**.  

Tanto na criptografia simétrica quanto na assimétrica o algoritmo não diz se você usou a chave certa ou errada.  

Na hora de criptografar/descriptografar:  
Se usou a chave certa você vai ter uma cifra correta ou texto plano correto.  
Se usou a chave errada você vai ter um lixo.  

A criptografou e enviou para B.  
Durante o caminho alterou-se 1 bit **/** Um hacker alterou 1 bit  
B descriptografou lixo ou está lendo informação incorreta.  

### Integridade
Para garantirmos **integridade** usamos o algoritmo message **digest** (um algoritmo hash).  
Esse algoritmo processa os bits de entrada e gera um bloco de bits de saida.  

A idéia é justamente usar esse algoritmo nos dados que ainda vamos criptografar, para ele gerar um bloco de bits único<sup>[1]</sup> para aquele arquivo.  
Quem receber os dados pode jogar os dados no algoritmo e verificar se vai dar o mesmo bloco de bits.  

<sup>[1]</sup> *Único é uma palavra muito forte, eu queria dizer que as chances são pequenas dependendo do algoritmo.*   

### Assinatura digital  
Agora que você também envia o digest, você tem que proteger ele, você vai fazer da mesma maneira que protegia a chave secreta.  

**Assinatura digital** - Digest criptografado com a chave privada  

### Cartório
A pergunta principal é:  
Como você sabe que aquela chave pública que você tem é daquela pessoa?  

Se você está na rua e eu chego para você e falo "Eu sou o ThiagoLA92", como você pode saber que eu realmente sou o ThiagoLA92?  

A resposta é:  
Cartório  

Vocês precisam de uma pessoa em comum que ambos confie, essa pessoa é justamente o cartório, ele pode confirmar se a chave pública que você possui é realmente minha e vice-versa.  

Por isso ambos os lados precisam já ter ido no cartório, assinado e criado firma.  

O que é usada como assinatura no cartório?
Não é usada assinatura digital pois ela depende do digest e o digest varia conforme os dados passados.  
Ele usa **certificado digital**.  

**Certificado digital** - Documento que tem informações sobre você, sobre o cartório e a sua chave pública.  

Esse certificado tem que ser aprova de falsificação, então o cartório assina esse documento que diz que essa chave pública é sua.  

Na prática não usamos **chave privada e pública**.  
Usamos **chave privada e certificado digital**.  
O certificado digital que contém a sua chave pública.  

Obs: Cartório é uma entidade que você tem que comparecer físicamente para provar que você é você e com isso gerar um certificado.  

# Referências
Algoritmo de chave pública: https://en.wikipedia.org/wiki/RSA_(cryptosystem)  
Algoritmo de messa digest: https://en.wikipedia.org/wiki/MD5   
Algoritmo de messa digest: https://en.wikipedia.org/wiki/SHA-1
