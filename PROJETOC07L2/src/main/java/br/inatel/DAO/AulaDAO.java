package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.*;

public class AulaDAO extends ConnectionDAO {

    public boolean insert(Aula aula) {
        connectToDb();
        try {
            String sql = "INSERT INTO Aulas (horario, data, Disciplinas_idDisciplinas) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, aula.horario);
            pst.setDate(2, new java.sql.Date(aula.data.getTime()));
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
            String sql = "UPDATE Aulas SET horario=?, data=?, Disciplinas_idDisciplinas=? WHERE idAulas=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, aula.horario);
            pst.setDate(2, new java.sql.Date(aula.data.getTime()));
            pst.setInt(3, aula.disciplinaId);
            pst.setInt(4, aula.idAulas);
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
}