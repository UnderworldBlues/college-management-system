public class Grad extends Estudante implements Exibivel{
    String estagio;
    String  dataInicioEstagio;

    public Grad(String nome, String dataNascimento, String CPF, Float CRA, String estagio, String dataInicioEstagio) {
        super(nome, dataNascimento, CPF, CRA);
        this.estagio = estagio;
        this.dataInicioEstagio = dataInicioEstagio;
    }

    public void setEstagio(String estagio) {
        this.estagio = estagio;
    }

    public void setDataInicioEstagio(String dataInicioEstagio) {
        this.dataInicioEstagio = dataInicioEstagio;
    }

    public String getEstagio() {
        return estagio;
    }

    public String getDataInicioEstagio() {
        return dataInicioEstagio;
    }

    @Override
    public String mostrarDados() {
        return super.mostrarDados() + "\nEstágio: " + estagio + "\nData de Início do Estágio: " + dataInicioEstagio;
    }

}
