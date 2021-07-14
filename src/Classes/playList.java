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
            System.out.println("A playList está vazia");
        }else if(i<0||i>playlist.size()-1){ //checa se o index passado pra remoção é válido
            System.out.println("Música não encontrada!");
        }else{
            System.out.println(playlist.get(i).getNomeMusica()+" removida com sucesso!");
            playlist.remove(i);
        }
    }

    public void getPlaylistString() { //printa a playList atual
        for (int i =0;i<playlist.size();i++){
            System.out.println(i+"-"+playlist.get(i).getNomeMusica());
        }
    }


}

