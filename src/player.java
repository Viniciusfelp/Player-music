import java.util.Scanner;

public class player {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        playList playerDeMusicas=new playList();
        listaMusica listaDeMusicas=new listaMusica();

        playerDeMusicas.addMusic(listaDeMusicas.getMusica(0));

       //Thread remove = new Thread(new remover());
        //Thread add = new Thread (new adicionar());

    }
}
