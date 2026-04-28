package com.exemplo.demo;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner carregarDados(AlunoRepository repository) {
        return (args) -> {
            repository.save(new Aluno("Jonatan", "rm566998", "26/02/2006"));
        };
    }

    @GetMapping("/alunos")
    public List<Aluno> listarAlunos(AlunoRepository repository) {
        return repository.findAll();
    }
}

@Entity
class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String rm;
    public String dataNascimento;
    
    public Aluno() {}
    public Aluno(String nome, String rm, String dataNascimento) {
        this.nome = nome;
        this.rm = rm;
        this.dataNascimento = dataNascimento;
    }
}

interface AlunoRepository extends JpaRepository<Aluno, Long> {}