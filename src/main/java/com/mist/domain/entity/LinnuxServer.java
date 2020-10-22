package com.mist.domain.entity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 服务器实体类
 *
 * @author heng.huang@hand-china.com 2020/10/17
 */
public class LinnuxServer {

    // 服务器ip
    private String hostname;
    // 服务器端口
    private int port;
    // 服务器用户名
    private String username;
    // 服务器密码
    private String password;

    private Connection connection;
    private Session session;
    private BufferedReader stdout;
    private BufferedReader stderr;
    private PrintWriter printWriter;


    public LinnuxServer() {
        // 使用默认值
        long t1 = System.currentTimeMillis();
        initSession();
        long t2 = System.currentTimeMillis();
        System.out.println("远程登陆linux，连接耗时：" + (t2 - t1) / 1000.0 + "s");
    }

    public LinnuxServer(String hostname, int port, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    // 初始化连接并建立虚拟终端
    public boolean initSession() {
        long t1 = System.currentTimeMillis();
        try {
            connection = new Connection(hostname, port);
            connection.connect();
            boolean isAuthenticated = connection.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
            session = connection.openSession();
            // 建立虚拟终端
            session.requestDumbPTY();
            // 打开一个Shell
            session.startShell();

            stdout = new BufferedReader(
                            new InputStreamReader(new StreamGobbler(session.getStdout()), StandardCharsets.UTF_8));
            stderr = new BufferedReader(
                            new InputStreamReader(new StreamGobbler(session.getStderr()), StandardCharsets.UTF_8));
            printWriter = new PrintWriter(session.getStdin(), true);// 准备输入命令
            long t2 = System.currentTimeMillis();
            System.out.println("远程登陆linux，连接耗时：" + (t2 - t1) / 1000.0 + "s");
            return true;
        } catch (IOException e) {
            long t2 = System.currentTimeMillis();
            System.out.println("------建立ssh linux连接错误--耗时：" + (t2 - t1) / 1000.0 + "s");
            return false;
        }
    }

    public void close() {
        try {
            stdout.close();
            stderr.close();
            printWriter.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("------关闭ssh连接错误------");
        }

    }

    public List<String> execute(String strcmd) {
        System.out.println("在execute()中接收到命令:" + strcmd);
        List<String> rstList = new ArrayList<String>();
        try {
            if (strcmd.equals("exit")) {
                // 输入待执行命令
                printWriter.println(strcmd);
                close();
                rstList.add("用户退出，关闭连接!!!");
                return rstList;
            }
            // 输入待执行命令
            printWriter.println(strcmd);
            System.out.println("命令执行完成:");
            while (true) {
                String line = stdout.readLine();
                if (line == null || line.trim().endsWith("#")){
                    break;
                }
                System.out.println(line);
                rstList.add(line);
            }

        } catch (IOException e) {
            System.out.println("------连接已经关闭或命令出错------");
        }
        return rstList;
    }



    //
    // getter/setter
    // ------------------------------------------------------------------------------

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public BufferedReader getStdout() {
        return stdout;
    }

    public void setStdout(BufferedReader stdout) {
        this.stdout = stdout;
    }

    public BufferedReader getStderr() {
        return stderr;
    }

    public void setStderr(BufferedReader stderr) {
        this.stderr = stderr;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
}
