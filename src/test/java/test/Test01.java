package test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.junit.Test;

/**
 * @author Administrator on 2021/12/22.
 * Description:
 */
public class Test01 {
    @Test
    public void test01(){
        Md5Hash md5Hash = new Md5Hash("123","lisi",1024);
        System.out.println("md5Hash= " + md5Hash);
        Sha512Hash sha512Hash = new Sha512Hash("123");
        System.out.println("sha= "+sha512Hash);
    }
}
