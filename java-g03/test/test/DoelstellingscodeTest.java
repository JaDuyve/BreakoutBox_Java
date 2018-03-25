package test;


import domein.Doelstellingscode;
import org.junit.Assert;
import org.junit.Test;

public class DoelstellingscodeTest {

    @Test
    public void aanmakenDoelstellingscodeSuccesTest(){
        Doelstellingscode d = new Doelstellingscode("code");
        Assert.assertEquals("code", d.getCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenDoelstellingscodeFoutiveCodeLeegTest(){
        Doelstellingscode d = new Doelstellingscode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenDoelstellingscodeFoutiveCodeNullTest(){
        Doelstellingscode d = new Doelstellingscode(null);
    }
}
