package com.ihuxu.hestia.server.controller;

import com.ihuxu.hestia.server.library.server.ServerHandler;

public class BootstrapController {
    public static void main(String[] args) {
        (new ServerHandler()).start();
    }
}
