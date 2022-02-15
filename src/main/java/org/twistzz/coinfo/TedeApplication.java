package org.twistzz.coinfo;

import org.twistzz.coinfo.model.SigarInfoEntity;
import org.twistzz.coinfo.utils.SigarUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TedeApplication {
    public static void main(String[] args) throws IOException {
        System.out.println("===========操作系统是:"+System.getProperties().getProperty("os.name"));
        System.out.println("===========文件的分隔符为file.separator:"+System.getProperties().getProperty("file.separator"));
        String windows = "windows";
        if(System.getProperties().getProperty("os.name").toLowerCase().contains(windows)){
            // dll
//            File file = new File("src\\main\\resources\\sigar-amd64-winnt.dll");
//            String topathname = "C:\\Windows\\System32";
//            File toFile = new File(topathname);
//                try {
//                    copy(file, toFile);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
        }
        else {
            //linux
            File file = new File("src\\main\\resources\\libsigar-amd64-winnt.so");
            String topathname = "usr/lib54";
            File toFile = new File(topathname);
            toFile.setExecutable(true);//设置可执行权限
            toFile.setReadable(true);//设置可读权限
            toFile.setWritable(true);//设置可写权限
            if(!file.exists()){
                try {
                    copy(file, toFile);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        // Sigar信息集合
        List<SigarInfoEntity> sigarInfos = new ArrayList<>();
        try {
            // 1.获取系统信息和jvm虚拟机信息
            sigarInfos.addAll(SigarUtils.getJvmInfos());
            // 2.获取cpu信息
            sigarInfos.addAll(SigarUtils.getCpuInfos());
            // 3.获取内存信息
            sigarInfos.addAll(SigarUtils.getMemoryInfos());
            // 4.获取操作系统信息
            sigarInfos.addAll(SigarUtils.getOsInfos());
            // 5.获取文件信息
            sigarInfos.addAll(SigarUtils.getFileInfos());
            // 6.获取网络信息
            sigarInfos.addAll(SigarUtils.getNetInfos());
            StringBuilder sigarStringBuffer = new StringBuilder();
            for (SigarInfoEntity sigarInfo : sigarInfos) {
                sigarStringBuffer.append(sigarInfo.getName()).append(":").append(sigarInfo.getValue()).append("\r\n");
            }
            System.out.println(sigarStringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void copy(File file, File toFile) throws Exception {
        byte[] b = new byte[1024];
        int a;
        FileInputStream fis;
        FileOutputStream fos;
        if (file.isDirectory()) {
            String filepath = file.getAbsolutePath();
            filepath=filepath.replaceAll("\\\\", "/");
            String toFilepath = toFile.getAbsolutePath();
            toFilepath=toFilepath.replaceAll("\\\\", "/");
            int lastIndexOf = filepath.lastIndexOf("/");
            toFilepath = toFilepath + filepath.substring(lastIndexOf ,filepath.length());
            File copy=new File(toFilepath);
            //复制文件夹
            if (!copy.exists()) {
                copy.mkdir();
            }
            //遍历文件夹
            for (File f : file.listFiles()) {
                copy(f, copy);
            }
        } else {
            if (toFile.isDirectory()) {
                String filepath = file.getAbsolutePath();
                filepath=filepath.replaceAll("\\\\", "/");
                String toFilepath = toFile.getAbsolutePath();
                toFilepath=toFilepath.replaceAll("\\\\", "/");
                int lastIndexOf = filepath.lastIndexOf("/");
                toFilepath = toFilepath + filepath.substring(lastIndexOf ,filepath.length());

                //写文件
                File newFile = new File(toFilepath);
                fis = new FileInputStream(file);
                fos = new FileOutputStream(newFile);
                while ((a = fis.read(b)) != -1) {
                    fos.write(b, 0, a);
                }
            } else {
                //写文件
                fis = new FileInputStream(file);
                fos = new FileOutputStream(toFile);
                while ((a = fis.read(b)) != -1) {
                    fos.write(b, 0, a);
                }
            }

        }
    }

}
