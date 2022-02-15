package org.twistzz.coinfo.utils;

import org.twistzz.coinfo.TedeApplication;

import java.io.*;

/**
 * @author 7w1st22
 * @package_name org.twistzz.coinfo.utils    创建新文件的包的名称
 * @date 2022/2/15	当前系统日期
 * @time 14:33	当前系统时间
 */
public class type {
    private synchronized static void main(String libName) throws IOException {
        String systemType = System.getProperty("os.name");
        String libExtension = (systemType.toLowerCase().indexOf("win")!=-1) ? ".dll" : ".so";
        String libFullName = libName + libExtension;
        String nativeTempDir = System.getProperty("java.io.tmpdir");

        InputStream in = null;
        BufferedInputStream reader = null;
        FileOutputStream writer = null;

        File extractedLibFile = new File(nativeTempDir+File.separator+libFullName);
        if(!extractedLibFile.exists()){
            try {
                in = TedeApplication.class.getClassLoader().getResourceAsStream("libs/"+libFullName);
                if(in==null)
                    in =  TedeApplication.class.getResourceAsStream(libFullName);
                TedeApplication.class.getResource(libFullName);
                reader = new BufferedInputStream(in);
                writer = new FileOutputStream(extractedLibFile);

                byte[] buffer = new byte[1024];

                while (reader.read(buffer) > 0){
                    writer.write(buffer);
                    buffer = new byte[1024];
                }
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if(in!=null)
                    in.close();
                if(writer!=null)
                    writer.close();
            }
        }
        System.load(extractedLibFile.toString());
    }
}
