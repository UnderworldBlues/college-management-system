import java.util.*;

public class Notas {
    private String alunoCPF;
    private List<Float> notas;
    private List<String> turmas;

    public Notas(String alunoCPF) {
        this.alunoCPF = alunoCPF;
        this.notas = new ArrayList<Float>();
        this.turmas = new ArrayList<String>();
    }

    public void setTurma(String turma) {
        this.turmas.add(turma);
    }

    public void setNota(float nota) {
        this.notas.add(nota);
    }

    public String getAlunoCPF() {
        return this.alunoCPF;
    }

    public List<Float> getNotas() {
        return this.notas;
    }

    public List<String> getTurmas() {
        return this.turmas;
    }

    public Float getMedia() {
        float media = 0;
        int size = this.notas.size();
        for (int i = 0; i < size; i++) {
            media += this.notas.get(i);
        }
        return media / size;
    }

    // adiciona um valor a nota
    public void addNota(float nota, String turma) {
        int n = this.turmas.size();
        for (int i = 0; i < n; i++) {
            if (this.turmas.get(i).equals(turma)) {
                this.notas.set(i, this.notas.get(i) + nota);
                return;
            }
        }   
    }

    // mostra a nota de uma turma especifica
    public void printNota(String turma) {
        int n = this.turmas.size();
        for (int i = 0; i < n; i++) {
            if (this.turmas.get(i).equals(turma)) {
                System.out.println("Turma: " + this.turmas.get(i) + " Nota: " + this.notas.get(i));
                return;
            }
        }
    }

    public void printNota() {
        for (int i = 0; i < this.notas.size(); i++) {
            System.out.println("Turma: " + this.turmas.get(i) + " Nota: " + this.notas.get(i));
        }
    }

}
