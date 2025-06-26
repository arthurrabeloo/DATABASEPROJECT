package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.SQLException;
import java.util.*;

public class AlunoDAO extends ConnectionDAO {

    public boolean insert(Aluno aluno) {
        connectToDb();
        try {
            String sql = "INSERT INTO Alunos (idade, nome, email, data_nasc, telefone, Cursos_idCursos) VALUES (?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, aluno.idade);
            pst.setString(2, aluno.nome);
            pst.setString(3, aluno.email);
            pst.setDate(4, new java.sql.Date(aluno.data_nasc.getTime()));
            pst.setString(5, aluno.telefone);
            pst.setInt(6, aluno.cursoId);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Aluno aluno) {
        connectToDb();
        try {
            String sql = "UPDATE Alunos SET idade=?, nome=?, email=?, data_nasc=?, telefone=?, Cursos_idCursos=? WHERE idAlunos=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, aluno.idade);
            pst.setString(2, aluno.nome);
            pst.setString(3, aluno.email);
            pst.setDate(4, new java.sql.Date(aluno.data_nasc.getTime()));
            pst.setString(5, aluno.telefone);
            pst.setInt(6, aluno.cursoId);
            pst.setInt(7, aluno.idAlunos);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idAluno) {
        connectToDb();
        try {
            String sql = "DELETE FROM Alunos WHERE idAlunos=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idAluno);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Aluno> selectAll() {
        List<Aluno> alunos = new ArrayList<>();
        connectToDb();
        try {
            pst = con.prepareStatement("SELECT * FROM Alunos");
            rs = pst.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.idAlunos = rs.getInt("idAlunos");
                a.nome = rs.getString("nome");
                a.email = rs.getString("email");
                a.telefone = rs.getString("telefone");
                a.idade = rs.getInt("idade");
                a.data_nasc = rs.getDate("data_nasc");
                a.cursoId = rs.getInt("Cursos_idCursos");
                alunos.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> listarAlunosPorCurso(int cursoId) {
        List<Aluno> alunos = new ArrayList<>();
        connectToDb();
        try {
            String sql = "CALL ListarAlunosPorCurso(?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, cursoId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.nome = rs.getString("Nome_Aluno");
                a.email = rs.getString("Email_Aluno");
                alunos.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public void exibirCursosDisponiveis() {
        CursoDAO cursoDAO = new CursoDAO();
        List<Curso> cursos = cursoDAO.selectAll();
        System.out.println("Cursos disponíveis:");
        for (Curso c : cursos) {
            System.out.println("ID: " + c.idCursos + " | Nome: " + c.nome);
        }
    }
}

