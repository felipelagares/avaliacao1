package negocio;

public class Disciplina {
    private String nome;
    private int ch;
    private int iden_curso;

    public Disciplina(int idenCurso, int ch, String nome) {
        setIden_curso(idenCurso);
        setCh(ch);
        setNome(nome);
    }

    public int getIden_curso() {
        return iden_curso;
    }

    public int getCh() {
        return ch;
    }

    public String getNome() {
        return nome;
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    public void setIden_curso(int iden_curso) {
        this.iden_curso = iden_curso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "disciplina " +
                "nome: " + nome + "\n" +
                "   ch: " + ch + "\n" +
                "   id do curso: " + iden_curso;
    }
}
