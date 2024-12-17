package com.example.springboot.controller;

import com.example.springboot.dao.IndianNameDao;
import com.example.springboot.entity.IndianName;
import com.example.springboot.model.IndianNameModel;
import com.example.springboot.service.NamesService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class IndianNamesController {

    @Autowired
    private NamesService namesService;

    @GetMapping("/names")
    public Page<IndianName> getNames(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name")String sortBy,
            @RequestParam(defaultValue = "asc")String direction){
        return namesService.getPaginatedIndianNames(page, size, sortBy, direction);
    }

    @GetMapping("/getname/{id}") //http://localhost:8080/getname/89
    public IndianNameModel getEmployeeById(@PathVariable int id) {
        return namesService.getIndianNameById(id);
    }

//    @GetMapping("/testSFT")
//    public String testSFT(){
//        String server = "127.0.0.1"; // FTP server address
//        int port = 21;              // Default FTP port
//        String user = "testuser";   // FTP username
//        String pass = "Deepak   "; // FTP password
//
//        FTPClient ftpClient = new FTPClient();
//
//        try {
//            // Connect to the FTP server
//            ftpClient.connect(server, port);
//            ftpClient.login(user, pass);
//
//            // Check if login was successful
//            int replyCode = ftpClient.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(replyCode)) {
//                System.out.println("FTP login failed. Reply Code: " + replyCode);
//                return "failed";
//            }
//
//            System.out.println("Connected to the FTP server");
//
//            // Set FTP client to binary mode
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
//
//            // Create a file in memory to upload
//            String fileContent = "Hello, this is a test file for FTP upload.";
//            InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
//
//            // Upload the file
//            String remoteFileName = "testfile.txt"; // Name of the file on the server
//            boolean done = ftpClient.storeFile(remoteFileName, inputStream);
//            inputStream.close();
//
//            if (done) {
//                System.out.println("File uploaded successfully.");
//            } else {
//                System.out.println("Failed to upload the file.");
//            }
//
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return "all good";
//    }
}
