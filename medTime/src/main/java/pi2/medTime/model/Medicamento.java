package pi2.medTime.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

/**
 * Entidade que representa um medicamento no sistema.
 *
 * Esta classe é usada para mapear a tabela "medicamentos" no banco de dados e
 * armazenar as informações relacionadas aos medicamentos associados aos usuários.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicamentos") // Define o mapeamento da classe para a tabela "medicamentos"
public class Medicamento {

    // Identificador único do medicamento (chave primária)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID gerado automaticamente pelo banco
    private Long id;

    // Nome do medicamento
    private String nome;

    // Descrição do medicamento
    private String descricao;

    // Dosagem recomendada (em número de comprimidos)
    private int dosagem;

    // Duração do tratamento em dias
    private int duracao;

    // Frequência de uso (número de vezes ao dia)
    private int frequencia;

    // Horário específico para tomar o medicamento
    private LocalTime horario;

    /**
     * Relacionamento muitos-para-um com a entidade `Usuario`.
     * Indica que um medicamento pertence a um único usuário.
     *
     * - A coluna "usuario_id" é usada como chave estrangeira na tabela "medicamentos".
     * - A anotação `@JsonBackReference` evita a serialização cíclica no retorno de dados em APIs.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Define a coluna de chave estrangeira no banco
    @JsonBackReference // Previne loops de serialização no JSON (complementa @JsonManagedReference no Usuario)
    private Usuario usuario;

    /**
     * Construtor personalizado (sem o campo `usuario`).
     *
     * @param id Identificador do medicamento
     * @param nome Nome do medicamento
     * @param descricao Descrição do medicamento
     * @param dosagem Dosagem recomendada (em comprimidos)
     * @param duracao Duração do tratamento (em dias)
     * @param frequencia Frequência diária (quantidade de vezes ao dia)
     * @param horario Horário específico para o uso do medicamento
     */
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
