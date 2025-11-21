package domain;

import java.util.List;
import java.util.ArrayList;

public class Disciplina {

    private String nomeDisciplina;
    private String professor;
    private List<Tarefa> tarefas;
 
    public Disciplina(String nomeDisciplina, String professor) {
        this.nomeDisciplina = nomeDisciplina;
        this.professor = professor;
        // Importante: Inicializamos a lista como uma implementação concreta!
        this.tarefas = new ArrayList<>(); // O ArrayList
    }

    // --- Métodos de Acesso (Getters) ---
    
    public String getNome() {
        return nomeDisciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    // --- Métodos de Comportamento ---
    
    // Um método para "fazer" algo (adicionar uma tarefa na lista)
    public void adicionarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }
}