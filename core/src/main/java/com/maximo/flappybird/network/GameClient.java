package com.maximo.flappybird.network;

import java.net.*;

public class GameClient {

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    private NetworkListener listener;

    public GameClient(String host, int port, NetworkListener listener) {
        this.listener = listener;
        this.serverPort = port;

        try {
            socket = new DatagramSocket();  // Socket UDP
            serverAddress = InetAddress.getByName(host);

            new Thread(this::listen).start();    // Hilo de escucha

            send("HELLO");   // Saludo inicial al servidor

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Usa UDP (DatagramSocket) para comunicación rápida
    //Corre en un hilo separado para no bloquear el juego
    //El listener es quien recibe los mensajes del servidor



    private void listen() {
        try {
            byte[] buffer = new byte[1024];
            //El cliente espera paquetes del server, y los traduce al recibirlos y los
            // envia al NetworkListener para que el juego procese esos paquetes*/
            while (true) {

                DatagramPacket packet =
                    new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String message = new String(
                    packet.getData(),
                    0,
                    packet.getLength()
                );

                listener.onMessageReceived(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        try {

            byte[] data = message.getBytes();

            DatagramPacket packet =
                new DatagramPacket(
                    data,
                    data.length,
                    serverAddress,
                    serverPort
                );

            socket.send(packet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
