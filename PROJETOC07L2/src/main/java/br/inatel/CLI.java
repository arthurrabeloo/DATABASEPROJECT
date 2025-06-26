package br.inatel;

import br.inatel.DAO.*;
import br.inatel.Model.*;

import java.util.*;
import java.text.SimpleDateFormat;

public class CLI {
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
                try {
                    a.data_nasc = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                dao.exibirCursosDisponiveis();
                System.out.print("ID do curso: "); a.cursoId = sc.nextInt();
                dao.insert(a);
            }
            case "professor" -> {
                ProfessorDAO dao = new ProfessorDAO();
                Professor p = new Professor();
                System.out.print("Nome: "); p.nome = sc.nextLine();
                System.out.print("Email: "); p.email = sc.nextLine();
                System.out.print("Data nascimento (yyyy-MM-dd): "); p.data_nasc = sc.nextLine();
                System.out.print("Telefone: "); p.telefone = sc.nextLine();
                dao.insert(p);
            }
            case "disciplina" -> {
                DisciplinaDAO dao = new DisciplinaDAO();
                Disciplina d = new Disciplina();

                System.out.print("Nome da disciplina: "); d.nome = sc.nextLine();

                CursoDAO cursoDAO = new CursoDAO();
                cursoDAO.exibirCursosDisponiveis();
                System.out.print("ID do curso: "); d.cursoId = sc.nextInt();

                ProfessorDAO professorDAO = new ProfessorDAO();
                professorDAO.exibirProfessoresDisponiveis();
                System.out.print("ID do professor: "); d.professorId = sc.nextInt();
                dao.insert(d);
            }

            case "aula" -> {
                AulaDAO dao = new AulaDAO();
                Aula a = new Aula();
                System.out.print("Horário: "); a.horario = sc.nextLine();

                System.out.println("Informe o dia da semana (ex: Segunda-feira, Terça-feira, etc.):");
                a.diaSemana = sc.nextLine();

                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                disciplinaDAO.exibirDisciplinasDisponiveis();
                System.out.print("ID da disciplina: "); a.disciplinaId = sc.nextInt();
                dao.insert(a);
            }

            default -> System.out.println("Tabela inválida.");
        }
    }

    static boolean confirmar(String pergunta) {
        System.out.print(pergunta + " (s/n): ");
        return sc.nextLine().trim().equalsIgnoreCase("s");
    }

    static void menuUpdate() {
        System.out.print("\nTabela para atualizar (curso, aluno, professor, aula, disciplina): ");
        String tabela = sc.nextLine().toLowerCase();

        switch (tabela) {
            case "curso" -> {
                CursoDAO dao = new CursoDAO();
                dao.exibirCursosDisponiveis();

                System.out.print("ID do curso a ser atualizado: ");
                int id = sc.nextInt(); sc.nextLine();
                Curso c = dao.selectAll().stream().filter(cur -> cur.idCursos == id).findFirst().orElse(null);
                if (c == null) return;

                System.out.print("Atualizar nome? (s/n): ");
                if (sc.nextLine().equalsIgnoreCase("s")) {
                    System.out.print("Novo nome: ");
                    c.nome = sc.nextLine();
                }

                System.out.print("Atualizar descrição? (s/n): ");
                if (sc.nextLine().equalsIgnoreCase("s")) {
                    System.out.print("Nova descrição: ");
                    c.descricao = sc.nextLine();
                }

                dao.update(c);
            }

            case "aluno" -> {
                AlunoDAO dao = new AlunoDAO();
                dao.exibirAlunosDisponiveis();

                System.out.print("ID do aluno: ");
                int id = sc.nextInt(); sc.nextLine();
                Aluno a = dao.selectAll().stream().filter(al -> al.idAlunos == id).findFirst().orElse(null);
                if (a == null) return;

                if (confirmar("Atualizar nome?")) {
                    System.out.print("Novo nome: ");
                    a.nome = sc.nextLine();
                }
                if (confirmar("Atualizar email?")) {
                    System.out.print("Novo email: ");
                    a.email = sc.nextLine();
                }
                if (confirmar("Atualizar telefone?")) {
                    System.out.print("Novo telefone: ");
                    a.telefone = sc.nextLine();
                }
                if (confirmar("Atualizar idade?")) {
                    System.out.print("Nova idade: ");
                    a.idade = sc.nextInt(); sc.nextLine();
                }
                if (confirmar("Atualizar data nascimento?")) {
                    System.out.print("Nova data (yyyy-MM-dd): ");
                    try {
                        a.data_nasc = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (confirmar("Atualizar curso?")) {
                    dao.exibirCursosDisponiveis();
                    System.out.print("ID novo curso: ");
                    a.cursoId = sc.nextInt();
                }

                dao.update(a);
            }

            case "professor" -> {
                ProfessorDAO dao = new ProfessorDAO();
                dao.exibirProfessoresDisponiveis();

                System.out.print("ID do professor: ");
                int id = sc.nextInt(); sc.nextLine();
                Professor p = dao.selectAll().stream().filter(pr -> pr.idProfessor == id).findFirst().orElse(null);
                if (p == null) return;

                if (confirmar("Atualizar nome?")) {
                    System.out.print("Novo nome: ");
                    p.nome = sc.nextLine();
                }
                if (confirmar("Atualizar email?")) {
                    System.out.print("Novo email: ");
                    p.email = sc.nextLine();
                }
                if (confirmar("Atualizar data nascimento?")) {
                    System.out.print("Nova data nascimento (yyyy-MM-dd): ");
                    p.data_nasc = sc.nextLine();
                }
                if (confirmar("Atualizar telefone?")) {
                    System.out.print("Novo telefone: ");
                    p.telefone = sc.nextLine();
                }

                dao.update(p);
            }

            case "disciplina" -> {
                DisciplinaDAO dao = new DisciplinaDAO();
                dao.exibirDisciplinasDisponiveis();

                System.out.print("ID da disciplina: ");
                int id = sc.nextInt(); sc.nextLine();
                Disciplina d = dao.selectAll().stream().filter(di -> di.idDisciplinas == id).findFirst().orElse(null);
                if (d == null) return;

                if (confirmar("Atualizar nome?")) {
                    System.out.print("Novo nome: ");
                    d.nome = sc.nextLine();
                }
                if (confirmar("Atualizar curso?")) {
                    CursoDAO cdao = new CursoDAO();
                    cdao.exibirCursosDisponiveis();
                    System.out.print("Nova ID do curso: ");
                    d.cursoId = sc.nextInt(); sc.nextLine();
                }
                if (confirmar("Atualizar professor?")) {
                    ProfessorDAO pdao = new ProfessorDAO();
                    pdao.exibirProfessoresDisponiveis();
                    System.out.print("Nova ID do professor: ");
                    d.professorId = sc.nextInt();
                }

                dao.update(d);
            }

            case "aula" -> {
                AulaDAO dao = new AulaDAO();
                dao.selectAll().forEach(a -> System.out.println("ID: " + a.idAulas + " | Horário: " + a.horario + " | Dia: " + a.diaSemana + " | Disciplina: " + a.nomeDisciplina));

                System.out.print("ID da aula: ");
                int id = sc.nextInt(); sc.nextLine();
                Aula a = dao.selectAll().stream().filter(au -> au.idAulas == id).findFirst().orElse(null);
                if (a == null) return;

                if (confirmar("Atualizar horário?")) {
                    System.out.print("Novo horário: ");
                    a.horario = sc.nextLine();
                }
                if (confirmar("Atualizar dia da semana?")) {
                    System.out.print("Novo dia da semana: ");
                    a.diaSemana = sc.nextLine();
                }
                if (confirmar("Atualizar disciplina?")) {
                    DisciplinaDAO ddao = new DisciplinaDAO();
                    ddao.exibirDisciplinasDisponiveis();
                    System.out.print("Nova ID da disciplina: ");
                    a.disciplinaId = sc.nextInt();
                }

                dao.update(a);
            }

            default -> System.out.println("Tabela inválida.");
        }
    }

    static void menuDelete() {
        System.out.print("\nTabela para deletar (curso, aluno, professor, aula, disciplina): ");
        String tabela = sc.nextLine().toLowerCase();
        switch (tabela) {
            case "curso" -> {
                CursoDAO dao = new CursoDAO();
                dao.exibirCursosDisponiveis();
                System.out.print("ID do curso: ");
                int id = sc.nextInt();
                dao.delete(id);
            }
            case "aluno" -> {
                AlunoDAO dao = new AlunoDAO();
                dao.exibirAlunosDisponiveis();
                System.out.print("ID do aluno: ");
                int id = sc.nextInt();
                dao.delete(id);
            }
            case "professor" -> {
                ProfessorDAO dao = new ProfessorDAO();
                dao.exibirProfessoresDisponiveis();
                System.out.print("ID do professor: ");
                int id = sc.nextInt();
                dao.delete(id);
            }
            case "disciplina" -> {
                DisciplinaDAO dao = new DisciplinaDAO();
                dao.exibirDisciplinasDisponiveis();
                System.out.print("ID da disciplina: ");
                int id = sc.nextInt();
                dao.delete(id);
            }
            case "aula" -> {
                AulaDAO dao = new AulaDAO();
                dao.selectAll().forEach(a -> {
                    System.out.println("ID: " + a.idAulas + " | Horário: " + a.horario + " | Dia: " + a.diaSemana + " | Disciplina: " + a.nomeDisciplina);
                });
                System.out.print("ID da aula: ");
                int id = sc.nextInt();
                dao.delete(id);
            }
            default -> System.out.println("Tabela inválida.");
        }
    }

    static void menuSelect() {
        System.out.print("\nTabela para buscar (curso, aluno, professor, aula, disciplina): ");
        String tabela = sc.nextLine().toLowerCase();
        System.out.println("1. Todos os dados");
        System.out.println("2. Um único dado (por ID)");
        System.out.println("3. JOIN com outra tabela");
        System.out.print("Escolha: ");
        int op = sc.nextInt(); sc.nextLine();

        switch (tabela) {
            case "curso" -> {
                CursoDAO dao = new CursoDAO();
                if (op == 1) {
                    dao.selectAll().forEach(c -> System.out.println("ID: " + c.idCursos + " | Nome: " + c.nome + " | Descrição: " + c.descricao));
                } else if (op == 2) {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.selectAll().stream().filter(c -> c.idCursos == id)
                            .forEach(c -> System.out.println("ID: " + c.idCursos + " | Nome: " + c.nome + " | Descrição: " + c.descricao));
                } else if (op == 3) {
                    System.out.println("JOIN: Curso → Disciplinas");
                    DisciplinaDAO ddao = new DisciplinaDAO();
                    ddao.selectAll().forEach(d -> System.out.println("Curso ID: " + d.cursoId + " | Disciplina: " + d.nome));
                }
            }

            case "aluno" -> {
                AlunoDAO dao = new AlunoDAO();
                if (op == 1) {
                    dao.selectAll().forEach(a -> System.out.println("ID: " + a.idAlunos + " | Nome: " + a.nome + " | Email: " + a.email + " | Curso: " + a.cursoNome));
                } else if (op == 2) {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.selectAll().stream().filter(a -> a.idAlunos == id)
                            .forEach(a -> System.out.println("ID: " + a.idAlunos + " | Nome: " + a.nome + " | Email: " + a.email + " | Curso ID: " + a.cursoId));
                } else if (op == 3) {
                    dao.exibirCursosDisponiveis();
                    System.out.print("ID do curso: ");
                    int cursoId = sc.nextInt();
                    dao.listarAlunosPorCurso(cursoId)
                            .forEach(a -> System.out.println("Nome: " + a.nome + " | Email: " + a.email));
                }
            }

            case "professor" -> {
                ProfessorDAO dao = new ProfessorDAO();
                if (op == 1) {
                    dao.selectAll().forEach(p -> System.out.println("ID: " + p.idProfessor + " | Nome: " + p.nome + " | Email: " + p.email));
                } else if (op == 2) {
                    dao.exibirProfessoresDisponiveis();
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.selectAll().stream().filter(p -> p.idProfessor == id)
                            .forEach(p -> System.out.println("ID: " + p.idProfessor + " | Nome: " + p.nome + " | Email: " + p.email));
                } else if (op == 3) {
                    System.out.println("JOIN: Professor → Disciplinas");
                    DisciplinaDAO ddao = new DisciplinaDAO();
                    ddao.selectAll().forEach(d -> System.out.println("Professor: " + d.professorNome + " | Disciplina: " + d.nome));
                }
            }

            case "disciplina" -> {
                DisciplinaDAO dao = new DisciplinaDAO();
                if (op == 1) {
                    dao.selectAll().forEach(d -> System.out.println(
                            "ID: " + d.idDisciplinas +
                                    " | Nome: " + d.nome +
                                    " | Curso: " + d.nomeCurso +
                                    " | Professor: " + d.professorNome
                    ));
                } else if (op == 2) {
                    dao.exibirDisciplinasDisponiveis();
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.selectAll().stream().filter(d -> d.idDisciplinas == id)
                            .forEach(d -> System.out.println("ID: " + d.idDisciplinas + " | Nome: " + d.nome + " | Curso: " + d.nomeCurso + " | Professor: " + d.professorNome));
                } else if (op == 3) {
                    System.out.println("JOIN: Disciplinas → Curso & Professor");
                    dao.selectAll().forEach(d ->
                            System.out.println("Disciplina: " + d.nome +
                                    " | Curso: " + d.nomeCurso +
                                    " | Professor: " + d.professorNome));
                }
            }

            case "aula" -> {
                AulaDAO dao = new AulaDAO();
                if (op == 1) {
                    dao.selectAll().forEach(a -> {
                        System.out.println("ID: " + a.idAulas);
                        System.out.println("Horário: " + a.horario);
                        System.out.println("Dia da Semana: " + a.diaSemana);
                        System.out.println("Disciplina: " + a.nomeDisciplina);
                        System.out.println("-------------------------");
                    });
                } else if (op == 2) {
                    dao.exibirAulasDisponiveis();
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.selectAll().stream().filter(a -> a.idAulas == id)
                            .forEach(a -> {
                                System.out.println("ID: " + a.idAulas);
                                System.out.println("Horário: " + a.horario);
                                System.out.println("Dia da Semana: " + a.diaSemana);
                                System.out.println("Disciplina: " + a.nomeDisciplina);
                            });
                } else if (op == 3) {
                    System.out.println("JOIN: Aula → Disciplina");
                    dao.selectAll().forEach(a ->
                            System.out.println("Horário: " + a.horario + " | Dia: " + a.diaSemana + " | Disciplina: " + a.nomeDisciplina));
                }
            }

            default -> System.out.println("Tabela inválida.");
        }
    }
}