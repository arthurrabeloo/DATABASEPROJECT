package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.*;
import java.util.*;

public class DisciplinaDAO extends ConnectionDAO {

    public boolean insert(Disciplina disciplina) {
        connectToDb();
        try {
            String sql = "INSERT INTO Disciplinas (nome, Cursos_idCursos, Professor_idProfessor) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, disciplina.nome);
            pst.setInt(2, disciplina.cursoId);
            pst.setInt(3, disciplina.professorId);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Disciplina disciplina) {
        connectToDb();
        try {
            String sql = "UPDATE Disciplinas SET nome=?, Cursos_idCursos=?, Professor_idProfessor=? WHERE idDisciplinas=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, disciplina.nome);
            pst.setInt(2, disciplina.cursoId);
            pst.setInt(3, disciplina.professorId);
            pst.setInt(4, disciplina.idDisciplinas);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idDisciplina) {
        connectToDb();
        try {
            String sql = "DELETE FROM Disciplinas WHERE idDisciplinas=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idDisciplina);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Disciplina> selectAll() {
        List<Disciplina> disciplinas = new ArrayList<>();
        connectToDb();
        try {
            String sql = """
            SELECT d.*, c.nome AS nome_curso, p.nome AS nome_professor
            FROM Disciplinas d
            JOIN Cursos c ON d.Cursos_idCursos = c.idCursos
            JOIN Professor p ON d.Professor_idProfessor = p.idProfessor""";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Disciplina d = new Disciplina();
                d.idDisciplinas = rs.getInt("idDisciplinas");
                d.nome = rs.getString("nome");
                d.cursoId = rs.getInt("Cursos_idCursos");
                d.professorId = rs.getInt("Professor_idProfessor");
                d.nomeCurso = rs.getString("nome_curso");
                d.professorNome = rs.getString("nome_professor");
                disciplinas.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }

    public void exibirDisciplinasDisponiveis() {
        List<Disciplina> disciplinas = this.selectAll();
        System.out.println("Disciplinas disponíveis:");
        for (Disciplina d : disciplinas) {
            System.out.println("ID: " + d.idDisciplinas + " | Nome: " + d.nome);
        }
    }


}

