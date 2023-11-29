package biblioteca;

import negocio.Curso;
import negocio.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private Connection conexao;
    public DisciplinaDAO(){
        conexao = FabricaDeConexao.obterConexao();
    }
    public void incluir(Disciplina c) {
        try {
            String sql = "INSERT INTO disciplina(iden_curso, ch, nome) VALUES (?, ?, ?)";
            PreparedStatement instrucao = this.conexao.prepareStatement(sql);
            instrucao.setInt(1, c.getIden_curso());
            instrucao.setInt(2, c.getCh());
            instrucao.setString(3, c.getNome());
            instrucao.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Disciplina> getDisciplinasByText(String text) {
        List<Disciplina> listaDisciplinas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM disciplina WHERE nome LIKE '%?%'";
            PreparedStatement instrucao = this.conexao.prepareStatement(sql);
            instrucao.setString(1, text);
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("iden_curso"),
                        rs.getInt("ch"),
                        rs.getString("nome")
                );
                listaDisciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDisciplinas;
    }
}
