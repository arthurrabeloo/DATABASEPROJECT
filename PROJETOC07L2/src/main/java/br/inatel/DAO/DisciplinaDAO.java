package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.*;

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
}
