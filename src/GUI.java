
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.listaMusica;
import Classes.music;
import Classes.playList;
import Metodos.*;

public class GUI implements ActionListener {

   int indexOfselectedMusicToAdd=-1;
   int indexOfselectedMusicToRemove=-1;
   music getSelectedMusicToAdd=null;
   music getSelectedMusicToRemove=null;
   playList playerDeMusicas = new playList();//instância do player de músicas que recebe os métodos(add,remove,skip,etc)
   listaMusica listaDeMusicas = new listaMusica();
   DefaultListModel playlistModel = new DefaultListModel();
   JList actualPlaylist = new JList(playlistModel);
   boolean pauseFlag = false;

   Thread play_thread = new Thread();
   Thread avancar_thread = new Thread();
   Thread voltar_thread = new Thread();
   Thread playTimer = new Thread();

   JFrame guiFrame = new JFrame();

   //Panels
   JPanel eastPanel = new JPanel();
   JPanel westPanel = new JPanel();
   JPanel centerPanel = new JPanel();
   JPanel progressBarPanel = new JPanel();
   JPanel actionBtnsPanel = new JPanel();

   //Progress Bar
   JProgressBar progressBar = new JProgressBar();


   //Lista e Scroller da Lista de musicas
   JScrollPane listScroller;
   JScrollPane playlistScroller;

   //Botão Add music
   JButton add = new JButton("Add Music");

   //Botão Remove music
   JButton rmv = new JButton("Remove Music");
   JButton play_btn = new JButton("Play");
   JButton pause_btn = new JButton("Pause");
   JButton step_forward = new JButton("Avançar");
   JButton step_backward = new JButton("Voltar");


   public GUI() {
      //Configs da janela
      guiFrame.setTitle("Malvadeza Music");
      guiFrame.setSize(800, 300);
      guiFrame.setLocation(500, 300);
      guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      guiFrame.setLayout(new BorderLayout(10, 10));

      //Config westPanel
      westPanel.setLayout(new BoxLayout(this.westPanel, BoxLayout.PAGE_AXIS));
      westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      //Botão AddMusic
      westPanel.add(add);
      add.addActionListener(this);

      //Config Lista de Musicas
      JList musicList = new JList(listaDeMusicas.lista);
      musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JList finalMusicList = musicList;
      finalMusicList.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            getSelectedMusicToAdd= (music) finalMusicList.getSelectedValue();
            indexOfselectedMusicToAdd= finalMusicList.getSelectedIndex();
            // selectedMusicToAdd= (music) finalMusicList.getSelectedValue();

         }
      });
      listScroller = new JScrollPane(finalMusicList);
      listScroller.setPreferredSize(new Dimension(200, 80));
      westPanel.add(this.listScroller, finalMusicList);


      //Config eastPanel
      eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
      eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      //Botão RemoveMusic
      eastPanel.add(rmv);
      rmv.addActionListener(this);

      //Config Playlist atual
      JList finalPlaylist = actualPlaylist;

      finalPlaylist.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            getSelectedMusicToRemove= (music) actualPlaylist.getSelectedValue();
            indexOfselectedMusicToRemove= actualPlaylist.getSelectedIndex();
            //System.out.println(finalActualPlaylist.getSelectedValue());
         }
      });
      playlistScroller = new JScrollPane(finalPlaylist);
      playlistScroller.setPreferredSize(new Dimension(200, 80));
      eastPanel.add(playlistScroller, finalPlaylist);

      //Config centerPanel
      centerPanel.setLayout(new BorderLayout());

      //Config progressBar
      progressBar.setStringPainted(true);
      progressBar.setMaximum(30000);
      progressBar.setValue(0);
      //progressBar.setSize(200, 25);
      progressBarPanel.add(progressBar);


      //Botão Voltar
      actionBtnsPanel.add(step_backward);
      step_backward.addActionListener(this);

      //Botão Play
      actionBtnsPanel.add(play_btn);
      play_btn.addActionListener(this);

      //Botão Pause
      actionBtnsPanel.add(pause_btn);
      pause_btn.addActionListener(this);

      //Botão Avançar

      actionBtnsPanel.add(this.step_forward);
      step_forward.addActionListener(this);


      //Configs finais

      centerPanel.add(this.progressBarPanel, BorderLayout.NORTH);
      centerPanel.add(this.actionBtnsPanel, BorderLayout.CENTER);
      guiFrame.add(centerPanel, BorderLayout.CENTER);
      guiFrame.add(eastPanel, BorderLayout.EAST);
      guiFrame.add(westPanel, BorderLayout.WEST);
      guiFrame.setVisible(true);

   }
   public void addAtPlaylist(music musicAdded){
      actualPlaylist.setModel(playlistModel);
      playlistModel.addElement(musicAdded);
   }
   public void removeOfPlaylist(music musicRemoved){
      actualPlaylist.setModel(playlistModel);
      playlistModel.removeElement(musicRemoved);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      try {
         Thread add_thread = new Thread();
         Thread rmv_thread=new Thread();
         switch (e.getActionCommand()){
            case"Add Music":
               if (indexOfselectedMusicToAdd>=0){
                  addAtPlaylist(getSelectedMusicToAdd);
                  add_thread = new Thread(new adicionar(indexOfselectedMusicToAdd,playerDeMusicas,listaDeMusicas));
                  add_thread.start();
               }
               break;
            case"Remove Music":
               if (indexOfselectedMusicToRemove>=0){
                  rmv_thread=new Thread(new remover(indexOfselectedMusicToRemove,playerDeMusicas));
                  rmv_thread.start();
                  removeOfPlaylist(getSelectedMusicToRemove);
               }
               break;
            case"Play":
               if (pauseFlag){
                  play_thread.resume();
                  playTimer.resume();
                  pauseFlag = false;
               }else{
                  if (!playerDeMusicas.getPlaylist().isEmpty()&&!play_thread.isAlive()){
                     progressBar.setValue(0);
                     play_thread = new Thread(new Play(playerDeMusicas));
                     play_thread.start();
                     playTimer = new Thread(new Timer());
                     playTimer.start();
                  }
               }
               break;
            case"Pause":
               if (play_thread.isAlive()){
                  pauseFlag=true;
                  play_thread.suspend();
                  playTimer.suspend();
               }
               break;
            case"Voltar":
               if (playerDeMusicas.getMusicaAtual()>0){
                  play_thread.stop();
                  playTimer.stop();
                 voltar_thread=new Thread(new voltar(playerDeMusicas));
                  playTimer = new Thread(new Timer());
                  playTimer.start();
                  voltar_thread.start();
               }
               break;
            case"Avançar":
               if (playerDeMusicas.getMusicaAtual()+1<playerDeMusicas.getPlaylist().size()){
                  play_thread.stop();
                  playTimer.stop();
                  avancar_thread=new Thread(new avancar(playerDeMusicas));
                  playTimer = new Thread(new Timer());
                  playTimer.start();
                  avancar_thread.start();
               }
               break;
         }
         add_thread.join();
         rmv_thread.join();
         //play_thread.join();


      }catch (InterruptedException y){
         y.printStackTrace();
      }

   }

   public class Play extends Thread{

      public Play(playList listaDeReproducao) {
         playerDeMusicas = listaDeReproducao;
      }
      public void run() {
         try {
            sleep(playerDeMusicas.play(playerDeMusicas.getMusicaAtual()));
            if (playerDeMusicas.getPlaylist().size()>playerDeMusicas.getMusicaAtual()+1){
               avancar_thread = new Thread(new avancar(playerDeMusicas));
               avancar_thread.start();
            }
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

   }

   public class avancar implements Runnable {

      public avancar(playList listaDeReproducao){
         playerDeMusicas = listaDeReproducao;
      }

      @Override
      public void run() {
         playerDeMusicas.setMusicaAtual(playerDeMusicas.getMusicaAtual()+1);
         if (play_thread.isAlive()){
            play_thread.stop();
         }
         play_thread=new Thread(new Play(playerDeMusicas));
         playTimer=new Thread(new Timer());
         playTimer.start();
         play_thread.start();
      }
   }

   public class voltar implements Runnable{

      public voltar (playList listaDeReproducao){
         playerDeMusicas= listaDeReproducao;
      }
      @Override
      public void run() {
         playerDeMusicas.setMusicaAtual(playerDeMusicas.getMusicaAtual()-1);
         play_thread=new Thread(new Play(playerDeMusicas));
      }
   }



   public class Timer extends Thread{  //Esse método serve apenas para simular o andamento da barra de progresso
      public void run(){               //já que a classe que roda o sleep com o timer da musica não consegue
         progressBar.setValue(0);                              //interferir no progressBar.value
         while(progressBar.getValue()<30000&&!avancar_thread.isAlive()){
            try {
               sleep(1000);
               progressBar.setValue(progressBar.getValue()+1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         progressBar.setValue(0);
      }
   }
}







