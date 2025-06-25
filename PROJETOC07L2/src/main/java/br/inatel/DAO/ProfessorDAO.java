package br.inatel.DAO;

import br.inatel.Model.Professor;
import java.sql.*;
import java.util.ArrayList;

public class ProfessorDAO extends ConnectionDAO {
    public ArrayList<Professor> selectAll() {
        ArrayList<Professor> list = new ArrayList<>();
        connectToDb();
        try {
            String sql = "SELECT * FROM Professor";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Professor p = new Professor();
                p.idProfessor = rs.getInt("idProfessor");
                p.nome = rs.getString("nome");
                p.email = rs.getString("email");
                p.data_nasc = rs.getString("data_nasc");
                p.telefone = rs.getString("telefone");
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}