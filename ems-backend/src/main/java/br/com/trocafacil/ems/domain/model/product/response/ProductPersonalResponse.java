package br.com.trocafacil.ems.domain.model.product.response;

import br.com.trocafacil.ems.domain.helpers.enums.ProductCondition;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import java.math.BigDecimal;

public record ProductPersonalResponse(long id,
                                      String name,
                                      BigDecimal price,
                                      Integer amount,
                                      ProductCondition curCondition,
                                      ProductStatus status,
                                      String category,
                                      String subCategory,
                                      String photoPath
) {}