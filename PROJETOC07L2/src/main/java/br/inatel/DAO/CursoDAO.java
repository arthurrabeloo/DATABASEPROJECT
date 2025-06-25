package br.inatel.DAO;

import br.inatel.Model.Curso;
import java.sql.*;
import java.util.ArrayList;

public class CursoDAO extends ConnectionDAO {
    public ArrayList<Curso> selectAll() {
        ArrayList<Curso> list = new ArrayList<>();
        connectToDb();
        try {
            String sql = "SELECT * FROM Cursos";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Curso c = new Curso();
                c.idCursos = rs.getInt("idCursos");
                c.nome = rs.getString("nome");
                c.descricao = rs.getString("descricao");
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}