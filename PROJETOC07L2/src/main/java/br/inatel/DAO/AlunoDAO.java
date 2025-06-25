package br.inatel.DAO;

import br.inatel.Model.Aluno;
import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO extends ConnectionDAO {
    public ArrayList<Aluno> selectAll() {
        ArrayList<Aluno> list = new ArrayList<>();
        connectToDb();
        try {
            String sql = "SELECT * FROM Alunos";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.idAlunos = rs.getInt("idAlunos");
                a.idade = rs.getInt("idade");
                a.nome = rs.getString("nome");
                a.email = rs.getString("email");
                a.data_nasc = rs.getDate("data_nasc");
                a.telefone = rs.getString("telefone");
                a.cursoId = rs.getInt("Cursos_idCursos");
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Aluno> listarAlunosPorCurso(int cursoId) {
        ArrayList<Aluno> list = new ArrayList<>();
        connectToDb();
        try {
            String sql = "CALL ListarAlunosPorCurso(?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, cursoId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.nome = rs.getString("Nome_Aluno");
                a.email = rs.getString("Email_Aluno");
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}