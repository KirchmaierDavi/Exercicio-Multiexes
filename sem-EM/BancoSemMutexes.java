import java.util.Random;

class ContaBanco {
    private int saldo = 1000; // inicia classe banco com saldo inicial

    public void deposito(int quantia) { //Funcao responsavel por realizar o deposito de uma quantia
        saldo += quantia;
        System.out.println("Depositado: " + quantia + ", Saldo atual: " + saldo);
    }

    public void retirada(int quantia) { //Funcao responsavel por sacar certa quantia da conta
        if (saldo >= quantia) {
            saldo -= quantia;
            System.out.println("Retirado: " + quantia + ", Saldo atual: " + saldo);
        } else {
            System.out.println("Tentativa de retirada de " + quantia + " falhou. Saldo atual: " + saldo);
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

        for (int i = 0; i < threads.length; i++) { // vão ser executadas todas as 10 threads armazenadas no array

            threads[i] = new Thread(() -> { //Cria e inicia cada uma das 10 threads dentro da array

                for (int j = 0; j < 1000; j++) { // cada thread vai executar 1000 operações de retirada ou inserção de dinheiro
                    // 50% de chance para depósito ou retirada

                    if (random.nextBoolean()) {
                        conta.deposito(10);
                    } else {
                        conta.retirada(10);
                    }

                }

            });
            threads[i].start(); //Inicia a thread 
        }

        for (Thread thread : threads) { //Aguarda a execução de todas as threads
            thread.join();
        }

        System.out.println("Saldo final: " + conta.getSaldo());
    }
}


