package app;

// 1. Importamos as classes que criamos no outro pacote
import domain.Disciplina;
import domain.Tarefa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        boolean executando = true;
    
        while (executando)  {
            menu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                
                case 1 -> 
                    cadastrarDisciplina();
                
                case 2 ->
                    cadastrarTarefa();
            
                case 3 ->
                    listarDisciplinas();

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

    private static void menu() {
        String menu = """
                --------- STUDY TRACKER ---------
                1. Cadastrar disciplina
                2. Cadastrar tarefa
                3. Listar disciplinas
                0. Sair
                Escolha uma opção:\s""";
        System.out.print(menu);
    }

    private static void cadastrarDisciplina() {
        System.out.println("\n------ CADASTRAR DISCIPLINA ------");

        System.out.print("Digite o nome da disciplina: ");
        String nomeDisciplina = scanner.nextLine();

        System.out.print("Digite o nome do professor: ");
        String professor = scanner.nextLine();

        Disciplina disciplina = new Disciplina(nomeDisciplina, professor);
        disciplinas.add(disciplina);

        System.out.println("Disciplina cadastrada com sucesso!");
        System.out.println("----------------------------------");
    }

    private static void cadastrarTarefa() {

        if (disciplinas.isEmpty()) {
            System.out.println("\nNão há disciplinas cadastradas para adicionar tarefas!");
            return;
        } 

        System.out.println("\n------- CADASTRAR TAREFA -------");
        System.out.println("Em qual disciplina você deseja adicionar uma tarefa?");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println(i+1 + ". " + disciplinas.get(i).getNome());
        }
        int disciplinaEscolhida = scanner.nextInt();
        scanner.nextLine();

        if (disciplinaEscolhida < 1 || disciplinaEscolhida > disciplinas.size()) {
            System.out.println("Opção inválida! Tente novamente.");
            return;
        }

        System.out.print("Digite qual é a tarefa: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o prazo (formato AAAA-MM-DD, ex: 2024-12-31): ");
        
        try {
            LocalDate data = LocalDate.parse(scanner.nextLine());
            disciplinas.get(disciplinaEscolhida - 1).adicionarTarefa(new Tarefa(titulo, data));
            System.out.println("Tarefa adicionada com sucesso!");
        }  catch (Exception e) {
            System.out.println("Erro: Data inválida. Use o formato AAAA-MM-DD.");
        }
    }

    private static void listarDisciplinas() { // por enquanto, lista apenas disciplinas
        if (disciplinas.isEmpty()) {
            System.out.println("\nNenhuma disciplina cadastrada ainda!");
        } else {
            System.out.println("\n------ LISTA DE DISCIPLINAS ------");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("Disciplina: " + disciplina.getNome());
                System.out.println("Professor: " + disciplina.getProfessor());
                System.out.println("Quantidade de tarefas pendentes: " + disciplina.getTarefas().size());
            }
        }
        System.out.println("----------------------------------");
    }
}
