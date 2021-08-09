package Metodos;

import Classes.playList;

public class play extends Thread{
    private final playList listaDeReproducao;
    private final int indiceMusicaEscolhida;

    public play(int indice,playList listaDeReproducao) {
        this.indiceMusicaEscolhida = indice;
        this.listaDeReproducao = listaDeReproducao;
    }
    public void run() {
        try {
            sleep(listaDeReproducao.play(indiceMusicaEscolhida));
            new avancar(indiceMusicaEscolhida, listaDeReproducao);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
