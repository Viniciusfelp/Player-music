package Metodos;

import Classes.playList;

public class remover extends lock implements Runnable {
    playList listaDeReproducao = new playList();
    private final int indiceMusicaEscolhida;

    public remover(int indice) {
        this.indiceMusicaEscolhida = indice;
    }
    public void run() {
        getAcessLock().lock();
        try {
            while (isOcupado()) getCanWrite().await();
            listaDeReproducao.removeMusic(indiceMusicaEscolhida);
            setOcupado(true);
            getCanRead().signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getAcessLock().unlock();
        }
    }
}