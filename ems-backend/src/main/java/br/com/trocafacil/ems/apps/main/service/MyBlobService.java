package br.com.trocafacil.ems.apps.main.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyBlobService {

    private static String basePath = "https://trocafacilstorage.blob.core.windows.net/testcontainer/";
    @Autowired
    private final AzureBlobProperties azureBlobProperties;


    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    private BlobContainerClient containerClient() {

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .endpoint(azureBlobProperties.getConnectionstring())
                .credential(new StorageSharedKeyCredential(azureBlobProperties.getAccountname(), azureBlobProperties.getAccountkey()))
                .buildClient();

        BlobContainerClient container = serviceClient.getBlobContainerClient(azureBlobProperties.getContainer());
        return container;
    }

    public String storeAccountFile(String filename, InputStream content, long length, Long id) {
        log.info("Azure store file BEGIN {}", filename);
        BlobClient client = containerClient().getBlobClient(filename);
        if (client.exists()) {
            log.warn("The file was already located on azure");
        } else {
            client.upload(content, length);
        }
        log.info("Azure store file END");
        log.info("Saving image path in database");

        log.info(accountService.saveImage(id, basePath.concat(filename)));
        

        return "File uploaded with success!";
    }

    public String deleteFile(String filename) {
        log.info("Azure delete file BEGIN {}", filename);
        BlobClient client = containerClient().getBlobClient(filename);
        if (client.exists()) {
            client.delete();
        } else {
            log.info("The file is not found on azure");
        }
        return "Filed deleted with success!";
    }

    public String storeProductFile(String filename, InputStream content, long length, Long id) {
        log.info("Azure store file BEGIN {}", filename);
        BlobClient client = containerClient().getBlobClient(filename);
        if (client.exists()) {
            log.warn("The file was already located on azure");
        } else {
            client.upload(content, length);
        }
        log.info("Azure store file END");
        log.info("Saving image path in database");
        log.info(productService.saveImage(id, basePath.concat(filename)));
        

        return "File uploaded with success!";
    }

    public String getBasePath(){
        return basePath;
    }

}
