package br.inatel;

import br.inatel.DAO.*;
import br.inatel.Model.*;

import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Inserir dados");
            System.out.println("2. Atualizar dados");
            System.out.println("3. Deletar dados");
            System.out.println("4. Buscar dados");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> menuInsert();
                case 2 -> menuUpdate();
                case 3 -> menuDelete();
                case 4 -> menuSelect();
                case 0 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }
    }

    static void menuInsert() {
        System.out.println("Tabelas disponíveis: Curso, Aluno, Professor, Disciplina, Aula");
        System.out.print("Escolha uma tabela para inserir: ");
        String tabela = sc.nextLine().toLowerCase();

        switch (tabela) {
            case "curso" -> {
                CursoDAO dao = new CursoDAO();
                Curso c = new Curso();
                System.out.print("Nome do curso: "); c.nome = sc.nextLine();
                System.out.print("Descrição: "); c.descricao = sc.nextLine();
                dao.insert(c);
            }
            case "aluno" -> {
                AlunoDAO dao = new AlunoDAO();
                Aluno a = new Aluno();
                System.out.print("Nome: "); a.nome = sc.nextLine();
                System.out.print("Email: "); a.email = sc.nextLine();
                System.out.print("Telefone: "); a.telefone = sc.nextLine();
                System.out.print("Idade: "); a.idade = sc.nextInt(); sc.nextLine();
                System.out.print("Data de nascimento (yyyy-MM-dd): ");
                try { a.data_nasc = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine()); } catch (Exception e) { e.printStackTrace(); return; }
                System.out.print("ID do curso: "); a.cursoId = sc.nextInt();
                dao.insert(a);
            }
            case "professor" -> {
                ProfessorDAO dao = new ProfessorDAO();
                Professor p = new Professor();
                System.out.print("Nome: "); p.nome = sc.nextLine();
                System.out.print("Email: "); p.email = sc.nextLine();
                System.out.print("Data nascimento: "); p.data_nasc = sc.nextLine();
                System.out.print("Telefone: "); p.telefone = sc.nextLine();
                dao.insert(p);
            }
            case "disciplina" -> {
                DisciplinaDAO dao = new DisciplinaDAO();
                Disciplina d = new Disciplina();
                System.out.print("Nome: "); d.nome = sc.nextLine();
                System.out.print("ID do curso: "); d.cursoId = sc.nextInt();
                System.out.print("ID do professor: "); d.professorId = sc.nextInt();
                dao.insert(d);
            }
            case "aula" -> {
                AulaDAO dao = new AulaDAO();
                Aula a = new Aula();
                System.out.print("Horário: "); a.horario = sc.nextLine();
                System.out.print("Data (yyyy-MM-dd): ");
                try { a.data = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine()); } catch (Exception e) { e.printStackTrace(); return; }
                System.out.print("ID da disciplina: "); a.disciplinaId = sc.nextInt();
                dao.insert(a);
            }
            default -> System.out.println("Tabela inválida.");
        }
    }

    static void menuUpdate() {
        System.out.print("\nTabela para atualizar (curso, aluno, professor): ");
        String tabela = sc.nextLine().toLowerCase();
        switch (tabela) {
            case "curso" -> {
                CursoDAO dao = new CursoDAO();
                Curso c = new Curso();
                System.out.print("ID do curso: "); c.idCursos = sc.nextInt(); sc.nextLine();
                System.out.print("Novo nome: "); c.nome = sc.nextLine();
                System.out.print("Nova descrição: "); c.descricao = sc.nextLine();
                dao.update(c);
            }
            case "aluno" -> {
                AlunoDAO dao = new AlunoDAO();
                Aluno a = new Aluno();
                System.out.print("ID do aluno: "); a.idAlunos = sc.nextInt(); sc.nextLine();
                System.out.print("Novo nome: "); a.nome = sc.nextLine();
                System.out.print("Novo email: "); a.email = sc.nextLine();
                System.out.print("Novo telefone: "); a.telefone = sc.nextLine();
                System.out.print("Nova idade: "); a.idade = sc.nextInt(); sc.nextLine();
                System.out.print("Nova data nascimento (yyyy-MM-dd): ");
                try { a.data_nasc = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine()); } catch (Exception e) { e.printStackTrace(); return; }
                System.out.print("ID novo curso: "); a.cursoId = sc.nextInt();
                dao.update(a);
            }
            case "professor" -> {
                ProfessorDAO dao = new ProfessorDAO();
                Professor p = new Professor();
                System.out.print("ID do professor: "); p.idProfessor = sc.nextInt(); sc.nextLine();
                System.out.print("Novo nome: "); p.nome = sc.nextLine();
                System.out.print("Novo email: "); p.email = sc.nextLine();
                System.out.print("Nova data nascimento: "); p.data_nasc = sc.nextLine();
                System.out.print("Novo telefone: "); p.telefone = sc.nextLine();
                dao.update(p);
            }
            default -> System.out.println("Tabela inválida.");
        }
    }

    static void menuDelete() {
        System.out.print("\nTabela para deletar (curso, aluno, professor): ");
        String tabela = sc.nextLine().toLowerCase();
        switch (tabela) {
            case "curso" -> {
                CursoDAO dao = new CursoDAO();
                System.out.print("ID do curso: ");
                int id = sc.nextInt();
                dao.delete(id);
            }
            case "aluno" -> {
                AlunoDAO dao = new AlunoDAO();
                System.out.print("ID do aluno: ");
                int id = sc.nextInt();
                dao.delete(id);
            }
            case "professor" -> {
                ProfessorDAO dao = new ProfessorDAO();
                System.out.print("ID do professor: ");
                int id = sc.nextInt();
                dao.delete(id);
            }
            default -> System.out.println("Tabela inválida.");
        }
    }

    static void menuSelect() {
        System.out.print("\nTabela para buscar (curso, aluno, professor): ");
        String tabela = sc.nextLine().toLowerCase();
        System.out.println("1. Todos os dados");
        System.out.println("2. Um único dado (por ID)");
        System.out.println("3. JOIN com outra tabela");
        System.out.print("Escolha: ");
        int op = sc.nextInt(); sc.nextLine();

        switch (tabela) {
            case "curso" -> {
                CursoDAO dao = new CursoDAO();
                if (op == 1) dao.selectAll().forEach(c -> System.out.println(c.nome));
                else if (op == 2) {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.selectAll().stream().filter(c -> c.idCursos == id).forEach(c -> System.out.println(c.nome));
                }
            }
            case "aluno" -> {
                AlunoDAO dao = new AlunoDAO();
                if (op == 1) dao.selectAll().forEach(a -> System.out.println(a.nome));
                else if (op == 2) {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.selectAll().stream().filter(a -> a.idAlunos == id).forEach(a -> System.out.println(a.nome));
                }
                else if (op == 3) {
                    System.out.print("ID do curso: ");
                    int cursoId = sc.nextInt();
                    dao.listarAlunosPorCurso(cursoId).forEach(a -> System.out.println(a.nome + " | " + a.email));
                }
            }
            case "professor" -> {
                ProfessorDAO dao = new ProfessorDAO();
                if (op == 1) dao.selectAll().forEach(p -> System.out.println(p.nome));
                else if (op == 2) {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.selectAll().stream().filter(p -> p.idProfessor == id).forEach(p -> System.out.println(p.nome));
                }
            }
            default -> System.out.println("Tabela inválida.");
        }
    }
}