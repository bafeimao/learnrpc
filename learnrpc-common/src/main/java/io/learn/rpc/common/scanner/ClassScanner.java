package io.learn.rpc.common.scanner;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.common.scanner
 * @className: ClassScanner
 * @author: ycd20
 * @description: class scanner
 * @date: 2022/10/29 11:11
 * @version: 1.0
 */
public class ClassScanner {
    /**
     * file
     */
    public static final String PROTOCOL_FILE = "file";

    /**
     * jar
     */
    public static final String PROTOCOL_JAR = "jar";

    /**
     * .class
     */
    public static final String CLASS_FILE_SUFFIX = ".class";

    /**
     * scan all class information under
     * the specified package in the current project
     *
     * @param packageName   name
     * @param packagePath   path
     * @param recursive     is recursive
     * @param classNameList name list
     */

    private static void findAndAddClassInPackageByFile(String packageName,
                                                       String packagePath, final boolean recursive,
                                                       List<String> classNameList) {
        //get dir from package path
        File dir = new File(packagePath);
        //if not exist then return
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        //exist then get file and directory
        //custom filter rules contains subdirectory and .class file
        File[] dirFiles = dir.listFiles(pathname -> (recursive && pathname.isDirectory()) ||
                (pathname.getName().endsWith(".class")));
        //loop all files
        for (File file : dirFiles) {
            //if directory then continue scanning
            if (file.isDirectory()) {
                findAndAddClassInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(), recursive, classNameList);
            } else {
                //if file is .class file then remove suffix .class,only get class name
                String className = file.getName().substring(0, file.getName().length() - 6);
                //add to the collection
                classNameList.add(packageName + "." + className);
            }
        }

    }

    /**
     * scan class from specified package
     *
     * @param packageName    package name
     * @param classNameList  nameList
     * @param recursive      recursive
     * @param packageDirName dirName
     * @param url            url
     * @return processed packageName
     * @throws java.io.IOException
     */
    private static String findAndAddClassInPackageByJar(String packageName,
                                                        List<String> classNameList,
                                                        boolean recursive,
                                                        String packageDirName,
                                                        URL url) throws IOException {
        //define jar file
        JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
        //get enum from jar file
        Enumeration<JarEntry> entries = jarFile.entries();
        //loop files
        while (entries.hasMoreElements()) {
            //get jar element it might be META-INF
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            //if start with '/'
            if (name.charAt(0) == '/') {
                //get string after '/'
                name = name.substring(1);
            }
            //if the first half is same as the define package name
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf('/');
                // if endWith '/' is a package
                if (idx != -1) {
                    //get package name and replace '/'to '.'
                    packageName = name.substring(0, idx).replace('/', '.');
                }
                //if can iterator and is package
                if ((idx != 1) || recursive) {
                    //if it is class and not directory
                    if (name.endsWith(CLASS_FILE_SUFFIX) && !entry.isDirectory()) {
                        // removing the following '.class' and get the real class name
                        String className = name.substring(packageName.length()
                                + 1, name.length() - 6);
                        classNameList.add(packageName + '.' + className);
                    }
                }
            }
        }
        return packageName;
    }

    /**
     * scan class information from specifies package name
     *
     * @param packageName specifies package name
     * @return ClassNameList
     * @throws Exception
     */
    public static List<String> getClassNameList(String packageName) throws Exception {
        //first class collectionList
        List<String> classNameList = new ArrayList<String>();
        //is iterator
        boolean recursive = true;
        //get packageName and replace
        String packageDirName = packageName.replace('.', '/');
        //define enum collection and loop to handle directory things
        Enumeration<URL> dirs =
                Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        //loop and iterator
        while (dirs.hasMoreElements()) {
            //get next element
            URL url = dirs.nextElement();
            //get protocol name
            String protocol = url.getProtocol();
            //if file then save to server
            if (PROTOCOL_FILE.equals(protocol)) {
                //get absolute path
                String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
                //scan entire package as file and add to collection
                findAndAddClassInPackageByFile(packageName, filePath, recursive, classNameList);
            } else if (PROTOCOL_JAR.equals(protocol)) {
                packageName = findAndAddClassInPackageByJar(packageName, classNameList, recursive, packageDirName, url);
            }
        }
        return classNameList;
    }

}
