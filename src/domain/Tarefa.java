package domain;

import java.time.LocalDate;

public class Tarefa {
    
    private String titulo;
    private LocalDate prazo;
    private StatusTarefa status;
    
    // Construtor: A "receita" para criar uma nova Tarefa.
    // O status sempre começa como PENDENTE.
    public Tarefa(String titulo, LocalDate prazo) {
        this.titulo = titulo;
        this.prazo = prazo;
        this.status = StatusTarefa.PENDENTE; // Valor padrão
    }

    // --- Métodos Getters (para ler os dados) ---

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    // --- Métodos Setters (para alterar os dados) ---
    
    // O único campo que queremos permitir alteração é o status
    public void setStatus(StatusTarefa status) {
        this.status = status;
    }
}
