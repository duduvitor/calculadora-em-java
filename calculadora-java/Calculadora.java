import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Calculadora extends JFrame {

    private final JTextField campoNumero1;
    private final JTextField campoNumero2;
    private final JLabel rotuloResultado;
    private final Operacoes operacoes;
    private final DecimalFormat formato = new DecimalFormat("0.##########");

    public Calculadora() {
        operacoes = new Operacoes();

        setTitle("Calculadora Java");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        rotuloResultado = new JLabel("Resultado: -", SwingConstants.CENTER);
        rotuloResultado.setFont(new Font("Arial", Font.BOLD, 20));
        rotuloResultado.setForeground(new Color(0, 90, 150));

        JPanel painelEntradas = new JPanel(new GridLayout(2, 2, 8, 8));
        painelEntradas.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        painelEntradas.add(new JLabel("Primeiro número:"));
        painelEntradas.add(campoNumero1);
        painelEntradas.add(new JLabel("Segundo número:"));
        painelEntradas.add(campoNumero2);

        JButton botaoSomar = new JButton("Somar (+)");
        JButton botaoSubtrair = new JButton("Subtrair (-)");
        JButton botaoMultiplicar = new JButton("Multiplicar (*)");
        JButton botaoDividir = new JButton("Dividir (/)");

        botaoSomar.addActionListener(e -> calcular('+'));
        botaoSubtrair.addActionListener(e -> calcular('-'));
        botaoMultiplicar.addActionListener(e -> calcular('*'));
        botaoDividir.addActionListener(e -> calcular('/'));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        painelBotoes.add(botaoSomar);
        painelBotoes.add(botaoSubtrair);
        painelBotoes.add(botaoMultiplicar);
        painelBotoes.add(botaoDividir);

        JPanel painelResultado = new JPanel(new BorderLayout());
        painelResultado.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));
        painelResultado.add(rotuloResultado, BorderLayout.CENTER);

        add(painelEntradas, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(painelResultado, BorderLayout.SOUTH);
    }

    private void calcular(char operador) {
        try {
            double numero1 = lerNumero(campoNumero1.getText());
            double numero2 = lerNumero(campoNumero2.getText());
            double resultado;

            switch (operador) {
                case '+':
                    resultado = operacoes.somar(numero1, numero2);
                    break;
                case '-':
                    resultado = operacoes.subtrair(numero1, numero2);
                    break;
                case '*':
                    resultado = operacoes.multiplicar(numero1, numero2);
                    break;
                case '/':
                    resultado = operacoes.dividir(numero1, numero2);
                    break;
                default:
                    throw new IllegalArgumentException("Operação inválida.");
            }

            rotuloResultado.setText("Resultado: " + formato.format(resultado));
        } catch (NumberFormatException erro) {
            mostrarErro("Digite números válidos nos dois campos.");
        } catch (IllegalArgumentException erro) {
            mostrarErro(erro.getMessage());
        }
    }

    private double lerNumero(String texto) {
        // Permite que o usuário use vírgula ou ponto como separador decimal.
        return Double.parseDouble(texto.trim().replace(',', '.'));
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}
