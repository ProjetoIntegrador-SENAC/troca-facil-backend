package br.com.trocafacil.ems.apps.main.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@RestController
@RequestMapping("blob")
public class BlobController {

    @Value("azure-blob://testcontainer/teste2.txt")
    private Resource blobFile;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/readBlobFile")
    public String readBlobFile() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity("https://trocafacilstorage.blob.core.windows.net/testcontainer/teste2.txt", String.class);
        return response.toString();
    }

}