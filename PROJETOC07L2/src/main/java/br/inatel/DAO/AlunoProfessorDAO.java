package br.inatel.DAO;

import br.inatel.Model.AlunoProfessor;
import java.sql.*;
import java.util.ArrayList;

public class AlunoProfessorDAO extends ConnectionDAO {
    public ArrayList<AlunoProfessor> selectAll() {
        ArrayList<AlunoProfessor> list = new ArrayList<>();
        connectToDb();
        try {
            String sql = "SELECT * FROM Alunos_has_Professor";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                AlunoProfessor ap = new AlunoProfessor();
                ap.alunoId = rs.getInt("Alunos_idAlunos");
                ap.professorId = rs.getInt("Professor_idProfessor");
                list.add(ap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
