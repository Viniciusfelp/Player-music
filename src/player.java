import Classes.listaMusica;
import Metodos.adicionar;
import Metodos.remover;

import java.util.Scanner;

public class player {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        listaMusica listaDeMusicas = new listaMusica();
        System.out.println("Seu acervo de músicas:");
        for(int i = 0; i < listaDeMusicas.lista.length; i++ ){
            System.out.println(i+"->"+listaDeMusicas.getNomeMusica(i));
        }
        System.out.println("1- Adicionar musica na playList:\n2- Remover Musica da playList: ");
        int opc = s.nextInt();
        if(opc == 1){
            System.out.println("Digite o número da música para adicionar à Playlist:");
            int mEscolhido = s.nextInt();
            Thread add = new Thread (new adicionar(mEscolhido));
            add.start();
        }else if(opc==2){ //remover músicas
            System.out.println("Digite o número da música para remover da Playlist:");
            int mEscolhido = s.nextInt();
            Thread remove = new Thread(new remover(mEscolhido));
            remove.start();
        }
    }
}

