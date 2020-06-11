/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Boule
 */
public abstract class Utils {

    private Utils() {
    }

    public static final byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    public static final Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

    public static final char[] convertStringToCharArray(String chaineAConvertir) {
        char[] charArray = chaineAConvertir.toCharArray();
        return charArray;
    }

    public static java.util.Date stringToDate(String dateStr) {
        System.err.println("Début simple Date");
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        System.err.println("Simple date format effectué");
        java.util.Date date = null;
        try {
            date = simpleDate.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        System.out.println("Date fonctionne");
        return date;
    }

    public static final String dateToString(Date date) {
        String datestr = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd-MM-yyyy");
        try {
            datestr = sdfr.format(date);
        } catch (Exception e) {
            System.out.println(e);
        }
        return datestr;
    }

    public static final java.sql.Date convertJavaDateToSqlDate(java.util.Date javaDate) {
        return new java.sql.Date(javaDate.getTime());
    }

    public static final java.util.Date convertSqlDateToJavaDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }
}
