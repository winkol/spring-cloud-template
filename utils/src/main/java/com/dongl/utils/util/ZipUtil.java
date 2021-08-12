/*
 * **************************************************************
 * Copyright ⓒ XIWEI PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * FILENAME：ZipUtil.java
 * EXPLAIN：zip文件处理工具
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/8/12 11:10 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import net.lingala.zip4j.model.FileHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Description: zip文件处理工具
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2021/8/12 11:10
 * @Author: Dong.L
 **/
public class ZipUtil {
    private static final Logger log = LoggerFactory.getLogger(ZipUtil.class);

    public static String zipHandler(String filePath) {
        log.info("->>> zipHandler > filePath: {}", filePath);
        try {
            File file = new File(filePath);

            net.lingala.zip4j.ZipFile zipFile = new net.lingala.zip4j.ZipFile(file);

            String dest = "E:\\temp\\zip\\extract";
            // 解压目录
            File destDir = new File(dest);
            // 将文件抽出到解压目录（解压）
            zipFile.extractAll(dest);

            // 获取zip文件包内信息
            List<FileHeader> headers = zipFile.getFileHeaders();
            headers.forEach(item -> {
                log.info("判断是文件还是目录 > isDirectory={}", item.isDirectory());
                log.info("获取文件名 > fileName={}", item.getFileName());
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 解压文件
     * @param file		压缩文件
     * @param targetDir	解压文件输出的目录
     * @throws IOException
     */
    public static void unPacket(Path file, Path targetDir) throws IOException {
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }
        // 创建zip对象
        ZipFile zipFile = new ZipFile(file.toFile());
        try {
            // 读取zip流
            try(ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(file))){
                ZipEntry zipEntry = null;
                // 遍历每一个zip项
                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    // 获取zip项目名称
                    String entryName = zipEntry.getName();
                    // 构建绝对路径
                    Path entryFile = targetDir.resolve(entryName);

                    // 文件夹
                    if(zipEntry.isDirectory()) {
                        if (!Files.isDirectory(entryFile)) {
                            Files.createDirectories(entryFile);
                        }
                    } else {							// 文件
                        // 读取zip项数据流
                        try(InputStream zipEntryInputStream = zipFile.getInputStream(zipEntry)){
                            try(OutputStream fileOutputStream = Files.newOutputStream(entryFile, StandardOpenOption.CREATE_NEW)){
                                byte[] buffer = new byte[4096];
                                int length = 0;
                                while ((length = zipEntryInputStream.read(buffer)) != -1) {
                                    fileOutputStream.write(buffer, 0, length);
                                }
                                fileOutputStream.flush();
                            }
                        }
                    }
                }
            }
        } finally {
            zipFile.close();
        }
    }

    /**
     * 压缩指定的文件
     * @param files				目标文件
     * @param zipFile			生成的压缩文件
     * @throws IOException
     */
    public static void packet(Path[] files, Path zipFile) throws IOException {
        OutputStream outputStream = Files.newOutputStream(zipFile, StandardOpenOption.CREATE_NEW);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        try {
            for (Path file : files) {
                if (Files.isDirectory(file)) {
                    continue;
                }
                try (InputStream inputStream = Files.newInputStream(file)) {
                    // 创建一个压缩项，指定名称
                    ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
                    // 添加到压缩流
                    zipOutputStream.putNextEntry(zipEntry);
                    // 写入数据
                    int len = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((len = inputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, len);
                    }
                    zipOutputStream.flush();
                }
            }
            // 完成所有压缩项的添加
            zipOutputStream.closeEntry();
        } finally {
            zipOutputStream.close();
            outputStream.close();
        }
    }

    /**
     * 压缩指定的目录
     * @param folder
     * @param zipFile
     * @throws IOException
     */
    public static void packet(Path folder, Path zipFile) throws IOException {
        if (!Files.isDirectory(folder)) {
            throw new IllegalArgumentException(folder.toString() + " 不是合法的文件夹");
        }
        OutputStream outputStream = Files.newOutputStream(zipFile, StandardOpenOption.CREATE_NEW);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        LinkedList<String> path = new LinkedList<>();

        try {
            Files.walkFileTree(folder, new FileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                    if (!dir.equals(folder)) {
                        // 开始遍历目录
                        String folder = dir.getFileName().toString();
                        path.addLast(folder);
                        // 写入目录
                        ZipEntry zipEntry = new ZipEntry(path.stream().collect(Collectors.joining("/", "", "/")));
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            zipOutputStream.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // 开始遍历文件
                    try (InputStream inputStream = Files.newInputStream(file)) {

                        // 创建一个压缩项，指定名称
                        String fileName = path.size() > 0
                                ? path.stream().collect(Collectors.joining("/", "", "")) + "/" + file.getFileName().toString()
                                : file.getFileName().toString();

                        ZipEntry zipEntry = new ZipEntry(fileName);

                        // 添加到压缩流
                        zipOutputStream.putNextEntry(zipEntry);
                        // 写入数据
                        int len = 0;
                        byte[] buffer = new byte[1024 * 10];
                        while ((len = inputStream.read(buffer)) > 0) {
                            zipOutputStream.write(buffer, 0, len);
                        }

                        zipOutputStream.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    // 结束遍历目录
                    if (!path.isEmpty()) {
                        path.removeLast();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            zipOutputStream.closeEntry();
        } finally {
            zipOutputStream.close();
            outputStream.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String filePath = "E:\\temp\\zip\\file\\model-1.0.zip";
        String dest = "E:\\temp\\zip\\extract";
//        zipHandler(filePath);

        File file1 = new File(filePath);
        File file2 = new File(dest);
        unPacket(file1.toPath(), file2.toPath());
    }

}
