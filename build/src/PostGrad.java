public class postGrad extends estudante {
    String tema;
    String dataDefesa;

    public postGrad(String nome, String dataNascimento, String CPF, Float CRA, String tema, String dataDefesa) {
        super(nome, dataNascimento, CPF, CRA);
        this.tema = tema;
        this.dataDefesa = dataDefesa;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setDataDefesa(String dataDefesa) {
        this.dataDefesa = dataDefesa;
    }

    public String getTema() {
        return tema;
    }

    public String getDataDefesa() {
        return dataDefesa;
    }

}
