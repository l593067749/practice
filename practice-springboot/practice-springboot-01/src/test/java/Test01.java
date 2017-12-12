import java.net.URLEncoder;

/**
 * Created by liaowenqiang on 2017/3/14.
 */
public class Test01 {
    public static void main(String[] args) {
        String msg="张三李四abc124";
        msg=URLEncoder.encode(msg);
        System.out.println(msg);
        msg=URLEncoder.encode(msg);
        System.out.println(msg);
        msg=URLEncoder.encode(msg);
        System.out.println(msg);
    }

}
