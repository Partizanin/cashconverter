package launch;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created with Intellij IDEA.
 * Project name: cashTestConverter.
 * Date: 08.10.2015.
 * Time: 20:39.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class SiteFilterYahooTest {

    ClassLoaderBanks cl;

    @Before
    public void setUp() throws Exception {
        cl = new ClassLoaderBanks();

    }

    @Test
    public void testGetBankName() throws Exception {
        ArrayList<String> BankName = new ArrayList<>(cl.getOptionsValute().size());


    }
}