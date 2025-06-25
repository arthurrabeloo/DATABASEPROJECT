package br.inatel.DAO;

import br.inatel.Model.Aula;
import java.sql.*;
import java.util.ArrayList;

public class AulaDAO extends ConnectionDAO {
    public ArrayList<Aula> selectAll() {
        ArrayList<Aula> list = new ArrayList<>();
        connectToDb();
        try {
            String sql = "SELECT * FROM Aulas";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Aula a = new Aula();
                a.idAulas = rs.getInt("idAulas");
                a.horario = rs.getString("horario");
                a.data = rs.getDate("data");
                a.disciplinaId = rs.getInt("Disciplinas_idDisciplinas");
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}