package pi2.medTime.model;

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
    private int dosagem;//comprimidos
    private int duracao;//dias
    private int frequencia;//numero de vezes ao dia
    private LocalTime horario;//horario para tomar o medicamento
    private int qnt_frequencia_restantes;

}
