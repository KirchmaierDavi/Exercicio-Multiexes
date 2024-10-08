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

