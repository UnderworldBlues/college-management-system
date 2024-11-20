import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int opt;
        Scanner sc = new Scanner(System.in);
        // lista de notas
        ArrayList<Notas> notas = populateNotas();
        if (notas == null) 
        {
            System.out.println("Erro ao popular notas. Too bad!");
            sc.close();
            System.exit(1);
        }
        // mapa de turmas
        Map<String, Turma> turmas = populate();
        if (turmas == null) 
        {
            System.out.println("Erro ao popular turmas. Too bad!");
            sc.close();
            System.exit(1);
        }

        System.out.println("Bem vindo.");
        while (true) {
            System.out.println("Escolha uma opcao:\n1- Estudante\n2- Professor\n3- Turma\n4- Notas\n5- Sair");
            opt = sc.nextInt();
            switch (opt) {
                case 1:

                    studentMenu(turmas, notas, sc);
                    break;
                
                case 2:
                    
                    teacherMenu(turmas, sc);
                    break;
                
                case 3:
                               
                    classMenu(turmas, sc);
                    break;
                
                case 4:
                    notaMenu(notas, turmas, sc);
                    break;
                case 5:
                    System.out.println("buh-bye!");
                    System.out.println("==================================");
                    writeTurmas(turmas);
                    writeNotas(notas);
                    sc.close();
                    System.exit(0);
                
                default:
                    System.out.println("\n\n\n");
                    System.out.println("Opcao invalida.");
                    break;
            }
        }

    }

    private static void studentMenu(Map<String, Turma> turmas, ArrayList<Notas> notas, Scanner sc) {
        System.out.println("=======================");
        while (true) {
            System.out.println("Escolha uma opcao:\n1- Adicionar estudante\n2- Remover estudante\n3- Visualizar informacoes de estudante\n4- Sair");
            int opt = sc.nextInt();
            try {
                switch (opt) {
                    case 1:

                        Estudante temp = getStudentData(sc);
                        if (temp == null) {
                            System.out.println("Erro no cadastro do estudante.");
                            break;
                        }

                        System.out.println("Digite o codigo da turma que deseja adicionar o estudante:");
                        String turma = sc.next();
                        if (!turmas.containsKey(turma)) {
                            throw new TurmaNaoEncontrada("Turma " + turma + " nao encontrada.");
                        }

                        if (turmas.get(turma).adicionarEstudante(temp))
                            System.out.println("Estudante adicionado com sucesso.");
                        else
                            System.out.println("Erro ao adicionar estudante.");

                        break;

                    case 2:

                        System.out.println("Digite o codigo da turma do estudante que deseja remover:");
                        turma = sc.next();
                        if (!turmas.containsKey(turma)) {
                            throw new TurmaNaoEncontrada("Turma " + turma + " nao encontrada.");
                        }
                        System.out.println("Digite o CPF do estudante que deseja remover:");
                        String CPF = sc.next();

                        if (turmas.get(turma).removerEstudante(CPF))
                            System.out.println("Estudante removido com sucesso.");
                        else
                            System.out.println("Erro ao remover estudante.");
                        break;

                    case 3:

                        System.out.println("Voce deseja:\n1- Visualizar lista de estudantes\n2- Visualizar informacoes de um estudante especifico\n3- Voltar");
                        int opt2 = sc.nextInt();

                        if (opt2 == 1) {
                            System.out.println("Digite o codigo da turma:");
                            turma = sc.next();
                            if (turmas.containsKey(turma))
                                turmas.get(turma).showStudentList();
                            else
                                throw new TurmaNaoEncontrada("Turma " + turma + " nao encontrada.");
                        } 
                        else if (opt2 == 2) 
                        {
                            System.out.println("Digite o CPF do estudante:");
                            CPF = sc.next();
                            // procura o estudante em todas as turmas
                            for (Map.Entry<String, Turma> entry : turmas.entrySet()) 
                            {
                                Estudante tempEst = entry.getValue().searchStudent(CPF);
                                // no primeiro acerto, printa as informações do estudante e suas notas
                                if (tempEst != null) 
                                {
                                    System.out.println(tempEst.mostrarDados());
                                    System.out.println("Notas do estudante:");
                                    for(Notas n : notas)
                                        if(n.getAlunoCPF().equals(CPF))
                                            n.printNota();

                                    // quebra o loop
                                    break;
                                }
                            }
                        }
                        break;

                    case 4:
                        // volta para o menu principal;
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("=======================");
                        return;

                    default:
                        System.out.println("Opcao invalida.");
                }
            } 
            catch (TurmaNaoEncontrada e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void teacherMenu(Map<String, Turma> turmas, Scanner sc) {
        System.out.println("=======================");
        try {
            while (true)
            {
                System.out.println("Voce deseja:\n1- Adicionar professor\n2- Remover professor\n3- Visualizar informacoes de professor\n4- Voltar");
                int opt = sc.nextInt();
                switch (opt) {
                    case 1:

                        Professor temp = getProfessorData(sc);

                        System.out.println("Digite o codigo da turma que deseja adicionar o professor:");
                        String turma = sc.next();
                        if (turmas.containsKey(turma))
                        {
                            turmas.get(turma).setProfessor(temp);
                            System.out.println("Professor adicionado com sucesso!");
                        }
                        else
                            throw new TurmaNaoEncontrada("Turma " + turma + " nao encontrada.");
                        break;

                    case 2:
                        System.out.println("Digite o codigo da turma do professor que deseja remover:");
                        turma = sc.next();
                        if (turmas.containsKey(turma)){
                            turmas.get(turma).setProfessor(null);
                        }
                        else
                            throw new TurmaNaoEncontrada("Turma " + turma + " nao encontrada.");
                        break;

                    case 3:

                        System.out.println("Digite o CPF do professor:");
                        String CPF = sc.next();
                        int marker = 0; // pra garantir q tempProf só seja setado uma vez
                        Professor tempProf = null;
                        ArrayList<String> turmasDoProf = new ArrayList<>();

                        // pega as turmas do professor
                        for (Map.Entry<String, Turma> entry : turmas.entrySet()) {
                            if (entry.getValue().getProfessor() != null && entry.getValue().getProfessor().getCPF().equals(CPF))
                            {
                                // toda vez que o professor aparecer, adiciona a turma na lista
                                turmasDoProf.add(entry.getKey());
                                if(marker == 0)
                                {
                                    tempProf = entry.getValue().getProfessor();
                                    marker++;
                                }
                            }
                        }

                        if (tempProf == null)
                            System.out.println("Professor nao encontrado.");
                        else
                        {
                            System.out.println(tempProf.toString());
                            System.out.println("Turmas em que o professor ministra:");
                            for(String t : turmasDoProf)
                                System.out.println(t);
                        }

                        break;

                    case 4:
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("=======================");
                        return;

                    default:
                        System.out.println("opcao invalida");
                }
            }
        } catch (TurmaNaoEncontrada e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void classMenu(Map<String, Turma> turmas, Scanner sc) {
        System.out.println("=======================");
        while (true) {
          System.out.println("\n\n\n"); 
            
            try {
                System.out.println("Voce deseja:\n1- Adicionar uma turma\n2- Visualizar as informacoes de uma turma\n3- Remover uma turma\n4- Voltar");
                int opt = sc.nextInt();
                sc.nextLine();
                String turma;

                switch (opt) {
                    case 1:
                        
                        System.out.println("\n\n\n");
                        System.out.println("Digite o codigo da turma:");
                        String codigo = sc.nextLine();
                        if (turmas.containsKey(codigo)) 
                        {
                            System.out.println("Turma ja existente.");
                            break;
                        }
                        System.out.println("Digite o ano da turma:");
                        int ano = sc.nextInt();
                        System.out.println("Digite o semestre da turma:");
                        int semestre = sc.nextInt();
                        System.out.println("Digite o nome da disciplina:");
                        String disNome = sc.nextLine();
                        System.out.println("Digite o codigo da disciplina:");
                        String disCodigo = sc.nextLine();
                        System.out.println("Digite a carga horaria da disciplina:");
                        String disCargaHoraria = sc.nextLine();
                        Professor prof = getProfessorData(sc);
                        turmas.put(codigo, new Turma(new ArrayList<Estudante>(100), prof, ano, semestre, disNome, disCodigo, disCargaHoraria));
                        System.out.println("\n\n\n");

                        break;

                    case 2:
                        
                        System.out.println("\n\n\n");
                        System.out.println("Digite o codigo da turma:");
                        turma = sc.nextLine();
                        if (turmas.containsKey(turma)){
                            System.out.println("\n\n\n");
                            System.out.println(turmas.get(turma).mostrarDados());
                            System.out.println("==================================");
                        }
                        else
                            throw new TurmaNaoEncontrada("Turma " + turma + " nao encontrada.");
                        
                        System.out.println("\n\n\n");

                        break;

                    case 3:
                        
                        System.out.println("\n\n\n");
                        System.out.println("Digite o codigo da turma:");
                        turma = sc.nextLine();
                        if (turmas.containsKey(turma))
                            turmas.remove(turma);
                        else
                            throw new TurmaNaoEncontrada("Turma " + turma + " nao encontrada.");
                        
                        System.out.println("Turma removida com sucesso.");
                        System.out.println("\n\n\n");
                        
                        break;

                    case 4:

                        System.out.println("\n\n\n");
                        System.out.println("Voltando ao Menu Anterior.");
                        System.out.println("=======================");
                        System.out.println("\n\n\n");
                        return;
                    
                    default:
                        
                        System.out.println("\n\n\n");
                        System.out.println("Opçao Invalida.");
                        System.out.println("\n\n\n");
                }
            }catch (TurmaNaoEncontrada e)
            {
                System.out.println(e.getMessage());
                System.out.println("\n\n\n");
            }
        } 
    } 

    private static void notaMenu(ArrayList<Notas> notas, Map<String, Turma> turmas, Scanner sc) {
        System.out.println("=======================");
        while (true) {
            System.out.println("Escolha uma opcao:\n1- Visualizar notas de um estudante\n2- Visualizar media de uma turma\n3- Sair");
            int opt = sc.nextInt();
            switch (opt)
            {
                case 1 :
                    System.out.println("\n\n\n");
                    System.out.println("Digite o CPF do estudante:");
                    String CPF = sc.next();
                    System.out.println("Voce deseja:\n1- Visualizar a nota em uma turma especifica\n2- Visualizar todas as notas\n3- Visualizar a media do aluno\n4- Atualizar a nota de um aluno\n5- Adicionar uma nova nota\n");
                    int opt2 = sc.nextInt();
                    switch (opt2)
                    {
                        case 1:
                            System.out.println("\n\n\n");
                            System.out.println("Digite o codigo da turma:");
                            String turma = sc.next();
                            for(Notas n : notas)
                            {
                                if(n.getAlunoCPF().equals(CPF))
                                {
                                    n.printNota(turma);
                                    break;
                                }
                            }
                            break;
                        case 2:
                            System.out.println("\n\n\n");
                            for(Notas n : notas)
                            {
                                if(n.getAlunoCPF().equals(CPF))
                                {
                                    n.printNota();
                                    break;
                                }
                            }
                            break;
                        case 3:
                            System.out.println("\n\n\n");
                            for(Notas n : notas)
                            {
                                if(n.getAlunoCPF().equals(CPF))
                                {
                                    System.out.println("Media do aluno: " + n.getMedia());
                                    break;
                                }
                            }
                            break;
                        case 4:
                            System.out.println("\n\n\n");
                            System.out.println("Digite o codigo da turma:");
                            turma = sc.next();
                            System.out.println("Digite a nota:");
                            float nota = sc.nextFloat();
                            for(Notas n : notas)
                            {
                                if(n.getAlunoCPF().equals(CPF))
                                {
                                    n.addNota(nota, turma);
                                    break;
                                }
                            }

                        case 5:
                            System.out.println("\n\n\n");
                            System.out.println("Digite o codigo da turma:");
                            turma = sc.next();
                            System.out.println("Digite a nota:");
                            nota = sc.nextFloat();
                            for(Notas n : notas)
                            {
                                if(n.getAlunoCPF().equals(CPF))
                                {
                                    n.setNota(nota);
                                    n.setTurma(turma);
                                    break;
                                }
                            }
                            break;

                        default:
                            System.out.println("Opcao invalida.");
                    }
                    break;

                case 2:
                    try{
                        System.out.println("\n\n\n");
                        System.out.println("Digite o codigo da turma:");
                        String turma = sc.next();
                        if(turmas.containsKey(turma))
                        {
                            float media = 0;
                            int size = 0;
                            for(Notas n : notas)
                                if(n.getTurmas().contains(turma))
                                {
                                    media += n.getMedia();
                                    size++;
                                }
                            
                            System.out.println("Media da turma: " + media/size);
                        }
                        else
                            throw new TurmaNaoEncontrada("Turma " + turma + " nao encontrada.");
                        break;

                    }catch(TurmaNaoEncontrada e)    
                    {
                        System.out.println(e.getMessage());
                        System.out.println("\n\n\n");
                    }
                case 3:
                    System.out.println("Voltando ao menu principal...");
                    System.out.println("=======================");
                    return;

            }
        }
    }

    private static Professor getProfessorData(Scanner sc) {
        System.out.println("\n\n\n");
        System.out.println("Digite o nome do professor:");
        String nome = sc.nextLine();
        System.out.println("\n");
        System.out.println("Digite a data de nascimento do professor:");
        String dataNascimento = sc.nextLine();
        System.out.println("\n");
        System.out.println("Digite o CPF do professor:");
        String CPF = sc.nextLine();
        System.out.println("\n");
        System.out.println("Digite a data de inicio do contrato do professor:");
        String inicioContrato = sc.nextLine();
        System.out.println("\n");
        System.out.println("Digite o departamento do professor:");
        String departamento = sc.nextLine();
        System.out.println("\n\n\n");
        return new Professor(nome, dataNascimento, CPF, inicioContrato, departamento);
    }

    private static Estudante getStudentData(Scanner sc) {

        System.out.println("Voce deseja:\n1- Adicionar estudante da graduacao\n2- Adicionar estudante da pos graduacao\n3-Voltar");
        int opt = sc.nextInt();

        switch (opt) {
            case 1:
                // pega dados do usuario
                System.out.println("Digite o nome do estudante:");
                String nome = sc.next();
                System.out.println("Digite a data de nascimento do estudante:");
                String dataNascimento = sc.next();
                System.out.println("Digite o CPF do estudante:");
                String CPF = sc.next();
                System.out.println("Digite o CRA do estudante:");
                Float CRA = sc.nextFloat();
                System.out.println("Digite o nome da empresa de estagio do estudante:");
                String estagio = sc.next();
                System.out.println("Digite a data de inicio do estagio do estudante:");
                String dataInicioEstagio = sc.next();
                // cria um objeto do tipo grad temporario
                return new Grad(nome, dataNascimento, CPF, CRA, estagio, dataInicioEstagio);
            case 2:
                // pega dados do usuario
                System.out.println("Digite o nome do estudante:");
                nome = sc.next();
                System.out.println("Digite a data de nascimento do estudante:");
                dataNascimento = sc.next();
                System.out.println("Digite o CPF do estudante:");
                CPF = sc.next();
                System.out.println("Digite o CRA do estudante:");
                CRA = sc.nextFloat();
                System.out.println("Digite o tema da dissertacao do estudante:");
                String tema = sc.next();
                // cria um objeto do tipo postGrad temporario
                return new PostGrad(nome, dataNascimento, CPF, CRA, tema);
            case 3:
                System.out.println("Voltando ao Menu Anterior.");
                return null;
            default:
                System.out.println("Opcao invalida.");

                return null;
        }

    }


    // cria entradas estaticas para turmas
    private static Map<String, Turma> populate() {

            Map<String, Turma> turmas = new HashMap<>();
            // abre o arquivo
            try(BufferedReader reader = new BufferedReader(new FileReader("data.txt")))
            {
                // o arquivo é estruturado da seguinte maneira: nome da turma; info da disciplina; info do professor; ano; semestre
                // e as informações dos estudantes. 
                String buffer = reader.readLine();
                while(buffer != null)
                {   
                    // pega o nome da turma
                    String turmaNome = buffer;
                    // pega as informações da disciplina
                    String[] disciplina = reader.readLine().split(",");
                    // pega as informações do professor
                    String[] professor = reader.readLine().split(",");
                    // pega o ano e o semestre
                    int ano = Integer.parseInt(reader.readLine());
                    int semestre = Integer.parseInt(reader.readLine());
                    // pega as informações dos estudantes
                    String[] alunos = reader.readLine().split(";");
                    // pra isso, criamos uma lista de estudantes
                    ArrayList<Estudante> estudantes = new ArrayList<>();
                    // iteramos sobre os estudantes
                    for(String aluno : alunos)
                    {
                        // particionamos as informações do estudante
                        String[] info = aluno.split(",");
                        Float CRA = Float.parseFloat(info[3]);
    
                        // se o aluno for da gradução, ele tem 6 campos de informação, se for da pós, ele tem 5
                        if(info.length == 6)
                            estudantes.add(new Grad(info[0], info[1], info[2], CRA, info[4], info[5]));
                        else
                            estudantes.add(new PostGrad(info[0], info[1], info[2], CRA, info[4]));
                        
                    }
    
                    Professor prof = new Professor(professor[0], professor[1], professor[2], professor[3], professor[4]);
                    // adiciona a turma ao mapa
                    turmas.put(turmaNome, new Turma(estudantes, prof, ano, semestre, disciplina[0], disciplina[1], disciplina[2]));
    
                    // lemos a próxima linha
                    buffer = reader.readLine();
                }
                // fechamos o arquivo
                reader.close();
    
            }catch(IOException e)
            {
                System.out.println("Erro ao abrir o arquivo.");
                return null;
            }
            // retornamos o mapa
            return turmas;
    
        }

        // metodo pra popular notas
    private static ArrayList<Notas> populateNotas() {
        
            // criamos uma lista de notas
            ArrayList<Notas> notas = new ArrayList<>();
            // abrimos o arquivo
            try(BufferedReader reader = new BufferedReader(new FileReader("dataNotas.txt")))
            {
                // o arquivo é estruturado da seguinte maneira: CPF do aluno; turma; nota
                String buffer = reader.readLine();
                while(buffer != null)
                {
                    String alunoCPF = buffer;
                    String[] listaDeNotas = reader.readLine().split(";");
                    for(String notasItem : listaDeNotas)
                    {
                        String[] info = notasItem.split(",");
                        String turma = info[0];
                        Float nota = Float.parseFloat(info[1]);
                        Notas n = new Notas(alunoCPF);
                        n.setNota(nota);
                        n.setTurma(turma);
                        notas.add(n);
                    }
                    buffer = reader.readLine();
                }
                // fechamos o arquivo
                reader.close();
            }catch(IOException e)
            {
                System.out.println("Erro ao abrir o arquivo.");
                return null;
            }
            // retornamos a lista
            return notas;
    }

    // função pra salvar as turmas
    public static void writeTurmas(Map<String, Turma> turmas)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt")))
        {
            for(Map.Entry<String, Turma> entry : turmas.entrySet())
            {
                String disNome = entry.getValue().getDisciplinaNome();
                String disCodigo = entry.getValue().getDisciplinaCodigo();
                String disCargaHoraria = entry.getValue().getDisciplinaCargaHoraria();
                String prof = entry.getValue().getProfessor().toTXT();
                int ano = entry.getValue().getAno();
                int semestre = entry.getValue().getSemestre();


                writer.write(entry.getKey());
                writer.newLine();
                writer.write(disNome + "," + disCodigo + "," + disCargaHoraria);
                writer.newLine();
                writer.write(prof);
                writer.newLine();
                writer.write(Integer.toString(ano));
                writer.newLine();
                writer.write(Integer.toString(semestre));
                writer.newLine();
                for(Estudante aluno : entry.getValue().getAlunos())
                {
                   if(aluno instanceof Grad)
                   {
                       Grad g = (Grad) aluno;
                       writer.write(g.toTXT());
                   }
                   else
                   {
                       PostGrad pg = (PostGrad) aluno;
                       writer.write(pg.toTXT());
                   }
                }
                writer.newLine();
            }
            writer.close();
        }catch(IOException e)
        {
            System.out.println("Erro ao salvar as turmas. TOO BAD!");
        }
    }

    // função pra salvar as notas
    public static void writeNotas(ArrayList<Notas> notas)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("dataNotas.txt")))
        {
            for(Notas n : notas)
            {
                writer.write(n.getAlunoCPF());
                writer.newLine();
                List<String> notasAluno = n.getTurmas();
                List<Float> notasAlunoFloat = n.getNotas();
                int size = notasAluno.size();
                for(int i = 0; i < size; i++)
                {
                    // escreve as notas no formato turma,nota
                    writer.write(notasAluno.get(i) + "," + Float.toString(notasAlunoFloat.get(i)) + ";");
                }

                writer.newLine();
            }
            writer.close();
        }catch(IOException e)
        {
            System.out.println("Erro ao salvar as notas. TOO BAD!");
        }
    }
}