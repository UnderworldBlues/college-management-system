public class professor {
    private String nome;
    private String dataNascimento;
    private String CPF;
    private String inicioContrato;
    private String departamento;

    public professor(String nome, String dataNascimento, String CPF, String inicioContrato, String departamento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
        this.inicioContrato = inicioContrato;
        this.departamento = departamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setInicioContrato(String inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public String getInicioContrato() {
        return inicioContrato;
    }

    public String getDepartamento() {
        return departamento;
    }
}
