框架采用Struts+JPA+Spring技术开发，凯帝商城是一个完整的大型商城网站项目。
==================在开发工具中恢复凯帝商城开发环境====================
1>在开发工具中新建一个web工程,项目名称为"kaidishop","Context root URL"一栏中只填写"/"(*高度注意*),"J2EE Specification Level"一栏选择"J2EE1.8",然后点击"finish".

2>采用utf8字符集编码创建数据库,名为:kaishop。DDL语句如下：
CREATE DATABASE 为:kaishop。CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

3>修改jdbc.properties中的数据库连接信息

4>成功启动后,执行http://localhost:8080/system/init.do初始化信息

5>访问http://localhost:8080/进入主页

6>访问http://localhost:8080/control/center/main.do进入后台办公系统,用户名admin,密码123456
