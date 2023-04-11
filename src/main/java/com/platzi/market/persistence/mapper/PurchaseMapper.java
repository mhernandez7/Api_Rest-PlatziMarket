package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Purchase;
import com.platzi.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    //Siempre en la clase destino debemos tener todos los mapeos. Si no los tenemos debemos ignorarlos 2--

    @Mappings({
            @Mapping(source = "idCompra ", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha",      target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario",target = "comment"),
            @Mapping(source = "estado",     target = "state"),
            @Mapping(source = "productos",  target = "items")


    })
    Purchase toPurchase (Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    //2 con @InheritInverseConfiguration
    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);

}
