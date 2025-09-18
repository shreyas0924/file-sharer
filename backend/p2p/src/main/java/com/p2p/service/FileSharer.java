package com.p2p.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.p2p.Utils.UploadUtils;

public class FileSharer {
     // Key - Port, Value - File Path
     private HashMap<Integer, String> availableFiles;

     public FileSharer() {
          availableFiles = new HashMap<>();
     }

     public int offerFile(String filePath) {
          int port;
          while (true) {
               port = UploadUtils.generateCode();
               if (!availableFiles.containsKey(port)) {
                    availableFiles.put(port, filePath);
                    return port;
               }
          }
     }

     public void startFileServer(int port) {
          String filePath = availableFiles.get(port);
          if (filePath == null) {
               System.out.println("No file is associated to the port " + port);
          }

          try (ServerSocket serverSocket = new ServerSocket(port)) {
               System.out.println("Serving file " + new File(filePath).getName() + " on port " + port);
               Socket clientSocket = serverSocket.accept();
               System.out.println("Client connection " + clientSocket.getInetAddress());
               // each client's download will be handled by a new thread for concurrency
               new Thread(new FileSenderHandler(clientSocket, filePath)).start();
          } catch (IOException e) {
               System.err.print("Error handling file server on port " + port);
          }
     }

     private static class FileSenderHandler implements Runnable {

          private final Socket clientSocket;
          private final String filePath;

          public FileSenderHandler(Socket clientSocket, String filePath) {
               this.clientSocket = clientSocket;
               this.filePath = filePath;
          }

          @Override
          public void run() {
               try (FileInputStream fis = new FileInputStream(filePath);
                         OutputStream oss = clientSocket.getOutputStream()) {

                    String filename = new File(filePath).getName();
                    String header = "Filename: " + filename + "\n";
                    oss.write(header.getBytes());

                    // send the file content

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = fis.read(buffer)) != -1) {
                         oss.write(buffer, 0, bytesRead);
                    }
                    System.out.println("File '" + filename + "' sent to " + clientSocket.getInetAddress());

               } catch (IOException e) {
                    System.err.println("Error sending file to client: " + e.getMessage());
               } finally {
                    try {
                         clientSocket.close();
                    } catch (IOException e) {
                         System.err.println("Error closing client socket: " + e.getMessage());
                    }
               }
          }

     }
}
