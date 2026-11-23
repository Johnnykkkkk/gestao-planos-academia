import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PlanoService service = new PlanoService();

    public static void main(String[] args) {
        service.cadastrarMensal("Plano Mensal", 100.00, 20.00);
        service.cadastrarAnual("Plano Anual", 1200.00, 10.0);

        int op;
        do {
            System.out.println("\n===== GESTÃO DE PLANOS =====");
            System.out.println("1 - Cadastrar mensal");
            System.out.println("2 - Cadastrar anual");
            System.out.println("3 - Listar planos");
            System.out.println("4 - Consultar por ID");
            System.out.println("5 - Editar nome e valor-base");
            System.out.println("6 - Desativar plano");
            System.out.println("0 - Sair");
            op = inteiro("Escolha: ");

            try {
                switch (op) {
                    case 1:
                        System.out.println(service.cadastrarMensal(
                            texto("Nome: "), decimal("Valor-base: R$ "),
                            decimal("Taxa de adesão: R$ ")).exibirDados());
                        break;
                    case 2:
                        System.out.println(service.cadastrarAnual(
                            texto("Nome: "), decimal("Valor-base anual: R$ "),
                            decimal("Desconto (0 a 30%): ")).exibirDados());
                        break;
                    case 3:
                        if (service.listarPlanos().isEmpty()) System.out.println("Nenhum plano cadastrado.");
                        for (Plano p : service.listarPlanos()) System.out.println(p.exibirDados());
                        break;
                    case 4:
                        mostrar(inteiro("ID: "));
                        break;
                    case 5:
                        service.editarPlano(inteiro("ID: "), texto("Novo nome: "),
                            decimal("Novo valor-base: R$ "));
                        System.out.println("Plano atualizado.");
                        break;
                    case 6:
                        service.desativarPlano(inteiro("ID: "));
                        System.out.println("Plano desativado.");
                        break;
                    case 0:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (op != 0);
        sc.close();
    }

    private static void mostrar(int id) {
        Plano p = service.buscarPlano(id);
        if (p == null) System.out.println("Plano não encontrado.");
        else {
            System.out.println(p.exibirDados());
            if (p instanceof PlanoMensal)
                System.out.printf("Taxa de adesão: R$ %.2f%n", ((PlanoMensal)p).getTaxaAdesao());
            if (p instanceof PlanoAnual)
                System.out.printf("Desconto: %.1f%%%n", ((PlanoAnual)p).getPercentualDesconto());
        }
    }

    private static String texto(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    private static int inteiro(String msg) {
        while (true) {
            try { return Integer.parseInt(texto(msg).trim()); }
            catch (NumberFormatException e) { System.out.println("Digite um número inteiro válido."); }
        }
    }

    private static double decimal(String msg) {
        while (true) {
            try { return Double.parseDouble(texto(msg).trim().replace(",", ".")); }
            catch (NumberFormatException e) { System.out.println("Digite um valor numérico válido."); }
        }
    }
}
