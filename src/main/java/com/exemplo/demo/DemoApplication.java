package com.exemplo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Inserindo seus dados automaticamente ao subir a aplicação
    @Bean
    public CommandLineRunner carregarDados(AlunoRepository repository) {
        return (args) -> {
            repository.save(new Aluno("Jonatan", "rm566998", "26/02/2006"));
        };
    }
}

// 1. Classe Aluno com os atributos corretos
@Entity
class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String rm;
    private String dataNascimento;

    public Aluno() {}

    public Aluno(String nome, String rm, String dataNascimento) {
        this.nome = nome;
        this.rm = rm;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters obrigatórios para o Jackson (Postman) funcionar
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRm() { return rm; }
    public void setRm(String rm) { this.rm = rm; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
}

interface AlunoRepository extends JpaRepository<Aluno, Long> {
}

// 2. Controladores com GET e POST
@RestController
@RequestMapping("/alunos")
class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    // 3. O método que faltava para receber os dados do Postman!
    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        return repository.save(aluno);
    }
}