package com.mist.domain.entity;

/**
 * description
 *
 * @author heng.huang@hand-china.com 2020/10/18
 */
public class Permission {

    // 服务器ip
    private String ip;
    // 服务器端口
    private int port;
    // 服务器用户名
    private String username;
    // 服务器密码
    private String password;

    //
    // getter/setter
    // ------------------------------------------------------------------------------


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Permission{" + "ip='" + ip + '\'' + ", port=" + port + ", username='" + username + '\'' + ", password='"
                        + password + '\'' + '}';
    }
}
