package app;

// 1. Importamos as classes que criamos no outro pacote
import domain.Disciplina;
import domain.Tarefa;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;
        
        while (executando)  {
            menu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                
                case 1 ->
                    System.out.println(">> (em breve) Cadastrar disciplina");

                case 2 ->
                    System.out.println(">> (em breve) Cadastrar tarefa");
            
                case 3 ->
                    System.out.println(">> (em breve) Listar tudo");

                case 0 -> {
                    System.out.println("Saindo...");
                    executando = false;
                }
                default ->
                    System.out.println("Opção inválida!");
            }
            System.out.println();
        }

        scanner.close();

    }
    
    public static void menu() {

        String menu = """
                ---- STUDY TRACKER ----
                1. Cadastrar disciplina
                2. Cadastrar tarefa
                3. Listar tudo
                0. Sair
                Escolha uma opção:\s""";
        System.out.print(menu);

    }

}
