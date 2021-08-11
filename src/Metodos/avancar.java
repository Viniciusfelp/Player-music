package Metodos;
import Classes.playList;

public class avancar implements Runnable {
    private final playList listaDeReproducao;
    public avancar(playList listaDeReproducao){
        this.listaDeReproducao = listaDeReproducao;
    }
    @Override
    public void run() {
        listaDeReproducao.setMusicaAtual(listaDeReproducao.getMusicaAtual()+1);
        new Play(listaDeReproducao);
    }
}
