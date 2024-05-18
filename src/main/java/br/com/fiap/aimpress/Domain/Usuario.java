package br.com.fiap.aimpress.Domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JV_USUARIO")
@SequenceGenerator(name="seq_usuario", sequenceName="seq_JV_usuario", allocationSize=1)
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nm_usuario", nullable = false, length = 50)
    private String name;

    @Column(name = "email_usuario", nullable = false, length = 70)
    private String email;

    @Column(name="dt_nascimento")
    private LocalDate dataNascimento;

}
