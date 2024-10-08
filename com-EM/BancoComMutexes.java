import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ContaBanco {
    private int saldo = 1000; // inicia classe banco com saldo inicial
    private final Lock lock = new ReentrantLock(); // cria uma variavel para adquirir o estado e verificar se pode seguir ou não
    
    // função que deposita dinheiro
    public synchronized void deposito(int quantia) { //Os metodos synchronized garantem que uma thread vai acessar a classe por vez

    lock.lock(); // garante que o semafóro esteja fechado para execução 

    try {

        System.out.println(Thread.currentThread().getName() + " - Saldo antes do depósito: " + saldo);

        saldo += quantia;

        System.out.println(Thread.currentThread().getName() + " - Depositado: " + quantia + ", Saldo atual: " + saldo);

    } finally {

        lock.unlock(); // Libera o semafóro para proxima execução

    }
}
    //Funcao responsavel por sacar certa quantia da conta
    public synchronized void retirada(int quantia) { //Os metodos synchronized garantem que uma thread vai acessar a classe por vez

        lock.lock(); // garante que o semafóro esteja fechado para execução 

        try {

            if (saldo >= quantia) {

                saldo -= quantia;

                System.out.println(Thread.currentThread().getName() + " - Retirado: " + quantia + ", Saldo atual: " + saldo);

            } else {

                System.out.println(Thread.currentThread().getName() + " - Tentativa de retirada de " + quantia + " falhou. Saldo atual: " + saldo);

            }
        } finally {

            lock.unlock(); // Libera o semafóro para proxima execução

        }
    }

    public synchronized int getSaldo() { //Os metodos synchronized garantem que uma thread vai acessar a classe por vez

        lock.lock(); // garante que o semafóro esteja fechado para execução 

        try {

            return saldo;

        } finally {

            lock.unlock(); // Libera o semafóro para proxima execução

        }
    }
}

public class BancoComMutexes {
    public static void main(String[] args) throws InterruptedException {

        ContaBanco Conta = new ContaBanco();
        Thread[] threads = new Thread[10]; //Array de armazenamento das threads
        Random random = new Random();

        for (int i = 0; i < threads.length; i++) { // vão ser executadas todas as 10 threads armazenadas no array

            threads[i] = new Thread(() -> { //Cria e inicia cada uma das 10 threads dentro da array

                for (int j = 0; j < 1000; j++) { // Para cada threads serão executados 1000 operações na conta do banco
                    
                    // 50% de chance para depósito ou retirada
                   
                    if (random.nextBoolean()) {
                        Conta.deposito(10);
                    } else {
                        Conta.retirada(10);
                    }

                    //Esse try vai criar um atraso de execução entre as threads, a ideia é aumentar a chance de conflitos entre as threads, aumentando a taxa de erro
                    try {

                        Thread.sleep(random.nextInt(10)); // Atraso aleatório

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();
                        
                    }


                }

            });
            threads[i].start(); // inicia thread
        }

        for (Thread thread : threads) { //Aguarda a execução de todas as threads
            thread.join();
        }

        System.out.println("Saldo final: " + Conta.getSaldo());
    }
}
