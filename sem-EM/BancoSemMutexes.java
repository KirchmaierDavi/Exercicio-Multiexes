

class ContaBanco {
    private int saldo = 1000; // inicia classe banco com saldo inicial

    public void deposito(int quantia) { //Funcao responsavel por realizar o deposito de uma quantia
        saldo += quantia;
    }

    public void retirada(int quantia) { //Funcao responsavel por sacar certa quantia da conta
        if (saldo >= quantia) {
            saldo -= quantia;
        } else if (saldo == 0) {
            System.out.println("seu saldo está zerado, retirada impossível");
        }
    }

    public int getSaldo() {
        return saldo;
    }
}

