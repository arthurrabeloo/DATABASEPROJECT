package br.inatel;

import br.inatel.DAO.*;
import br.inatel.Model.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testando Conexão com o Banco de Dados ===");

        // Teste de Cursos
        CursoDAO cursoDAO = new CursoDAO();
        ArrayList<Curso> cursos = cursoDAO.selectAll();
        for (Curso c : cursos) {
            System.out.println("Curso: " + c.nome + " | Descrição: " + c.descricao);
        }

        // Teste de Alunos
        AlunoDAO alunoDAO = new AlunoDAO();
        ArrayList<Aluno> alunos = alunoDAO.selectAll();
        for (Aluno a : alunos) {
            System.out.println("Aluno: " + a.nome + " | Email: " + a.email);
        }

        // Teste de Professores
        ProfessorDAO professorDAO = new ProfessorDAO();
        ArrayList<Professor> professores = professorDAO.selectAll();
        for (Professor p : professores) {
            System.out.println("Professor: " + p.nome + " | Email: " + p.email);
        }

        // Teste de Disciplinas
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ArrayList<Disciplina> disciplinas = disciplinaDAO.selectAll();
        for (Disciplina d : disciplinas) {
            System.out.println("Disciplina: " + d.nome);
        }

        // Teste de Aulas
        AulaDAO aulaDAO = new AulaDAO();
        ArrayList<Aula> aulas = aulaDAO.selectAll();
        for (Aula a : aulas) {
            System.out.println("Aula: " + a.horario + " | Data: " + a.data);
        }

        // Teste de Relacionamento Aluno ↔ Professor
        AlunoProfessorDAO apDAO = new AlunoProfessorDAO();
        ArrayList<AlunoProfessor> aps = apDAO.selectAll();
        for (AlunoProfessor ap : aps) {
            System.out.println("Aluno ID: " + ap.alunoId + " - Professor ID: " + ap.professorId);
        }

        // Teste da Procedure
        System.out.println("\n=== Alunos do Curso ID 1 ===");
        ArrayList<Aluno> alunosCurso = alunoDAO.listarAlunosPorCurso(1);
        for (Aluno a : alunosCurso) {
            System.out.println("Aluno: " + a.nome + " | Email: " + a.email);
        }
    }
}