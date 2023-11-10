package br.com.trocafacil.ems.apps.main.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyBlobService {

    @Autowired
    private final AzureBlobProperties azureBlobProperties;

    private BlobContainerClient containerClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder().endpoint(azureBlobProperties.getConnectionstring()).buildClient();

        BlobContainerClient container = serviceClient.getBlobContainerClient(azureBlobProperties.getContainer());
        return container;
    }

    public String storeFile(String filename, InputStream content, long length) {
        log.info("Azure store file BEGIN {}", filename);
        BlobClient client = containerClient().getBlobClient(filename);
        if (client.exists()) {
            log.warn("The file was already located on azure");
        } else {
            client.upload(content, length);
        }

        log.info("Azure store file END");
        return "File uploaded with success!";
    }

}
