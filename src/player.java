import java.util.Scanner;

public class player {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        playList playlist=new playList();
        listaMusica listaDeMusicas=new listaMusica();
        System.out.println("Seu acervo de músicas:");
        for(int i = 0; i < listaDeMusicas.lista.length; i++ ){
            System.out.println(i+"->"+listaDeMusicas.getNomeMusica(i));
        }
        System.out.println("1- Adicionar musica na playList: \n 2- Remover Musica da playList: ");
        int opc = s.nextInt();
        if(opc == 1){
        System.out.println("Digite o número da música para adicionar à Playlist:");
        int mEscolhido = s.nextInt();
        playlist.addMusic(listaDeMusicas.getMusica(mEscolhido));

        //Thread remove = new Thread(new remover());
        //Thread add = new Thread (new adicionar());

    }else if(opc==2){ //remover músicaas
            System.out.println("Digite o número da música para remover da Playlist:");
            int mEscolhido = s.nextInt();
            playlist.removeMusic(mEscolhido);
        }
}
}
