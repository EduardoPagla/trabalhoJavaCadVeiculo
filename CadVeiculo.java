import java.util.ArrayList;
import java.util.List;

List<String> veiculos = new ArrayList<>();

void main() {
    IO.println("Bem vindo ao sistema CadVeiculos");

    String menu = """
            MENU DE OPCOES:

            1 - Cadastrar veiculo
            2 - Listar veiculos
            3 - Remover veiculo
            4 - Buscar por nome
            5 - Editar nome de um veículo
            0 - Sair

            """;
    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite a opcao desejada: ");
        switch (opcao) {
            case 1 -> {
                cadastrar();
                IO.readln("Pressione Enter para continuar");
            }
            case 2 -> {
                listar(veiculos);
                IO.readln("Pressione Enter para continuar");
            }
            case 3 -> {
                excluir();
                IO.readln("Pressione Enter para continuar");
            }
            case 4 -> {
                buscaPorNome();
                IO.readln("Pressione Enter para continuar");
            }
            case 5 -> {
                editarNome();
                IO.readln("Pressione Enter para continuar");
            }
            case 0 -> {
                IO.println("Volte sempre!");
            }
            default -> {
                IO.println("Opcao invalida");
                IO.readln("Pressione Enter para continuar");

            }
        }
    } while (opcao != 0);
}

void excluir() {
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado para remover");
        return;
    }

    IO.println("""
            1 - Remover por índice
            2 - Remover por nome
            """);

    int opcao = Input.scanInt("Escolha uma opção: ");
    if (opcao == 1) {
        listar(veiculos);
        int indice = Input.scanInt("Digite o indice do veículo a ser removido: ");
        if (indice <= 0 || indice > veiculos.size()) {
            IO.println("Índice inválido!");
        } else {
            veiculos.remove(indice - 1);
            IO.println("Veículo removido com sucesso!");
        }
    } else if (opcao == 2) {
        listar(veiculos);
        String nome = IO.readln("Digite o nome do veículo: ").trim();

        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).equalsIgnoreCase(nome)) {
                veiculos.remove(i);
                IO.println("Veículo removido com sucesso!");
                return;
            }
        }
        IO.println("Veículo não encontrado!");
    }
}

void cadastrar() {
    String veiculo = IO.readln("Digite o nome do veiculo: ").trim();

    if (veiculo.isEmpty()) {
        IO.println("Nome do veiculo invalido");
        return;
    }
    for (String v : veiculos) {
        if (v.equalsIgnoreCase(veiculo)) {
            IO.println("Veículo já cadastrado!");
            return;
        }
    }
    veiculos.add(veiculo);
    IO.println("Veículo cadastrado com sucesso!");
}

void listar(List<String> lista) {
    if (lista.isEmpty()) {
        IO.println("A lista esta vazia! Cadastre um veículo");
        return;
    }

    ordenarLista(lista);
    IO.println("Total de veículos cadastrados: " + veiculos.size());
    for (int i = 1; i <= lista.size(); i++) {
        IO.println(i + " - " + lista.get(i - 1));
    }
}

void ordenarLista(List<String> lista) {
    for (int i = 0; i < lista.size() - 1; i++) {
        for (int j = 0; j < lista.size() - 1; j++) {

            String atual = lista.get(j);
            String proximo = lista.get(j + 1);

            if (atual.compareToIgnoreCase(proximo) > 0) {
                lista.set(j, proximo);
                lista.set(j + 1, atual);
            }
        }
    }
}

void buscaPorNome() {
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado para buscar");
        return;
    }
    List<String> temp = new ArrayList<>();
    String busca = IO.readln("Digite o nome de um veículo: ").trim();

    for (String cad : veiculos) {
        if (cad.toLowerCase().contains(busca.toLowerCase())) {
            temp.add(cad);
        }
    }
    IO.println("Total de veículos cadastrados: " + veiculos.size());
    listar(temp);
}

void editarNome() {
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado para editar");
        return;
    }

    listar(veiculos);
    int indice = Input.scanInt("Digite o número do veículo que deseja editar: ");

    if (indice <= 0 || indice > veiculos.size()) {
        IO.println("Índice inválido!");
        return;
    }

    String novoNome = IO.readln("Digite o novo nome do veículo: ").trim();
    if (novoNome.isEmpty()) {
        IO.println("Nome inválido!");
        return;
    }

    for (int i = 0; i < veiculos.size(); i++) {
        if (i == indice - 1)
            continue;

        if (veiculos.get(i).equalsIgnoreCase(novoNome)) {
            IO.println("Já existe um veículo com esse nome!");
            return;
        }
    }

    veiculos.set(indice - 1, novoNome);
    IO.println("Veículo atualizado com sucesso!");
}
