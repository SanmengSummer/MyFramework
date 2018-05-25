
package mvp.cn.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * 统一添加Licenses信息
 * 为避免误删重要信息不能恢复，这里只有在java代码中之前没有Licenses信息的情况下添加。
 * 如果有，请自己手动删除后在运行本代码。
 */
public class JavaLicense {

    static String licenseStr = "";

    public static void main(String[] args) {
        try {
            File license = new File("mvp2/license.txt");//添加文字的txt文件位置

            InputStreamReader read = new InputStreamReader(new FileInputStream(license), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = "";
            while ((lineTxt = bufferedReader.readLine()) != null) {
                licenseStr += lineTxt + "\n";
            }
            read.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        File f = new File("mvp2/src/main/java/summer");//需要添加文档注释的文件位置，可选择文件夹
        System.out.println(f.getAbsolutePath());
        print(f);
    }

    public static void print(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        File file = fileArray[i];

                        if (file.isDirectory()) {
                            print(file);
                        } else {
                            addLicense(0, licenseStr, file);
                        }
                    }
                }
            } else {
                addLicense(0, licenseStr, f);
            }
        }
    }

    public static void addLicense(long skip, String str, File file) {
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = bufferedReader.readLine();
            read.close();
            if (lineTxt.startsWith("/**")) {
                return;
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            if (skip < 0 || skip > raf.length()) {
                System.out.println("skip error");
                return;
            }
            byte[] b = str.getBytes();
            raf.setLength(raf.length() + b.length);
            for (long i = raf.length() - 1; i > b.length + skip - 1; i--) {
                raf.seek(i - b.length);
                byte temp = raf.readByte();
                raf.seek(i);
                raf.writeByte(temp);
            }
            raf.seek(skip);
            raf.write(b);
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}