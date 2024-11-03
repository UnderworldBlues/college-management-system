import java.util.List;

public class turma {
    private List<estudante> alunos;
    private professor professor;
    private disciplina disciplina;
    private int ano;
    private int semestre;

    public turma(List<estudante> alunos, professor professor, disciplina disciplina, int ano, int semestre) {
        this.alunos = alunos;
        this.professor = professor;
        this.disciplina = disciplina;
        this.ano = ano;
        this.semestre = semestre;
    }

    public void setAlunos(List<estudante> alunos) {
        this.alunos = alunos;
    }

    public void setProfessor(professor professor) {
        this.professor = professor;
    }

    public void setDisciplina(disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public List<estudante> getAlunos() {
        return alunos;
    }

    public professor getProfessor() {
        return professor;
    }

    public disciplina getDisciplina() {
        return disciplina;
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }

    // metodos lista
    public boolean adicionarEstudante(estudante aluno) {
        return alunos.add(aluno);
    }

    public boolean removerEstudante(String CPF) {

        for (estudante aluno : alunos) {
            if (aluno.getCPF().equals(CPF)) {
                alunos.remove(aluno);
                return true;
            }
        }
        return false;
    }

    public void showStudentList() {
        for (estudante aluno : alunos) {
            System.out.println(aluno);
        }
    }

    public estudante searchStudent(String CPF) {
        for (estudante aluno : alunos) {
            if (aluno.getCPF().equals(CPF)) {
                return aluno;
            }
        }
        return null;
    }

}
