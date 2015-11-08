package com.acme.edu.printers;


import java.io.*;
import java.nio.charset.Charset;

/**
 * Class writes messages to file
 */
public class FilePrinter extends Printer{

    private String fileName;
    private String charset;

    public FilePrinter(String fileName, String charset) {
        this.fileName = fileName;
        this.charset = charset;
    }


    @Override
    public void print(String message) throws IOException {

        File f = new File(fileName);
        if(f.exists() && !f.isDirectory()) {
            // do something
        }
        try (
            BufferedWriter bufferedWriter = new BufferedWriter(
                                                new OutputStreamWriter(
                                                        new FileOutputStream(f, true), Charset.forName(charset)))
        ){
            bufferedWriter.write(message);
        } catch (IOException e) {
            throw new IOException("problem while writing to file", e);
        }
    }
}
