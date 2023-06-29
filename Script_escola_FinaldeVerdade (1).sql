-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29/06/2023 às 19:06
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.0.28

CREATE DATABASE escola;

USE escola;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `escola`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `aluno`
--

CREATE TABLE `aluno` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `aluno`
--

INSERT INTO `aluno` (`cpf`, `nome`, `dataNascimento`, `email`) VALUES
('11122233344', 'Pedro Oliveira Banana', '1997-09-15', 'pedro.oliveira10@example.com'),
('11122233399', 'Laura Almeida', '2001-06-30', 'laura.almeida@example.com'),
('12345678900', 'João Silva', '2000-01-01', 'joao.silva@example.com'),
('12664116746', 'Caio Ferreira', '1989-04-10', 'caio@gmail'),
('22233344411', 'Renato Silva', '1997-01-25', 'renato.silva@example.com'),
('22233344455', 'Rafael Santos', '2002-10-03', 'rafael.santos@example.com'),
('33344455522', 'Carolina Oliveira', '1999-07-30', 'carolina.oliveira@example.com'),
('33344455566', 'Fernanda Almeida', '1995-11-30', 'fernanda.almeida@example.com'),
('44455566677', 'Gustavo Silva', '2003-12-05', 'gustavo.silva@example.com'),
('55566677722', 'Lucas Ferreira', '2004-09-12', 'lucas.ferreira@example.com'),
('55566677788', 'Ana Ferreira', '2001-02-20', 'ana.ferreira@example.com'),
('66677788811', 'Vanessa Almeida', '1995-05-12', 'vanessa.almeida@example.com'),
('66677788899', 'Juliana Oliveira', '2004-08-17', 'juliana.oliveira@example.com'),
('77788899900', 'Mariana Sousa', '2003-04-25', 'mariana.sousa@example.com'),
('77788899911', 'Luisa Oliveira', '2002-08-15', 'luisa.oliveira@example.com'),
('77788899981', 'Beatriz Santos', '2002-03-25', 'beatriz.santos@example.com'),
('88899900022', 'Ricardo Santos', '2001-03-20', 'ricardo.santos@example.com'),
('98765432100', 'Maria Santos', '1998-05-10', 'maria.santos@example.com'),
('99900011122', 'Camila Ferreira', '2003-11-10', 'camila.ferreira@example.com'),
('99988877744', 'Rodrigo Sousa', '2000-11-10', 'rodrigo.sousa@example.com'),
('99988877766', 'Carlos Rodrigues', '1997-07-12', 'carlos.rodrigues@example.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `disciplina`
--

CREATE TABLE `disciplina` (
  `id` varchar(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `cpfAluno` varchar(11) DEFAULT NULL,
  `cpfProfessor` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `disciplina`
--

INSERT INTO `disciplina` (`id`, `nome`, `descricao`, `cpfAluno`, `cpfProfessor`) VALUES
('1', 'Matemática Avançada', 'Matemática avançada para nível universitário 2', '12345678900', '55566677788'),
('2', 'História do Brasil', 'Estudo da história do Brasil 2', '98765432100', '99988877766'),
('3', 'Química Orgânica', 'Estudo da química orgânica', '11122233344', '33344455566'),
('4', 'Física Moderna', 'Estudo da física moderna', '55566677788', '11122233300'),
('5', 'Inglês Avançado', 'Aprendizado avançado da língua inglesa', '99988877766', '66677788800');

-- --------------------------------------------------------

--
-- Estrutura para tabela `matricula`
--

CREATE TABLE `matricula` (
  `idAluno` varchar(11) DEFAULT NULL,
  `idDisciplina` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `matricula`
--

INSERT INTO `matricula` (`idAluno`, `idDisciplina`) VALUES
('12345678900', '1'),
('98765432100', '1'),
('11122233344', '1'),
('55566677788', '1'),
('99988877766', '2'),
('33344455566', '2'),
('77788899900', '2'),
('22233344455', '2'),
('66677788899', '3'),
('44455566677', '3'),
('11122233399', '3'),
('55566677722', '3'),
('77788899981', '4'),
('99988877744', '4'),
('33344455522', '4'),
('22233344411', '4'),
('66677788811', '5'),
('77788899911', '5'),
('88899900022', '5'),
('99900011122', '5');

-- --------------------------------------------------------

--
-- Estrutura para tabela `professor`
--

CREATE TABLE `professor` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `professor`
--

INSERT INTO `professor` (`cpf`, `nome`, `dataNascimento`, `email`) VALUES
('11122233300', 'Mariana Sousa bonita', '1989-04-25', 'mariana.sousa7@example.com'),
('33344455566', 'Fernanda Almeida', '1988-11-30', 'fernanda.almeida@example.com'),
('55566677788', 'Carlos Ferreira', '1975-03-20', 'carlos.ferreira@example.com'),
('66677788800', 'Gustavo Silva', '1980-12-05', 'gustavo.silva@example.com'),
('99988877766', 'Ana Rodrigues', '1982-07-12', 'ana.rodrigues@example.com');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`cpf`);

--
-- Índices de tabela `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `disciplina_ibfk_2` (`cpfProfessor`) USING BTREE,
  ADD KEY `disciplina_ibfk_1` (`cpfAluno`);

--
-- Índices de tabela `matricula`
--
ALTER TABLE `matricula`
  ADD KEY `idAluno` (`idAluno`),
  ADD KEY `idDisciplina` (`idDisciplina`);

--
-- Índices de tabela `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`cpf`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `disciplina`
--
ALTER TABLE `disciplina`
  ADD CONSTRAINT `disciplina_ibfk_1` FOREIGN KEY (`cpfAluno`) REFERENCES `aluno` (`cpf`) ON DELETE CASCADE,
  ADD CONSTRAINT `disciplina_ibfk_2` FOREIGN KEY (`cpfProfessor`) REFERENCES `professor` (`cpf`) ON DELETE CASCADE;

--
-- Restrições para tabelas `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `matricula_ibfk_1` FOREIGN KEY (`idAluno`) REFERENCES `aluno` (`cpf`) ON DELETE CASCADE,
  ADD CONSTRAINT `matricula_ibfk_2` FOREIGN KEY (`idDisciplina`) REFERENCES `disciplina` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
