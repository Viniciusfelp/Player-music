package Metodos;

import Classes.listaMusica;
import Classes.playList;

public class adicionar extends lock implements Runnable {
    playList listaDeReproducao = new playList();
    listaMusica lista = new listaMusica();
    private final int indiceMusicaEscolhida;
    public adicionar(int indice){
        this.indiceMusicaEscolhida = indice;
    }
    public void run() {
        getAcessLock().lock();
        try {
            while (isOcupado()) {
                getCanWrite().await();
            }
            listaDeReproducao.addMusic(lista.getMusica(indiceMusicaEscolhida));
            setOcupado(true);
            getCanRead().signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getAcessLock().unlock();
        }
    }
}
