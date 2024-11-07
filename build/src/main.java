import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        int opt;
        Scanner sc = new Scanner(System.in);
        Map<String, Turma> turmas = populate();

        System.out.println("Bem vindo.");
        while (true) 
        {
            System.out.println("Escolha uma opcao:\n1- Estudante\n2- Professor\n3- Turma\n4- Sair");
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    studentMenu(turmas);
                    break;
                case 2:
                    teacherMenu(turmas);
                    break;
                case 3:
                    classMenu(turmas);
                    break;
                case 4:
                    System.out.println("buh-bye!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }  
        }

    }

    // cria entradas estaticas para turmas
    private static Map<String, Turma> populate() {

        Map<String, Turma> turmas = new HashMap<>();

        Grad aluno1 = new Grad("Joao", "01/01/2004", "111456789", 8.0f, "Google", "01/01/2021");
        Grad aluno2 = new Grad("Maria", "01/01/2000", "987004321", 7.0f, "Facebook", "01/01/2021");
        Grad aluno3 = new Grad("Jose", "01/01/2001", "123499789", 6.0f, "Microsoft", "01/01/2021");

        Grad aluno4 = new Grad("Joana", "01/01/2003", "123476789", 8.0f, "Google", "01/01/2021");
        PostGrad aluno5 = new PostGrad("Mariana", "01/01/2001", "982654321", 7.0f, "IA", "01/01/2021");
        PostGrad aluno6 = new PostGrad("Joaquim", "01/01/2001", "123456989", 6.0f, "BD", "01/01/2021");

        PostGrad aluno7 = new PostGrad("Eduardo", "01/01/2001", "143466789", 8.0f, "IA", "01/01/2021");
        PostGrad aluno8 = new PostGrad("Ana", "01/01/2001", "987654221", 7.0f, "BD", "01/01/2021");
        Grad aluno9 = new Grad("Pedro", "01/01/2001", "699456789", 6.0f, "Google", "01/01/2021");

        List<Estudante> alunosArray1 = new ArrayList<>();
        alunosArray1.add(aluno1);
        alunosArray1.add(aluno2);
        alunosArray1.add(aluno3);

        List<Estudante> alunosArray2 = new ArrayList<>();
        alunosArray2.add(aluno4);
        alunosArray2.add(aluno5);
        alunosArray2.add(aluno6);

        List<Estudante> alunosArray3 = new ArrayList<>();
        alunosArray3.add(aluno7);
        alunosArray3.add(aluno8);
        alunosArray3.add(aluno9);

        Professor professor1 = new Professor("Carlos", "01/01/1970", "900654321", "01/01/2000", "FACOM");
        Professor professor2 = new Professor("Ana", "01/01/1970", "111156789", "01/01/2000", "FACOM");
        Professor professor3 = new Professor("Bertao", "01/01/1970", "997657321", "01/01/2000", "FACOM");

        Turma turma1 = new Turma(alunosArray1, professor1, 2021, 1, "POO", "123", "60");
        Turma turma2 = new Turma(alunosArray2, professor2, 2021, 1, "IA", "456", "60");
        Turma turma3 = new Turma(alunosArray3, professor3, 2021, 1, "BD", "789", "60");

        turmas.put("COMP69", turma1);
        turmas.put("COMP70", turma2);
        turmas.put("COMP71", turma3);

        return turmas;
    }

    private static void studentMenu(Map<String, Turma> turmas){

        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("Escolha uma opcao:\n1- Adicionar estudante\n2- Remover estudante\n3- Visualizar informacoes de estudante\n4- Sair");
            int opt = sc.nextInt();
            switch (opt) 
            {
                case 1:

                    Estudante temp = getStudentData();
                    if(temp == null){
                        System.out.println("Erro no cadastro do estudante.");
                        break;
                    }

                    System.out.println("Digite o codigo da turma que deseja adicionar o estudante:");
                    String turma = sc.next();
                    if(!turmas.containsKey(turma))
                    {
                        System.out.println("Turma nao encontrada.");
                        break;
                    }

                    if(turmas.get(turma).adicionarEstudante(temp))
                        System.out.println("Estudante adicionado com sucesso.");
                    else
                        System.out.println("Erro ao adicionar estudante.");
                        
                    break;

                case 2:

                    System.out.println("Digite o codigo da turma do estudante que deseja remover:");
                    turma = sc.next();
                    if(!turmas.containsKey(turma))
                    {
                        System.out.println("Turma nao encontrada.");
                        break;
                    }
                    System.out.println("Digite o CPF do estudante que deseja remover:");
                    String CPF = sc.next();

                    if(turmas.get(turma).removerEstudante(CPF))
                        System.out.println("Estudante removido com sucesso.");
                    else
                        System.out.println("Erro ao remover estudante.");
                    break;
                    
                case 3:

                    System.out.println("Voce deseja:\n1- Visualizar lista de estudantes\n2- Visualizar informacoes de um estudante especifico\n3- Voltar");
                    int opt2 = sc.nextInt();

                    if(opt2 == 1)
                    {
                        System.out.println("Digite o codigo da turma:");
                        turma = sc.next();
                        if(turmas.containsKey(turma))
                            turmas.get(turma).showStudentList();
                        else
                            System.out.println("Turma nao encontrada.");
                    }
                    else if(opt2 == 2)
                    {
                        System.out.println("Digite o codigo da turma:");
                        turma = sc.next();
                        if(turmas.containsKey(turma))
                        {
                            System.out.println("Digite o CPF do estudante:");
                            CPF = sc.next();
                            temp = turmas.get(turma).searchStudent(CPF);
                            if(temp == null)
                                System.out.println("Estudante nao encontrado.");
                            else
                                System.out.println(temp);
                        }
                        else
                            System.out.println("Turma nao encontrada.");
                    }
                    break;

                case 4:
                    // volta para o menu principal
                    sc.close();
                    return; 

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private static void teacherMenu(Map<String, Turma> turmas){
        Scanner sc = new Scanner(System.in);

        System.out.println("Voce deseja:\n1- Adicionar professor\n2- Remover professor\n3- Visualizar informacoes de professor\n4- Voltar");
        int opt = sc.nextInt();
        while(true)
            switch(opt)
            {
                case 1:

                    Professor temp = getProfessorData();

                    System.out.println("Digite o codigo da turma que deseja adicionar o professor:");
                    String turma = sc.next();
                    if(turmas.containsKey(turma))
                        turmas.get(turma).setProfessor(temp);
                    else
                        System.out.println("Turma nao encontrada.");
                    break;

                case 2:
                    System.out.println("Digite o codigo da turma do professor que deseja remover:");
                    turma = sc.next();
                    if(turmas.containsKey(turma))
                        turmas.get(turma).setProfessor(null);
                    else
                        System.out.println("Turma nao encontrada.");
                    break;

                case 3:
                    System.out.println("Voce deseja: \n1- Visualizar informacoes de um professor especifico\n2- Visualizar os professores de todas as turmas\n3- Voltar");
                    int opt2 = sc.nextInt();

                    if(opt2 == 1)
                    {
                        System.out.println("Digite o codigo da turma:");
                        turma = sc.next();
                        // confere se a tumra existe
                        if(turmas.containsKey(turma))
                            // se sim, imprime o professor da turma
                            System.out.println(turmas.get(turma).getProfessor());
                        else
                            System.out.println("Turma nao encontrada.");
                    }
                    else if(opt2 == 2)                
                        for (Map.Entry<String, Turma> entry : turmas.entrySet()) 
                            System.out.println("Turma: " + entry.getKey() + "\nProfessor: " + entry.getValue().getProfessor().toString());

                    break;

                case 4:
                    sc.close();
                    return;

                default:
                    System.out.println("opcao invalida");
            }

    }

    private static void classMenu(Map<String, Turma> turmas){
        Scanner sc = new Scanner(System.in);
        System.out.println("Voce deseja:\n1- Adicionar uma turma\n2- Visualizar as informacoes de uma turma\n3- remover uma turma\n4- Voltar");
        int opt = sc.nextInt();

        switch (opt)
        {
            case 1:

                System.out.println("Digite o codigo da turma:");
                String codigo = sc.next();
                if(turmas.containsKey(codigo))
                {
                    System.out.println("Turma ja existente.");
                    break;
                }
                System.out.println("Digite o ano da turma:");
                int ano = sc.nextInt();
                System.out.println("Digite o semestre da turma:");
                int semestre = sc.nextInt();
                System.out.println("Digite o nome da disciplina:");
                String disNome = sc.next();
                System.out.println("Digite o codigo da disciplina:");
                String disCodigo = sc.next();
                System.out.println("Digite a carga horaria da disciplina:");
                String disCargaHoraria = sc.next();
                Professor prof = getProfessorData();
                turmas.put(codigo, new Turma(new ArrayList<Estudante>(100), prof, ano, semestre, disNome, disCodigo, disCargaHoraria));
            
                break;
            case 2:

                System.out.println("Digite o codigo da turma:");
                String turma = sc.next();
                if(turmas.containsKey(turma))
                    System.out.println(turmas.get(turma));
                else
                    System.out.println("Turma nao encontrada.");
                break;

            case 3:

                System.out.println("Digite o codigo da turma:");
                turma = sc.next();
                if(turmas.containsKey(turma))
                    turmas.remove(turma);
                else
                    System.out.println("Turma nao existe.");
                
                System.out.println("Turma removida com sucesso.");
                break;

            case 4:
                break;
            default:
        }

    }

    private static Professor getProfessorData()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do professor:");
        String nome = sc.next();
        System.out.println("Digite a data de nascimento do professor:");
        String dataNascimento = sc.next();
        System.out.println("Digite o CPF do professor:");
        String CPF = sc.next();
        System.out.println("Digite a data de inicio do contrato do professor:");
        String inicioContrato = sc.next();
        System.out.println("Digite o departamento do professor:");
        String departamento = sc.next();
        
        sc.close();
        return new Professor(nome, dataNascimento, CPF, inicioContrato, departamento);
    }


    private static Estudante getStudentData () {

        Scanner sc = new Scanner(System.in);
        System.out.println("Voce deseja:\n1- Adicionar estudante da graduacao\n2- Adicionar estudante da pos graduacao\n3-Voltar");
        int opt = sc.nextInt();

        switch (opt) 
        {
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

                sc.close();
                // cria um objeto do tipo bachelor temporario
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
                System.out.println("Digite a data de defesa da dissertacao do estudante:");
                String dataDefesa = sc.next();

                sc.close();
                // cria um objeto do tipo postGrad temporario
                return new PostGrad(nome, dataNascimento, CPF, CRA, tema, dataDefesa);
            case 3:
                sc.close();
                return null;
            default:
                System.out.println("Opcao invalida.");
                sc.close();
                return null;
        }

    }

    
}
