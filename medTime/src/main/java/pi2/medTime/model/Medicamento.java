package pi2.medTime.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private int dosagem; // comprimidos
    private int duracao; // dias
    private int frequencia; // número de vezes ao dia
    private LocalTime horario; // horário para tomar o medicamento

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Nome da chave estrangeira na tabela "medicamentos"
    @JsonBackReference
    private Usuario usuario; // Relacionamento com Usuario

    public Medicamento(Long id, String nome, String descricao, int dosagem, int duracao, int frequencia, LocalTime horario) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.dosagem = dosagem;
    this.duracao = duracao;
    this.frequencia = frequencia;
    this.horario = horario;
    }
}
