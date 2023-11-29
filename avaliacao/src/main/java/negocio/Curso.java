package negocio;

public class Curso {
    private int iden;
    private int ano;
    private String nome;

    public String getNome() {
        return nome;
    }

    public int getAno() {
        return ano;
    }

    public int getIden() {
        return iden;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setIden(int iden) {
        this.iden = iden;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso(int iden, int ano, String nome){
        setIden(iden);
        setAno(ano);
        setNome(nome);
    }
}
