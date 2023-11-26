package br.com.trocafacil.ems.apps.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trocafacil.ems.domain.model.photo.Photo;


public interface PhotoRepository extends JpaRepository<Photo, Long>{

    Optional<Photo> findByExternalIdAndAccountProduct(Long externalId, String group);
    Photo findByIdAndAccountProduct(Long id, String group);

}
