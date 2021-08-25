package Classes;

import java.util.ArrayList;

public class listaMusica {
    public ArrayList<music> lista = new ArrayList<music>();

    public listaMusica() {
        lista.add(new music("Industry Baby", "Lil Nas X", 30000));
        lista.add(new music("Montero", "Lil Nas X", 30000));
        lista.add(new music("Holiday", "Lil Nas X", 30000));
        lista.add(new music("Old Town Road", "Lil Nas X", 30000));
        lista.add(new music("Rodeo", "Lil Nas X", 30000));
        lista.add(new music("Panini", "Lil Nas X", 30000));
        lista.add(new music("Meu pedaço de pecado", "João Gomes", 30000));
        lista.add(new music("Aquelas coisas", "João Gomes", 30000));
        lista.add(new music("Eu tenho a senha", "João Gomes", 30000));
        lista.add(new music("Mete um Block nele", "João Gomes", 30000));
        lista.add(new music("Volta comigo BB", "Zé Vaqueiro", 30000));
        lista.add(new music("Meia Noite", "Zé Vaqueiro", 30000));
    }
    public music getMusica(int indice){

        return this.lista.get(indice);
    }
    public String getNomeMusica(int indice){
        return this.lista.get(indice).getNomeMusica();
    }

}