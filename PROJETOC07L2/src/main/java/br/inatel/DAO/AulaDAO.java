package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.*;
import java.util.*;

public class AulaDAO extends ConnectionDAO {

    public boolean insert(Aula aula) {
        connectToDb();
        try {
            String sql = "INSERT INTO Aulas (horario, diaSemana, Disciplinas_idDisciplinas) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, aula.horario);
            pst.setString(2, aula.diaSemana);
            pst.setInt(3, aula.disciplinaId);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(Aula aula) {
        connectToDb();
        try {
            String sql = "UPDATE Aulas SET horario = ?, diaSemana = ?, Disciplinas_idDisciplinas = ? WHERE idAulas = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, aula.horario);
            pst.setString(2, aula.diaSemana); // Alterado de "data" para "diaSemana"
            pst.setInt(3, aula.disciplinaId);
            pst.setInt(4, aula.idAulas); // ID para identificar a aula a ser atualizada
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(int idAula) {
        connectToDb();
        try {
            String sql = "DELETE FROM Aulas WHERE idAulas=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idAula);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Aula> selectAll() {
        List<Aula> aulas = new ArrayList<>();
        connectToDb();
        try {
            String sql = "SELECT * FROM Aulas";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Aula a = new Aula();
                a.idAulas = rs.getInt("idAulas");
                a.horario = rs.getString("horario");
                a.diaSemana = rs.getString("diaSemana"); // Atualizado de "data" para "diaSemana"
                a.disciplinaId = rs.getInt("Disciplinas_idDisciplinas");
                aulas.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aulas;
    }

}
