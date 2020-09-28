package cl.pesb2.best.parserfile;

import java.io.IOException;

/**
 * Parser interface of classes
 * that parse brenda_downloaded file
 *
 * @author Juan Saez Hidalgo
 */
public interface Parser {

    /**
     * Parse text given
     *
     * @param text Text to parse
     */
    public void parse(String text) throws Exception;

}
