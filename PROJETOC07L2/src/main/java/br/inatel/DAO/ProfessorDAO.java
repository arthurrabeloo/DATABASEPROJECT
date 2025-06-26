package br.inatel.DAO;
import br.inatel.Model.*;
import java.sql.*;
import java.util.*;

public class ProfessorDAO extends ConnectionDAO {

    public boolean insert(Professor professor) {
        connectToDb();
        try {
            String sql = "INSERT INTO Professor (nome, email, data_nasc, telefone) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, professor.nome);
            pst.setString(2, professor.email);
            pst.setString(3, professor.data_nasc);
            pst.setString(4, professor.telefone);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Professor professor) {
        connectToDb();
        try {
            String sql = "UPDATE Professor SET nome=?, email=?, data_nasc=?, telefone=? WHERE idProfessor=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, professor.nome);
            pst.setString(2, professor.email);
            pst.setString(3, professor.data_nasc);
            pst.setString(4, professor.telefone);
            pst.setInt(5, professor.idProfessor);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idProfessor) {
        connectToDb();
        try {
            String sql = "DELETE FROM Professor WHERE idProfessor=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idProfessor);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Professor> selectAll() {
        List<Professor> professores = new ArrayList<>();
        connectToDb();
        try {
            pst = con.prepareStatement("SELECT * FROM Professor");
            rs = pst.executeQuery();
            while (rs.next()) {
                Professor p = new Professor();
                p.idProfessor = rs.getInt("idProfessor");
                p.nome = rs.getString("nome");
                p.email = rs.getString("email");
                p.data_nasc = rs.getString("data_nasc");
                p.telefone = rs.getString("telefone");
                professores.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professores;
    }

    public void exibirProfessoresDisponiveis() {
        List<Professor> professores = this.selectAll();
        System.out.println("Professores disponíveis:");
        for (Professor p : professores) {
            System.out.println("ID: " + p.idProfessor + " | Nome: " + p.nome);
        }
    }



}
