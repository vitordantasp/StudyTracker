package app;

// 1. Importamos as classes que criamos no outro pacote
import domain.Disciplina;
import domain.Tarefa;
import java.time.LocalDate; // Precisamos importar o LocalDate do Java

public class Main {
    public static void main(String[] args) {
        
        Disciplina java = new Disciplina("Java", "Prof. Aristóteles");
        Tarefa estudar = new Tarefa("Estudar POO", LocalDate.of(2025, 11, 18));
        
        java.adicionarTarefa(estudar);
        System.out.println("Nome da disciplina: " + java.getNome());
        System.out.println("Título da tarefa: " + estudar.getTitulo());
        System.out.println("Título da tarefa: " + java.getTarefas().get(0).getTitulo());
        System.out.println("Prazo da tarefa: " + java.getTarefas().get(0).getPrazo());

    }
}
