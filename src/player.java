import Classes.listaMusica;
import Classes.playList;
import Metodos.*;

import java.util.Scanner;

public class player {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        boolean pauseFlag = false;
        boolean fecharPrograma = true;//booleano que encerra o laço while
        listaMusica listaDeMusicas = new listaMusica(); //instância da lista de músicas já predefinida
        playList playerDeMusicas = new playList();//instância do player de músicas que recebe os métodos(add,remove,skip,etc)

        GUI musicPlayer = new GUI();

        System.out.println("Seu acervo de músicas:");

        for(int i = 0; i < listaDeMusicas.lista.length; i++ ){//laço para printar todas as músicas disponíveis
            System.out.println(i+"->"+listaDeMusicas.getNomeMusica(i));
        }
        while(fecharPrograma){ //laço do programa em si
            int musicaEscolhida;//será escolhida mais a frente
            System.out.println("\n1- Adicionar Musica na playList\n2- Remover Musica da playList \n3- play\n0- Fechar programa\n "); //opções do programa

            int opcaoMenu = s.nextInt();//opção escolhida pelo usuário para executar alguma das ações a seguir:

            try { //bloco try/catch para executar os .join()
                Thread add = new Thread(); //instancias das threads de adição e remoção
                Thread remove = new Thread();
                Thread play = new Thread();
                switch (opcaoMenu) {
                    case 1:
                        System.out.println("Digite o número da música para adicionar à Playlist:");
                        musicaEscolhida = s.nextInt();
                        add = new Thread(new adicionar(musicaEscolhida, playerDeMusicas, listaDeMusicas));//Parâmetros de execução da Thread add
                        add.start(); //execução da Thread
                        break;
                    case 2:
                        System.out.println("Digite o número da música para remover da Playlist:");
                        playerDeMusicas.getPlaylistString();//Print da playList atual
                        musicaEscolhida = s.nextInt();
                        remove = new Thread(new remover(musicaEscolhida, playerDeMusicas));//Parâmetros de execução da Thread remove
                        remove.start();//execução da Thread
                        break;
                    case 3:
                        if(pauseFlag){
                            play.notify();
                            pauseFlag = false;
                        }else{
                            play = new Thread(new Play(playerDeMusicas));
                            play.start();
                        }
                    case 4:
                        pauseFlag = true;
                        play.wait();
                    case 5:
                        play.stop();
                        play = new Thread(new avancar(playerDeMusicas));
                        play.start();
                    case 6:
                        play.stop();
                        play = new Thread(new voltar(playerDeMusicas));
                        play.start();
                        case 0:
                        fecharPrograma = false; //booleano que seta o fim do programa(sair do laço while)
                        System.out.println("Programa finalizado!");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente!");//Print para operações não definidas no Switch
                        break;
                }
                add.join();//espera do Main para a execução das Threads terminar
                remove.join();
            }   catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
