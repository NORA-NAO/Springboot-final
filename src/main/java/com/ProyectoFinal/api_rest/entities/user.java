package com.ProyectoFinal.api_rest.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 4, max = 50)
    private String name;
    @NotBlank
    @Size(min = 7, max = 50)
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 3, max = 30)
    private String password;
    @NotBlank
    @ManyToOne
    private Role rol; 
    @Transient
    private boolean admin, student, professor;
  
}
