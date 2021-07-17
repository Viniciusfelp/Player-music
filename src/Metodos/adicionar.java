package Metodos;

import Classes.listaMusica;
import Classes.playList;

public class adicionar extends lock implements Runnable {
    private final playList listaDeReproducao ;
    private final listaMusica lista ;
    private final int indiceMusicaEscolhida;

    public adicionar(int indice,playList listaDeReproducao,listaMusica lista){ //Passei a playList e a lista de Música como parâmetro do construtor
        this.indiceMusicaEscolhida = indice;                                   //Dessa forma a playList que ta instanciada no Main não reseta
        this.listaDeReproducao=listaDeReproducao;                              //toda vez que esse método aqui for chamado
        this.lista=lista;
    }
    public void run() {
        getAcessLock().lock();//Bloqueia esse objeto
        try {
            while (isOcupado()) getCondition().await();//Espera até que a thread possa escrever na playlist
            setOcupado(true);//Indica que outra thread nao pode usar a funcao
            listaDeReproducao.addMusic(lista.getMusica(indiceMusicaEscolhida));
            setOcupado(false);
            getCondition().signalAll();//sinaliza para a thread que está esperando
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Desbloqueia esse Objeto
            getAcessLock().unlock();
        }
    }
}
