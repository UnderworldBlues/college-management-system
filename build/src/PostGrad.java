public class PostGrad extends Estudante implements Exibivel, ToTXT{
    String tema;

    public PostGrad(String nome, String dataNascimento, String CPF, Float CRA, String tema) {
        super(nome, dataNascimento, CPF, CRA);
        this.tema = tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }


    public String getTema() {
        return tema;
    }

    @Override
    public String mostrarDados() {
        return super.mostrarDados() + "\nTema: " + tema;
    }

    @Override
    public String toTXT() {
        return this.nome + "," + this.dataNascimento + "," + this.CPF + "," + this.CRA + "," + this.tema + ";";
    }

}
