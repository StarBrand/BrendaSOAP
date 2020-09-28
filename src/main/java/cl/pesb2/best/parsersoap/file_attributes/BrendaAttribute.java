package cl.pesb2.best.parsersoap.file_attributes;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * BrendaAttribute Interface which implements functionality
 * to receive and parser SOAP response
 *
 * @author Juan Saez Hidalgo
 */
public interface BrendaAttribute {

    /**
     * Parses result of SOAP query
     *
     * @param result Result of SOAP query
     */
    void parseFromResult(String result);

    /**
     * Gets method for SOAP query
     *
     * @return Method for SOAP query
     */
    String getMethod();

    /**
     * Append result to a file
     *
     * @param file File to append result
     */
    void writeOutput(BufferedWriter file) throws IOException;

}
