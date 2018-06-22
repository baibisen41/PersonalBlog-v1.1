import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * User: baibisen
 * Date: 2018/5/6
 */
public class PbBlogEditTest extends BaseTest {

    private final static Logger LOG = LoggerFactory.getLogger(PbBlogEditTest.class);
    private final static String dbUrl = "jdbc:mysql://localhost:3306/db_personalblog?useUnicossssde=true&characterEncoding=UTF-8";
    private final static String dbName = "root";
    private final static String dbPwd = "root";

    private Connection dbConn() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbName, dbPwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOG.info("驱动加载失败");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("jdbc连接失败");
        }
        return connection;
    }

    private String getUserSql() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String name = "baibisen";
        String password = UUID.randomUUID().toString().replace("-", "");
        String status = "1";
        String ip = "127.0.0.1";
        String sql = "INSERT INTO pb_user (id,name,password,status,ip,create_date) VALUES (\"" + id + "\",\"" + name + "\",\"" + password + "\",\"" + status + "\",\"" + ip + "\",CURRENT_TIMESTAMP)";
        LOG.info(sql);
        return sql;
    }

    private String getBlogSql() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String userId = "e7d29c4eb93042f394194cb17498a785";
        String title = "Markdown在技术圈里（估计更多是程序猿吧）越来越流行";
        String tag = "技术笔记";
        String summary = "Markdown在技术圈里（估计更多是程序猿吧）越来越流行";
        String content = "### 一、Markdown和editor.md简介：\n" +
                "Markdown在技术圈里（估计更多是程序猿吧）越来越流行。简单的语法，统一的格式，使用过程中，手基本上不用从键盘上移到鼠标上去，超级方便。写好了一篇md文档(也就是含Markdown语法格式的普通TXT文件)，可以随意放到别的支持Markdown格式的网站上发布。\n" +
                "\n" +
                "editor.md是国内开源的一款在线Markdown编辑器，单纯基于前端JavaScript实现，和后端什么语言无关。这个还可以画流程图，以及数学公式。官网Demo示例使用的是PHP语言，我后端使用的是Java，Springmvc。\n" +
                "\n" +
                "如下记录我在个人开源项目17Smart中使用的方法和过程。17Smart源码目前托管在Github上，感兴趣的可以参考了解。";
        String type = "3";
        String status = "1";
        String sql = "INSERT INTO pb_blog (id,user_id,title,tag,thumb_pic,summary,content,type,status,create_date) VALUES (\"" + id + "\",\"" + userId + "\",\"" + title + "\",\"" + tag + "\",\"\",\"" + summary + "\",\"" + content + "\",\"" + type + "\",\"" + status + "\",CURRENT_TIMESTAMP)";
        LOG.info(sql);
        return sql;
    }

    private String getPvSql() {
        String id = "42f37b9c59c44c2c97ef89cdc3436e9f";
        Integer blogPv = 1;
        String sql = "INSERT INTO pb_blog_pv (id,blog_pv,create_date) VALUES (\"" + id + "\",\"" + blogPv + "\",CURRENT_TIMESTAMP)";
        LOG.info(sql);
        return sql;
    }

    private void insert() {
        Statement stmt = null;
        try {
            stmt = dbConn().createStatement();
            // String sql = getBlogSql();
            String sql = getBlogSql();
            stmt.executeUpdate(sql);
            LOG.info("插入成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (dbConn().createStatement() != null) {
                    dbConn().createStatement().close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new PbBlogEditTest().insert();
    }
}
