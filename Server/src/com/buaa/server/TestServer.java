package com.buaa.server;

import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(6666);
		Socket s = ss.accept();
		System.out.println("hhhh".getBytes());

	}

}
