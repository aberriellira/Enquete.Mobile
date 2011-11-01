package br.com.enquetemobile.mobile;

public interface RSSListener {

   public void itemParsed(String result);
   public void exception (java.io.IOException ex);
}