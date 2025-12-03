package PokePong;



import PokePong.Controller.GameState;
import PokePong.Controller.PongController;
import PokePong.Model.PongData;
import PokePong.Model.PongModel;
import PokePong.View.pongView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class ClientServerThread extends Thread {
    //multithreading icin

    private ServerSocket serversocket;
    private Socket socket;
    private PongModel model;
    private PongController controller;
    private GameState state;
    public boolean isServer;
    private ObjectOutputStream oos;

    public ClientServerThread(PongModel model, PongController controller) {
        this.model = model;
        this.controller = controller;
    }


    public static ClientServerThread newServer(int port, PongModel model, PongController controller) {
        var cst = new ClientServerThread(model, controller);
        try {
            cst.serversocket = new ServerSocket(port);
            cst.isServer = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        cst.start(); //ClientServerThread
        return cst;
    }


    public static ClientServerThread newClient(String ip, int port, PongModel model, PongController controller) {
        var cst = new ClientServerThread(model, controller);
        try {
            cst.socket = new Socket(ip, port);
            cst.oos = new ObjectOutputStream(cst.socket.getOutputStream());
            cst.isServer = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        cst.start();
        return cst;
    }

    public static ClientServerThread newPlayer(String ip, int port, PongModel model, PongController controller) throws IOException {
        var cst = new ClientServerThread(model, controller);
        try {
            cst.socket = new Socket(ip, port);
            cst.oos = new ObjectOutputStream(cst.socket.getOutputStream());
            cst.isServer = false;
        } catch (IOException e) {
            e.printStackTrace();
            ServerSocket serverSocket = new ServerSocket(port);
            cst.isServer = true;
        }
        cst.start();
        return cst;
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public void send(Object obj) {
        try {
            if (oos != null) {
                oos.reset();
                oos.writeObject(obj);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            if (socket == null) {
                socket = serversocket.accept();
                oos = new ObjectOutputStream(socket.getOutputStream());

            }

            // Read objects
            var ois = new ObjectInputStream(socket.getInputStream());
            while (true) {

                Object obj = ois.readObject();
                if (obj instanceof Character) {
                    switch ((char) obj) {
                        case 'u' -> this.model.getData().getPlayer2().getPosition().setY(max((float) (model.data.getPlayer2().getSize() / 2.0), model.data.getPlayer2().getPosition().getY() - 10));
                        case 'd' -> this.model.getData().getPlayer2().getPosition().setY(min((float) (controller.height - model.data.getPlayer2().getSize() / 2.0), model.data.getPlayer2().getPosition().getY() + 10));
                        case 'w' -> this.model.data.getPlayer1().getPosition().setY(max((float) (model.getData().getPlayer1().getSize() / 2.0), model.getData().getPlayer1().getPosition().getY() - 10));
                        case 's' -> this.model.data.getPlayer1().getPosition().setY(min((float) (controller.height - model.getData().getPlayer1().getSize() / 2.0), model.getData().getPlayer1().getPosition().getY() + 10));

                    }
                }
                if (obj instanceof PongData) {
                    this.model.data = ((PongData) obj);
                }
            }
        } catch (IOException e) {
            try {
                socket = serversocket.accept();
                oos = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
