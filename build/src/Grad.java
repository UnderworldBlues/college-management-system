public class bachelor extends estudante {
    String estagio;
    String  dataInicioEstagio;

    public bachelor(String nome, String dataNascimento, String CPF, Float CRA, String estagio, String dataInicioEstagio) {
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

}
