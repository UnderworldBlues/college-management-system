public class postGrad extends estudante {
    professor orientador;
    String tema;

    public postGrad(String nome, String dataNascimento, String CPF, Float CRA, professor orientador, String tema) {
        super(nome, dataNascimento, CPF, CRA);
        this.orientador = orientador;
        this.tema = tema;
    }

    public void setOrientador(professor orientador) {
        this.orientador = orientador;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public professor getOrientador() {
        return orientador;
    }

    public String getTema() {
        return tema;
    }

}
