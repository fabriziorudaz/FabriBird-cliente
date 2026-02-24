package com.maximo.flappybird.network;

public interface NetworkListener {
    void onMessageReceived(String message);
}

//Es un "puente" entre el cliente de red y el juego
//Cuando llega un mensaje, el cliente llama a onMessageReceived()
//El juego (GameScreen) implementa esta interfaz y procesa los datos
