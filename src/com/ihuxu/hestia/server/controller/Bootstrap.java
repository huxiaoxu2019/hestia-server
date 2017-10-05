package com.ihuxu.hestia.server.controller;

import com.ihuxu.hestia.server.library.server.SocketServer;

public class Bootstrap {
	public static void main(String[] args) {
		(new SocketServer()).start();
	}
}
