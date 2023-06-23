package org.inditex.prices.api.utils;

import org.inditex.prices.api.exceptions.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    private Parser() { }

    public static Date toDate(String stringDate) throws BadRequestException {
        try {
            return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").parse(stringDate);
        } catch (ParseException e) {
            throw new BadRequestException("Bad format in request date", e);
        }
    }

    public static String toString(Date date) {
            return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(date);
    }

}
