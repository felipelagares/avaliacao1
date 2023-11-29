package biblioteca;

import negocio.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private Connection conexao;
    public CursoDAO(){
        conexao = FabricaDeConexao.obterConexao();
    }

    public void incluir(Curso c) {
        try {
            String sql = "INSERT INTO curso(iden, ano, nome) VALUES (?, ?, ?)";
            PreparedStatement instrucao = this.conexao.prepareStatement(sql);
            instrucao.setInt(1, c.getIden());
            instrucao.setInt(2, c.getAno());
            instrucao.setString(3, c.getNome());
            instrucao.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Curso> getCursosByText(String text) {
        List<Curso> listaCursos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM curso WHERE nome LIKE '%?%'";
            PreparedStatement instrucao = this.conexao.prepareStatement(sql);
            instrucao.setString(1, text);
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("iden"),
                        rs.getInt("ano"),
                        rs.getString("nome")
                );
                listaCursos.add(curso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCursos;
    }

    public List<Curso> getCursosByYearAndText(int ano, String text){
        List<Curso> listaCursos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM curso WHERE year = ? AND nome LIKE '%?%'";
            PreparedStatement instrucao = this.conexao.prepareStatement(sql);
            instrucao.setInt(1,ano);
            instrucao.setString(2, text);
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("iden"),
                        rs.getInt("ano"),
                        rs.getString("nome")
                );
                listaCursos.add(curso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCursos;
    }

    public List<Curso> getCursos() {
        List<Curso> listaCursos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM curso";
            PreparedStatement instrucao = this.conexao.prepareStatement(sql);
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("iden"),
                        rs.getInt("ano"),
                        rs.getString("nome")
                );
                listaCursos.add(curso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCursos;
    }

}
