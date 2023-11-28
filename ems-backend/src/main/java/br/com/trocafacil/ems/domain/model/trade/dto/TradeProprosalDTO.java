package br.com.trocafacil.ems.domain.model.trade.dto;

import lombok.ToString;

@ToString
public record TradeProprosalDTO (String usernameProprosal, String AccountPhotoPath, String ProductPhotoPath){}
