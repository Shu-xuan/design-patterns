package com.hhy.design.decorator.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) {
        test03();
    }

    public static void test03() {
        File file = new File("booklet.pdf");
        long l = Instant.now().toEpochMilli();
        try (InputStream fileInputStream = new CounterFileInputStream(new BufferedFileInputStream(new FileInputStream(file)))) {
            while (true) {
                int read = fileInputStream.read();
                if (read == -1) {
                    break;
                }
            }
            System.out.println("用时: " + (Instant.now().toEpochMilli() - l) + "毫秒");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test02() {
        File file = new File("booklet.pdf");
        long l = Instant.now().toEpochMilli();
        try (InputStream fileInputStream = new BufferedFileInputStream(new FileInputStream(file))) {
            while (true) {
                int read = fileInputStream.read();
                if (read == -1) {
                    break;
                }
            }
            System.out.println("用时: " + (Instant.now().toEpochMilli() - l) + "毫秒");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test01() {
        File file = new File("booklet.pdf");
        long l = Instant.now().toEpochMilli();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            while (true) {
                int read = fileInputStream.read();
                if (read == -1) {
                    break;
                }
            }
            System.out.println("用时: " + (Instant.now().toEpochMilli() - l) + "毫秒");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
