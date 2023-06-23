package org.inditex.prices.api.controllers;

import org.inditex.prices.api.exceptions.BadRequestException;
import org.inditex.prices.api.exceptions.NotFoundException;
import org.inditex.prices.api.responses.PriceResponse;
import org.inditex.prices.api.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return null;
    }

}
