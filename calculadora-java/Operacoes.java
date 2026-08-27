public class Operacoes {

    public double somar(double numero1, double numero2) {
        return numero1 + numero2;
    }

    public double subtrair(double numero1, double numero2) {
        return numero1 - numero2;
    }

    public double multiplicar(double numero1, double numero2) {
        return numero1 * numero2;
    }

    public double dividir(double numero1, double numero2) {
        if (numero2 == 0) {
            throw new IllegalArgumentException("Não é possível dividir por zero.");
        }
        return numero1 / numero2;
    }
}

// Fim da classe Operacoes.
