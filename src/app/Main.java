package app;

import domain.Disciplina;
import domain.StatusTarefa;
import domain.Tarefa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

                case 4 ->
                    listarTarefasPorDisciplinas();

                case 5 ->
                    atualizarStatus();

                case 6 -> 
                    exibirDashboard();

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
                4. Listar tarefas por disciplina
                5. Atualizar status de tarefa
                6. Exibir Dashboard 
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

    private static void listarTarefasPorDisciplinas() {
     
        if (disciplinas.isEmpty()) {
            System.out.println("\nNão há disciplinas cadastradas para listar tarefas!");
            return;
        } 

        System.out.println("\n---- LISTAR TAREFAS POR DISCIPLINA ----");
        System.out.println("Você deseja ver as tarefas de qual disciplina?");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println(i+1 + ". " + disciplinas.get(i).getNome());
        }
        int disciplinaEscolhida = scanner.nextInt();
        scanner.nextLine();

        if (disciplinaEscolhida < 1 || disciplinaEscolhida > disciplinas.size()) {
            System.out.println("Opção inválida! Tente novamente.");
            return;
        }

        Disciplina disciplina = disciplinas.get(disciplinaEscolhida - 1);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (Tarefa tarefa : disciplina.getTarefas()) {
            System.out.println("\nTítulo: " + tarefa.getTitulo() + "\nPrazo: " + tarefa.getPrazo().format(formatador) + "\nStatus: " + tarefa.getStatus());
        }    
    }

    private static void atualizarStatus() {
        
        if (disciplinas.isEmpty()) {
            System.out.println("\nNão há disciplinas cadastradas para mudar o status de tarefas!");
            return;
        } 

        System.out.println("\n------- ATUALIZAR STATUS -------");
        System.out.println("Em qual disciplina você deseja mudar o status da tarefa?");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println(i+1 + ". " + disciplinas.get(i).getNome());
        }
        int disciplinaEscolhida = scanner.nextInt();
        scanner.nextLine();

        if (disciplinaEscolhida < 1 || disciplinaEscolhida > disciplinas.size()) {
            System.out.println("Opção inválida! Tente novamente.");
            return;
        }

        Disciplina disciplina = disciplinas.get(disciplinaEscolhida - 1);
        List<Tarefa> tarefasDaDisciplina = disciplina.getTarefas();

        if (tarefasDaDisciplina.isEmpty()) {
            System.out.println("Essa disciplina não possui tarefas!");
            return;
        }

        System.out.println("Você quer mudar o status de qual tarefa?");
        for (int i = 0; i < tarefasDaDisciplina.size(); i++) {
            System.out.println(i+1 + ". " + tarefasDaDisciplina.get(i).getTitulo() + " (" + disciplina.getTarefas().get(i).getStatus() + ")");
        }
        
        int tarefaEscolhida = scanner.nextInt();
        scanner.nextLine();

        if (tarefaEscolhida < 1 || tarefaEscolhida > tarefasDaDisciplina.size()) {
            System.out.println("Tarefa inexistente! Tente novamente.");
            return;
        }

        Tarefa tarefa = tarefasDaDisciplina.get(tarefaEscolhida - 1);

        StatusTarefa[] statusPossiveis = StatusTarefa.values();
        System.out.println("Status possíveis:");
        for (int i = 0; i < statusPossiveis.length; i++) {
            System.out.println(i + " - " + statusPossiveis[i]);
        }
    
        System.out.print("Digite o número do novo status: ");
        int statusEscolhido = scanner.nextInt();
        scanner.nextLine();
        
        if (statusEscolhido < 0 || statusEscolhido >= statusPossiveis.length) {
            System.out.println("Opção inválida!");
            return;
        }
        StatusTarefa novoStatus = statusPossiveis[statusEscolhido];
        tarefa.setStatus(novoStatus);
        System.out.println("Status atualizado com sucesso!");
    }
    
    private static void exibirDashboard() {
        System.out.println("\n--- DASHBOARD DE ESTUDOS ---");

        if (disciplinas.isEmpty()) {
            System.out.println("Nenhum dado para gerar estatísticas.");
            return;
        }

        int totalTarefasDoSistema = 0;

        for(Disciplina disciplina : disciplinas) {
            int pendentes = 0;
            int emProgresso = 0;
            int concluidas = 0;

            for (Tarefa tarefa : disciplina.getTarefas()) {
                totalTarefasDoSistema++;
                switch (tarefa.getStatus()) {
                    case PENDENTE -> pendentes++;
                    case EM_PROGRESSO -> emProgresso++;
                    case CONCLUIDA -> concluidas++;
                }
            }
            System.out.println(disciplina.getNome().toUpperCase());
            System.out.println("   - Pendentes: " + pendentes);
            System.out.println("   - Em Progresso: " + emProgresso);
            System.out.println("   - Concluídas: " + concluidas);
            System.out.println("-----------------------------");
        }
        System.out.println("TOTAL GERAL DE TAREFAS NO SISTEMA: " + totalTarefasDoSistema);
    }
}