1、整合一下cas（单点登录）
这里需要注意的是：
1、开始https协议，需要向jdk中导入证书
    1.1、生成证书
            keytool -genkey -alias tomcat -keyalg RSA -keysize 1024 -validity 365 -keystore C:/cas/keys/tomcat.keystore -storepass password
            注意当提示你输入 你的姓氏名称时 要输入你的域名，其它可不填
    1.2、导出证书文件 crt
            keytool -export -alias tomcat -file C:/cas/keys/tomcat.crt -keystore C:/cas/keys/tomcat.keystore
    1.3、将证书导入到jre中
            keytool -import -v -trustcacerts -alias tomcat -file C:/cas/keys/tomcat.crt -storepass changeit -keystore "%JAVA_HOME%/jre/lib/security/cacerts"
            changeit是jdk导入证书的默认密码跟你之前设置的不是一回事
    1.4、tomcat的server.xml配置修改
        <!--开启https -->
        <!--
            keystoreFile:证书地址
            keystorePass：读取密码
            keyAlias：别名
        -->
        <Connector
                   protocol="org.apache.coyote.http11.Http11NioProtocol"
                   port="8443" maxThreads="200"
                   scheme="https" secure="true" SSLEnabled="true"
                   keystoreFile="C:/cas/keys/tomcat.keystore" keystorePass="password"
                   clientAuth="false" sslProtocol="TLS" keyAlias="tomcat" />
 2、下载对应的cas Service，用tomcat起起来
        我这里是4.0的war包，你也可以从git上现在，然后自己去打包，注意跳过test

可访问 http://www.wenqiang.com:8080/thmleaf/ 查看效果
备注：
    www.wenqiang.com这个需要本机的域名，你可以改成你自己的。只要与下面输入的域名保持一致
    war 包较大，我就不上传了，可以上网自己找一下，我给个地址
    https://github.com/apereo/cas/releases/tag/v4.0.0
    解压后，modules文件里就是
