package br.com.trocafacil.ems.apps.main.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("azure.myblob")
@Configuration
public class AzureBlobProperties {
    private String connectionstring;
    private String container;
    private String accountname;
    private String accountkey;

}
