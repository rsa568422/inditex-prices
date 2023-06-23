package org.inditex.prices.api.controllers;

import org.iditex.prices.model.entities.Price;
import org.inditex.prices.api.exceptions.BadRequestException;
import org.inditex.prices.api.exceptions.NotFoundException;
import org.inditex.prices.api.responses.PriceResponse;
import org.inditex.prices.api.services.PriceService;
import org.inditex.prices.api.utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService service;

    @Autowired
    public PriceController(PriceService service) {
        this.service = service;
    }

    @GetMapping(path = "/{brandId}&{productId}&{date}")
    @ResponseStatus(HttpStatus.OK)
    public PriceResponse findApplicablePrice(@PathVariable(name = "brandId") Long brandId,
                                                       @PathVariable(name = "productId") Long productId,
                                                       @PathVariable(name = "date") String date) throws BadRequestException, NotFoundException {
        Optional<Price> optionalPrice = service.findApplicablePrice(brandId, productId, Parser.toDate(date));
        return optionalPrice.map(PriceResponse::fromPrice).orElseThrow(() -> new NotFoundException("Price not found"));
    }

}
