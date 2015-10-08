package launch;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Partizanin on 20.08.2015.
 */
public class SiteDownloadTest {

    private SiteDownload sd;
    @Before
    public void setUp() throws Exception {
        sd = new SiteDownload();


    }

    @Test
    public void testPath() throws Exception {
        String path = "/D:/Illia/Java/Projects/cashTestConverter/out/artifacts/cashTestConverter_Web_exploded";
        System.out.println(sd.getPath());

    }

    @Test
    public void writeTest() {

        sd.getSource("yahoo");
        sd.getSource("nbu");


    }

}