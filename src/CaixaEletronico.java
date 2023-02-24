import java.sql.SQLOutput;
import java.util.Scanner;

class Conta {
    private double saldo;
    private String extrato;

    public Conta(double SaldoInicial){
        saldo = SaldoInicial;
        extrato ="";
    }
    public  double ObterSaldo(){
        return saldo;
    }
    public String ObterExtrato(){
        return extrato;
    }
    public  boolean Sacar(double valor){
        if(valor > saldo){
            return false;
        }else {
            saldo -= valor;
            extrato += "-Saque de " + valor + "\n";
            return true;
        }
    }
    public void Depositar(double valor){
        saldo += valor;
        extrato += "-Deposito de " + valor + "\n";
    }

    public boolean Transferir(double valor, Conta outraConta){
        if(valor > saldo){
            return false;
        }else {
            saldo-=valor;
            outraConta.Depositar(valor);
            extrato += "-Transferencia de " +valor + " para conta #" +outraConta.hashCode()
                + "\n";
            return  true;

        }
    }
}
public class CaixaEletronico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conta conta = new Conta(1000);
        boolean run = true;

        while (run) {
            System.out.println("Selecione a operação desejada:\n1-Saldo em conta corrente" +
                    "\n2-Extrato bancario\n3-Sacar dinheiro\n4-Transferencia\n5-Encerrar\n");
            int opcao = scanner.nextInt();
            switch (opcao){
                case 1:
                    System.out.println("Saldo em conta corrente: R$ " + conta.ObterSaldo());
                    break;
                case 2:
                    System.out.println("Extrato bancario:\n"+conta.ObterExtrato());
                    break;
                 case 3:
                     System.out.println("Quanto voce quer sacar?");
                     double ValorSaque = scanner.nextDouble();
                     if(conta.Sacar(ValorSaque)){
                         System.out.println("Saque efeituado com sucesso");
                     }else{
                         System.out.println("Saldo insuficiente");
                     }
                     break;
                case 4:
                    System.out.println("Quanto voce quer transferir?");
                    double ValorTrans = scanner.nextDouble();
                    System.out.println("Digite o numero da conta para transferir:\n");
                    int NumeroConta = scanner.nextInt();
                    Conta outraConta = new Conta(0);
                    if(conta.Transferir(ValorTrans,outraConta)){
                        System.out.println("Transferecia efetuada com sucesso para conta #" + outraConta.hashCode());
                    }else{
                        System.out.println("Saldo insuficiente");
                    }
                    break;
                case 5:
                    run = false;
                    System.out.println("Caixa encerrado obrigado por ultilizar nosso caixa do banco Lisboa");
                    break;
            }
        }
    }
}