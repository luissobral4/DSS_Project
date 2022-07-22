/**
 * Classe que representa o Menu da aplicação
 */
package Presentation;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner is = new Scanner(System.in);
    private List<String> opcoes;
    private int op;

    /**
     * Constructor for objects of class Menu
     */
    public Menu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
    }

    /**
     * Método para apresentar o menu e ler uma opção.
     */
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    /** Apresentar o menu */
    private void showMenu() {
        System.out.println("\n----------------------------");
        System.out.println(" Sistema de Gestao de Stock");
        System.out.println("----------------------------");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(" " + (i+1));
            System.out.print(" | ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println(" 0 | Sair");
        System.out.println("----------------------------");
    }

    /**
     * Ler uma mensagem
     *
     * @param message Mensagem a ser lida
     * @return mensagem numa só linha
     */
    public String lerString(String message) {
        String line;
        line = is.nextLine();

        do{
            System.out.println(message);
            line = is.nextLine();
        } while (line.isBlank());

        return line;
    }

    /** Ler uma opção válida */
    private int lerOpcao() {
        int op;

        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
            System.out.println(e.toString());
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }

    /**
     * Método para obter a última opção lida
     */
    public int getOpcao() {
        return this.op;
    }

    public void executaLogin() {
        System.out.println("\n *** Login *** ");

        System.out.print("Introduza password: ");
    }

    /**
     * notificação do registo de uma Palete
     *
     * @param qrCode Código QR da Palete registada
     */
    public void notRegistaPalete(String qrCode) {
        System.out.println("A palete com o Qr-Code: " + qrCode + ", foi registada com sucesso.");
    }

    /**
     * Notifica um RObot
     *
     * @param qrCode Código QR da Palete a ser recolhida
     * @param robot  Robot que recolherá a Palete
     */
    public void notRobot(String qrCode,String robot) {
        System.out.println("O robot com o robotID: " + robot + ", foi notificado para recolher a palete: " + qrCode+ ".");
    }

    /**
     * Notificação de o Robot ter recolhido ou não a Palete
     *
     * @param qrCode Código QR da Palete recolhida
     * @param robot  Robot que recolhe a Palete
     */
    public void notRecolherPalete(String qrCode,String robot) {
        System.out.println("O robot com o robotID: " + robot + ", recolheu a palete: " + qrCode+ " com sucesso.");
    }

    /**
     * Notificação de um Robot ter entergue ou não a Palete
     *
     * @param qrCode Código QR da Palete entregue
     * @param robot  Robot que enterga a Palete
     */
    public void notEntregarPalete(String qrCode,String robot) {
        System.out.println("O robot com o robotID: " + robot + ", entregou a palete: " + qrCode+ " com sucesso.");
    }

    /**
     * Imprime a Listagem das Localizações das Paletes
     *
     * @param l Lista de Localizações
     */
    public void imprimeListagem(List<String> l){
        System.out.println("\nListagem das Paletes:");
        for(String s:l)
            System.out.println(s);
    }

    /**
     * Imprime a Lista das Paletes por Notificar
     *
     * @param l Lista de Paletes
     */
    public void listaPaletesPorNotificar(List<String> l) {
        System.out.print("Paletes por notificar: ");
        for(String s : l)
            System.out.print(s + " ");
        System.out.println();
    }

    /**
     * Imprime a Lista dos Robots por Recolher
     *
     * @param l Lista de Robots
     */
    public void listaRobotsPorRecolher(List<String> l) {
        System.out.print("Robots por recolher: ");
        for(String s : l)
            System.out.print(s + " ");
        System.out.println();
    }

    /**
     * Imprime a Lista dos Robots por Recolher
     *
     * @param l Lista de Robots
     */
    public void listaRobotsPorEntregar(List<String> l) {
        System.out.print("Robots por entregar: ");
        for(String s : l)
            System.out.print(s + " ");
        System.out.println();
    }
}
