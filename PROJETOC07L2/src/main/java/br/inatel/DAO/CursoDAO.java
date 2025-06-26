package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.*;

public class CursoDAO extends ConnectionDAO {

    public boolean insert(Curso curso) {
        connectToDb();
        try {
            String sql = "INSERT INTO Cursos (nome, descricao) VALUES (?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, curso.nome);
            pst.setString(2, curso.descricao);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Curso curso) {
        connectToDb();
        try {
            String sql = "UPDATE Cursos SET nome=?, descricao=? WHERE idCursos=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, curso.nome);
            pst.setString(2, curso.descricao);
            pst.setInt(3, curso.idCursos);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idCurso) {
        connectToDb();
        try {
            String sql = "DELETE FROM Cursos WHERE idCursos=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCurso);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}