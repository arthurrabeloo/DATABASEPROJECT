package br.inatel.DAO;

import br.inatel.Model.Disciplina;
import java.sql.*;
import java.util.ArrayList;

public class DisciplinaDAO extends ConnectionDAO {
    public ArrayList<Disciplina> selectAll() {
        ArrayList<Disciplina> list = new ArrayList<>();
        connectToDb();
        try {
            String sql = "SELECT * FROM Disciplinas";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Disciplina d = new Disciplina();
                d.idDisciplinas = rs.getInt("idDisciplinas");
                d.nome = rs.getString("nome");
                d.cursoId = rs.getInt("Cursos_idCursos");
                d.professorId = rs.getInt("Professor_idProfessor");
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
