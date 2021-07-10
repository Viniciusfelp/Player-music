public class listaMusica {
     music[] lista = new music[12];

    public listaMusica() {
        lista[0] = new music("track" + 0, "artista" + 0, 30000);
        lista[1] = new music("track" + 1, "artista" + 1, 30000);
        lista[2] = new music("track" + 2, "artista" + 2, 30000);
        lista[3] = new music("track" + 3, "artista" + 3, 30000);
        lista[4] = new music("track" + 4, "artista" + 4, 30000);
        lista[5] = new music("track" + 5, "artista" + 5, 30000);
        lista[6] = new music("track" + 6, "artista" + 6, 30000);
        lista[7] = new music("track" + 7, "artista" + 7, 30000);
        lista[8] = new music("track" + 8, "artista" + 8, 30000);
        lista[9] = new music("track" + 9, "artista" + 9, 30000);
        lista[10] = new music("track" + 10, "artista" + 10, 30000);
        lista[11] = new music("track" + 11, "artista" + 11, 30000);
    }
    public music getMusica(int indice){
     return this.lista[indice];
    }
    public String getNomeMusica(int indice){
        return this.lista[indice].getNomeMusica();
    }
}