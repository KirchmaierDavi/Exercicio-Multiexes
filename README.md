# Exercicio-Multiexes
Exercício com objetivo de demonstrar os problemas da falta de utilização de multiexes e semáforos em uma implementação.

Para exemplificação, utilizarem um sistema de banco, onde uma conta poderá receber entradas e retiradas de valores.

A ideia e mostrar como a falta de sincronização entre as threads podem causar inconsistencias nos dados.

Relátorio:

Código sem Mutexes e semaforos:

    -É perceptivel que durante a execução do código, as operações de retirada e deposito realizadas concomitantemente pelas threads fazem com que o valor do saldo seja diferente do esperado, pois 2 threads podem ler o mesmo saldo ao mesmo tempo e fazer com que o valor final seja diferente do esperado, gerando assim uma condição de corrida, gerando assim inconsistencia no saldo final.

Código com Mutexes e semaforos:

    -A execução do código com semaforos e mutexes garantiu que o saldo fosse acessado apenas por uma thread por vez, o que garantiu que o valor do saldo fosse igual ao valor esperado, garantindo a integridade dos dados e fazendo com que o valor final seja exatamento o esperado, sem gerar nenhuma condição de corrida e sem gerar inconsistencias no valor do saldo final.


