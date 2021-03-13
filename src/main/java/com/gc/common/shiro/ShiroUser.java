package com.gc.common.shiro;

import java.io.Serializable;

/**
 * 
 * @author zhuang_wt
 *
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -4522416348751332299L;

    private Integer id ;
    private String loginName;
    private String name;

    public ShiroUser(Integer id, String loginName, String name) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
