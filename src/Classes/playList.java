package Classes;

import java.util.ArrayList;

public class playList {
    private final ArrayList<music>playlist;

    public playList(){ //construtor
        this.playlist= new ArrayList<>();
    }
    public void addMusic(music musica){ //adicionar música no arraylist
        playlist.add(musica);
        System.out.println(musica.getNomeMusica()+" Adicionada com sucesso!");
    }
    public void removeMusic (int i){ //remover música do arrayList
        if(playlist.isEmpty()){
            System.out.println("A Classes.playList está vazia");
        }else{
            System.out.println(playlist.get(i).getNomeMusica()+" removida com sucesso!");
            playlist.remove(i);
        }
    }

}

