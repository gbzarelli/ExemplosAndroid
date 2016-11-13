/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biff.zarelli;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author guilherme
 */
public class Zip {

    public static boolean descompactar(String origem, String destino) throws FileNotFoundException, IOException {
        if (!destino.endsWith("/")) {
            destino += "/";
        }
        InputStream is = new FileInputStream(origem);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
        ZipEntry ze;

        while ((ze = zis.getNextEntry()) != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count;

            String filename = ze.getName();

            File file = new File(destino + filename);
            System.out.println("filename: " + file);

            if (ze.isDirectory()) {
                file.mkdir();
            } else {
                file.createNewFile();
                FileOutputStream fout = new FileOutputStream(file);

                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout.write(bytes);
                    baos.reset();
                }

                fout.close();
                zis.closeEntry();
            }
        }

        zis.close();

        return true;
    }

    private static void gerar(ZipOutputStream out, File file, String nomePath) throws FileNotFoundException, IOException {
        File[] files;
        if (file.isDirectory()) {
            if (nomePath == null) {
                nomePath = new String();
            }
            nomePath += file.getName() + "/";
            ZipEntry root = new ZipEntry(nomePath);
            out.putNextEntry(root);
            files = file.listFiles();
        } else {
            files = new File[]{file};
        }

        byte[] data;
        for (File f : files) {
            if (f.isDirectory()) {
                gerar(out, f, nomePath);
            } else {
                ZipEntry entry = new ZipEntry(nomePath + f.getName());


                FileInputStream fi = new FileInputStream(f.getAbsoluteFile());
                data = new byte[fi.available()];
                fi.read(data);
                out.putNextEntry(entry);
                out.write(data);
            }
        }
    }

    public static void compactar(String origem, String destino) throws FileNotFoundException, IOException {
        FileOutputStream dest = new FileOutputStream(destino);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
        gerar(out, new File(origem), null);
        out.close();
    }
}
