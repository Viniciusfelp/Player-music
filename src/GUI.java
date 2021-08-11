

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.listaMusica;
import Classes.music;
import Classes.playList;
import Metodos.adicionar;
import Metodos.remover;

public class GUI implements ActionListener {
   int indexOfselectedMusicToAdd=-1;
   int indexOfselectedMusicToRemove=-1;
   music getSelectedMusicToAdd=null;
   music getSelectedMusicToRemove=null;
   playList playerDeMusicas = new playList();//instância do player de músicas que recebe os métodos(add,remove,skip,etc)
   listaMusica listaDeMusicas = new listaMusica();
   DefaultListModel playlistModel = new DefaultListModel();
   JList actualPlaylist = new JList(playlistModel);


   private final JFrame guiFrame = new JFrame();

   //Panels
   private final JPanel eastPanel = new JPanel();
   private final JPanel westPanel = new JPanel();
   private final JPanel centerPanel = new JPanel();
   private final JPanel progressBarPanel = new JPanel();
   private final JPanel actionBtnsPanel = new JPanel();

   //Progress Bar
   private JProgressBar progressBar = new JProgressBar();


   //Lista e Scroller da Lista de musicas
   private final JScrollPane listScroller;
   private final JScrollPane playlistScroller;

   //Botão Add music
   private final JButton add = new JButton("Add Music");

   //Botão Remove music
   private final JButton rmv = new JButton("Remove Music");
   private final JButton play_btn = new JButton("Play");
   private final JButton pause_btn = new JButton("Pause");
   private final JButton step_forward = new JButton("Step Forward");
   private final JButton step_backward = new JButton("Step Backward");


   public GUI() {
      //Configs da janela
      this.guiFrame.setTitle("Malvadeza Music");
      this.guiFrame.setSize(800, 300);
      this.guiFrame.setLocation(500, 300);
      this.guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.guiFrame.setLayout(new BorderLayout(10, 10));

      //Config westPanel
      this.westPanel.setLayout(new BoxLayout(this.westPanel, BoxLayout.PAGE_AXIS));
      this.westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      //Botão AddMusic
      this.westPanel.add(this.add);
      this.add.addActionListener(this);

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
      this.eastPanel.setLayout(new BoxLayout(this.eastPanel, BoxLayout.PAGE_AXIS));
      this.eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      //Botão RemoveMusic
      this.eastPanel.add(this.rmv);
      this.rmv.addActionListener(this);

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
      eastPanel.add(this.playlistScroller, finalPlaylist);

      //Config centerPanel
      this.centerPanel.setLayout(new BorderLayout());

      //Config progressBar
      this.progressBar.setStringPainted(true);
      this.progressBar.setSize(200, 25);
      this.progressBarPanel.add(this.progressBar);


      //Botão Voltar
      this.actionBtnsPanel.add(this.step_backward);
      this.step_backward.addActionListener(this);

      //Botão Play
      this.actionBtnsPanel.add(this.play_btn);
      this.play_btn.addActionListener(this);

      //Botão Pause
      this.actionBtnsPanel.add(this.pause_btn);
      this.pause_btn.addActionListener(this);

      //Botão Avançar

      this.actionBtnsPanel.add(this.step_forward);
      this.step_forward.addActionListener(this);


      //Configs finais

      this.centerPanel.add(this.progressBarPanel, BorderLayout.NORTH);
      this.centerPanel.add(this.actionBtnsPanel, BorderLayout.CENTER);
      this.guiFrame.add(centerPanel, BorderLayout.CENTER);
      this.guiFrame.add(eastPanel, BorderLayout.EAST);
      this.guiFrame.add(westPanel, BorderLayout.WEST);
      this.guiFrame.setVisible(true);

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
                  System.out.println(indexOfselectedMusicToRemove);
                  System.out.println(playerDeMusicas.getPlaylist().size());
                  rmv_thread=new Thread(new remover(indexOfselectedMusicToRemove,playerDeMusicas));
                  rmv_thread.start();
                  removeOfPlaylist(getSelectedMusicToRemove);
               }
               break;
         }
         add_thread.join();
         rmv_thread.join();


      }catch (InterruptedException y){
         y.printStackTrace();
      }

   }
}


