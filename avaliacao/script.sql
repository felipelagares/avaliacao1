-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS avaliacao1;

-- Usar o banco de dados recém-criado
USE avaliacao1;

-- Criar a tabela curso
CREATE TABLE IF NOT EXISTS curso (
    iden INT PRIMARY KEY,
    ano INT NOT NULL,
    nome VARCHAR(255) NOT NULL
);

-- Criar a tabela disciplina
CREATE TABLE IF NOT EXISTS disciplina (
    nome VARCHAR(255) PRIMARY KEY,
    ch INT NOT NULL,
    iden_curso INT,
    FOREIGN KEY (iden_curso) REFERENCES curso(iden)
);
