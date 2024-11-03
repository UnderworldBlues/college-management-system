public class PostGrad extends Estudante {
    String tema;
    String dataDefesa;

    public PostGrad(String nome, String dataNascimento, String CPF, Float CRA, String tema, String dataDefesa) {
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

    @Override
    public String toString() {
        return super.toString() + "\nTema: " + tema + "\nData de Defesa: " + dataDefesa;
    }

}
