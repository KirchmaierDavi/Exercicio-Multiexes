import java.util.Random;

class ContaBanco {
    private int saldo = 1000; // inicia classe banco com saldo inicial

    public void deposito(int quantia) { //Funcao responsavel por realizar o deposito de uma quantia
        
        System.out.println(Thread.currentThread().getName() + " - Saldo antes do depósito: " + saldo);

        saldo += quantia;

        System.out.println(Thread.currentThread().getName() + " - Depositado: " + quantia + ", Saldo atual: " + saldo);
    }

    public void retirada(int quantia) { //Funcao responsavel por sacar certa quantia da conta
        if (saldo >= quantia) {

            saldo -= quantia;

            System.out.println(Thread.currentThread().getName() + " - Retirado: " + quantia + ", Saldo atual: " + saldo);

        } else {

            System.out.println(Thread.currentThread().getName() + " - Tentativa de retirada de " + quantia + " falhou. Saldo atual: " + saldo);

        }
    }

    public int getSaldo() {
        return saldo;
    }
}

public class BancoSemMutexes {
    public static void main(String[] args) throws InterruptedException {

        ContaBanco conta = new ContaBanco();
        Thread[] threads = new Thread[10]; //Array para guardar as threads
        Random random = new Random();

        final int[] num_adicoes = {0};
        final int[] num_retiradas = {0};

        for (int i = 0; i < threads.length; i++) { // vão ser executadas todas as 10 threads armazenadas no array

            threads[i] = new Thread(() -> { //Cria e inicia cada uma das 10 threads dentro da array

                for (int j = 0; j < 1000; j++) { // cada thread vai executar 1000 operações de retirada ou inserção de dinheiro
                    // 50% de chance para depósito ou retirada

                    if (random.nextBoolean()) {

                        conta.deposito(10);

                        num_adicoes[0]++;

                    } else {

                        conta.retirada(10);

                        num_retiradas[0]++;
                    }

                    //Esse try vai criar um atraso de execução entre as threads, a ideia é aumentar a chance de conflitos entre as threads, aumentando a taxa de erro
                    try {

                        Thread.sleep(random.nextInt(10)); // Atraso aleatório

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();
                        
                    }

                }

            });
            threads[i].start(); //Inicia a thread 
        }

        for (Thread thread : threads) { //Aguarda a execução de todas as threads
            thread.join();
        }

        int totalDepositos = num_adicoes[0] * 10;
        int totalRetiradas = num_retiradas[0] * 10;

        // Calcular o valor esperado
        int valorEsperado = 1000 + totalDepositos - totalRetiradas;

        // Garante que o valor esperado não seja negativo
        if (valorEsperado < 0) {

            valorEsperado = 0;

        }

        System.out.println("Saldo final: " + conta.getSaldo());
        System.out.println("Valor esperado:" + valorEsperado);
    }
}


