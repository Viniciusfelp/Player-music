package Metodos;
import Classes.playList;

public class avancar implements Runnable {
    private final playList listaDeReproducao;
    private final int indiceMusicaEscolhida;
    public avancar(int indice,playList listaDeReproducao){
        this.listaDeReproducao = listaDeReproducao;
        this.indiceMusicaEscolhida = indice;
    }
    @Override
    public void run() {
        listaDeReproducao.setMusicaAtual(listaDeReproducao.getMusicaAtual()+1);
        new Play(indiceMusicaEscolhida, listaDeReproducao);
    }
}
