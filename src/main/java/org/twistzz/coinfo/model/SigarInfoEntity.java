package org.twistzz.coinfo.model;

/**
 * @author 7w1st22
 * @package_name com.example.tede.model    创建新文件的包的名称
 * @date 2022/2/11	当前系统日期
 * @time 15:17	当前系统时间
 */

public class SigarInfoEntity {
    private String value;
    private String name;


    public SigarInfoEntity() { }

    public SigarInfoEntity(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
