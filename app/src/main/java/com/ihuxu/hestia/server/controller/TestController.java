package com.ihuxu.hestia.server.controller;

import com.ihuxu.hestia.server.library.map.MapHandler;

public class TestController {
  public static void main(String[] args) {
    System.out.println(MapHandler.geocoder(116.27671421137467, 40.048975719139477).toString());
  }
}
