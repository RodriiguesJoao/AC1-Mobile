public class Filme {
    private String titulo;
    private String diretor;
    private int ano;
    private String genero;
    private float nota;
    private boolean viuNoCinema;

    public Filme(String titulo, String diretor, int ano, String genero, float nota, boolean viuNoCinema) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.ano = ano;
        this.genero = genero;
        this.nota = nota;
        this.viuNoCinema = viuNoCinema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public boolean isViuNoCinema() {
        return viuNoCinema;
    }

    public void setViuNoCinema(boolean viuNoCinema) {
        this.viuNoCinema = viuNoCinema;
    }
}
