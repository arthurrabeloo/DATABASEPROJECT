package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.*;
import java.util.*;

public class AlunoProfessorDAO extends ConnectionDAO {

    public boolean insert(AlunoProfessor ap) {
        connectToDb();
        try {
            String sql = "INSERT INTO Alunos_has_Professor (Alunos_idAlunos, Professor_idProfessor) VALUES (?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, ap.alunoId);
            pst.setInt(2, ap.professorId);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int alunoId, int professorId) {
        connectToDb();
        try {
            String sql = "DELETE FROM Alunos_has_Professor WHERE Alunos_idAlunos=? AND Professor_idProfessor=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, alunoId);
            pst.setInt(2, professorId);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AlunoProfessor> selectAll() {
        List<AlunoProfessor> lista = new ArrayList<>();
        connectToDb();
        try {
            pst = con.prepareStatement("SELECT * FROM Alunos_has_Professor");
            rs = pst.executeQuery();
            while (rs.next()) {
                AlunoProfessor ap = new AlunoProfessor();
                ap.alunoId = rs.getInt("Alunos_idAlunos");
                ap.professorId = rs.getInt("Professor_idProfessor");
                lista.add(ap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
