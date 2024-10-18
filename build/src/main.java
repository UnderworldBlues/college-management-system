import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        int opt;
        Scanner sc = new Scanner(System.in);
        Map<String, turma> turmas = populate();

        System.out.println("Bem vindo.");
        while (true) 
        {
            System.out.println("Escolha uma opcao:\n1- Estudante\n2- Professor\n3- Turma\n4- Disciplina\n5- Sair");
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
                    disciplineMenu(turmas);
                    break;
                case 5:
                    System.out.println("buh-bye!");
                    System.exit(0);
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }  
        }
    }

    // cria entradas estaticas para turmas
    private static Map<String, turma> populate() {

        Map<String, turma> turmas = new HashMap<>();

        bachelor aluno1 = new bachelor("Joao", "01/01/2004", "111456789", 8.0f, "Google", "01/01/2021");
        bachelor aluno2 = new bachelor("Maria", "01/01/2000", "987004321", 7.0f, "Facebook", "01/01/2021");
        bachelor aluno3 = new bachelor("Jose", "01/01/2001", "123499789", 6.0f, "Microsoft", "01/01/2021");

        bachelor aluno4 = new bachelor("Joana", "01/01/2003", "123476789", 8.0f, "Google", "01/01/2021");
        postGrad aluno5 = new postGrad("Mariana", "01/01/2001", "982654321", 7.0f, "IA", "01/01/2021");
        postGrad aluno6 = new postGrad("Joaquim", "01/01/2001", "123456989", 6.0f, "BD", "01/01/2021");

        postGrad aluno7 = new postGrad("Eduardo", "01/01/2001", "143466789", 8.0f, "IA", "01/01/2021");
        postGrad aluno8 = new postGrad("Ana", "01/01/2001", "987654221", 7.0f, "BD", "01/01/2021");
        bachelor aluno9 = new bachelor("Pedro", "01/01/2001", "699456789", 6.0f, "Google", "01/01/2021");

        List<estudante> alunosArray1 = new ArrayList<>();
        alunosArray1.add(aluno1);
        alunosArray1.add(aluno2);
        alunosArray1.add(aluno3);

        List<estudante> alunosArray2 = new ArrayList<>();
        alunosArray2.add(aluno4);
        alunosArray2.add(aluno5);
        alunosArray2.add(aluno6);

        List<estudante> alunosArray3 = new ArrayList<>();
        alunosArray3.add(aluno7);
        alunosArray3.add(aluno8);
        alunosArray3.add(aluno9);

        disciplina disciplina1 = new disciplina("POO", "123", "60");
        disciplina disciplina2 = new disciplina("IA", "456", "60");
        disciplina disciplina3 = new disciplina("BD", "789", "60");

        professor professor1 = new professor("Carlos", "01/01/1970", "900654321", "01/01/2000", "FACOM");
        professor professor2 = new professor("Ana", "01/01/1970", "111156789", "01/01/2000", "FACOM");
        professor professor3 = new professor("Bertao", "01/01/1970", "997657321", "01/01/2000", "FACOM");

        turma turma1 = new turma(alunosArray1, professor1, disciplina1, 2021, 1);
        turma turma2 = new turma(alunosArray2, professor2, disciplina2, 2021, 1);
        turma turma3 = new turma(alunosArray3, professor3, disciplina3, 2021, 1);

        turmas.put("COMP69", turma1);
        turmas.put("COMP70", turma2);
        turmas.put("COMP71", turma3);

        return turmas;
    }

    private static void studentMenu(Map<String, turma> turmas){

        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("Escolha uma opcao:\n1- Adicionar estudante\n2- Remover estudante\n3- Visualizar informacoes de estudante\n4- Sair");
            int opt = sc.nextInt();
            switch (opt) 
            {
                case 1:

                    estudante temp = getStudentData();
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
                    return; 

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private static void teacherMenu(Map<String, turma> turmas){
        Scanner sc = new Scanner(System.in);

        System.out.println("Voce deseja:\n1- Adicionar professor\n2- Remover professor\n3- Visualizar informacoes de professor\n4- Voltar");
        int opt = sc.nextInt();
        while(true)
            switch(opt)
            {
                case 1:
                    // pega dados do usuario
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

                    professor temp = new professor(nome, dataNascimento, CPF, inicioContrato, departamento);

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
                        for (Map.Entry<String, turma> entry : turmas.entrySet()) 
                            System.out.println("Turma: " + entry.getKey() + "\nProfessor: " + entry.getValue().getProfessor().toString());

                    break;

                case 4:
                    return;

                default:
            }

    }

    private static void classMenu(Map<String, turma> turmas){
        Scanner sc = new Scanner(System.in);
        System.out.println("");

    }

    private static void disciplineMenu(Map<String, turma> turmas){
        Scanner sc = new Scanner(System.in);
        System.out.println("Voce deseja: 1- Visualizar disciplinas de todas as turmas\n2- Visualizar disciplinas de uma turma especifica\n3- Atualizar uma disciplina\n4- Voltar");
        int opt = sc.nextInt();
        while(true) 
            switch(opt)
            {
                case 1:
                    for (Map.Entry<String, turma> entry : turmas.entrySet()) 
                        System.out.println("Turma: " + entry.getKey() + "\nDisciplina: " + entry.getValue().getDisciplina().toString());
                    break;
                case 2:
                    System.out.println("Digite o codigo da turma:");
                    String turma = sc.next();
                    // confere se a turma existe
                    if(turmas.containsKey(turma))
                        // se sim, imprime a disciplina da turma
                        System.out.println(turmas.get(turma).getDisciplina());
                    else
                        System.out.println("Turma nao encontrada.");
                    break;
                case 3:
                    System.out.println("Digite o codigo da turma:");
                    turma = sc.next();
                    // confere se a turma existe (dnv)
                    if(!turmas.containsKey(turma))
                    {
                        System.out.println("Turma nao encontrada.");
                        break;
                    }
                    System.out.println("Digite o nome da disciplina:");
                    String nome = sc.next();
                    System.out.println("Digite o codigo da disciplina:");
                    String codigo = sc.next();
                    System.out.println("Digite a carga horaria da disciplina:");
                    String cargaHoraria = sc.next();
                    turmas.get(turma).setDisciplina(new disciplina(nome, codigo, cargaHoraria));
                    break;

                case 4:
                    return;

                default:
            }
    }

    private static estudante getStudentData () {

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

                // cria um objeto do tipo bachelor temporario
                return new bachelor(nome, dataNascimento, CPF, CRA, estagio, dataInicioEstagio);
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

                // cria um objeto do tipo postGrad temporario
                return new postGrad(nome, dataNascimento, CPF, CRA, tema, dataDefesa);
            case 3:
                return null;
            default:
                System.out.println("Opcao invalida.");
                return null;
        }

    }
}
