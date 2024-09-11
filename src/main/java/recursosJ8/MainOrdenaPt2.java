package recursosJ8;

import dto.Cliente;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que da continuidade a MainOrdena, apresentando as Stream's e algumas funcionalidades
 */
public class MainOrdenaPt2 {
    public static void main (String args[]) {
        List<Cliente> listCliente = Arrays.asList(
                new Cliente("Jozé Bezerra", "123.456.789-10", "Astronauta").setVivo(false),
                new Cliente("Maria dos Socorros", "999.999.999-99", "Musa do verão").setVivo(true),
                new Cliente("Joseph Climber", "000.000.000-00", "TUDO").setVivo(true)
        );
        //-- os metodos irão ordenar pelo CPF --
        listCliente.sort((c1,c2)->c1.getCpf().compareTo(c2.getCpf()));
        listCliente.sort(Comparator.comparing(Cliente::getCpf));
        //-- No comando abaixo:
        //  1- a Stream nao impacta no listCliente
        //  2- Filtrou apenas pelos clientes vivos
        //  3- Converteu para um mapa de cpfs
        //  4- Imprimiu este mapa
        listCliente.stream().filter(Cliente::getVivo).map(Cliente::getCpf).forEach(System.out::println);
        System.out.println(listCliente);

        listCliente.stream().filter(Cliente::getVivo).findAny().ifPresent(System.out::println);

        //-- Mapeia os clientes filtrados pelo cpf
        Map<String, Cliente> clientesMap = listCliente.stream().filter(Cliente::getVivo)
            .collect(Collectors.toMap(Cliente::getCpf,c->c));

        listCliente = listCliente.stream().filter(Cliente::getVivo).collect(Collectors.toList());
        System.out.println(listCliente); //agora filtrou para apenas os vivos
    }
}
