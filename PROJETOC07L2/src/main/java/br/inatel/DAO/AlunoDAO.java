package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.SQLException;

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
}
