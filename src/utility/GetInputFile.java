package utility;

import java.io.File;

/**
 * Created by poorvank on 03/06/16.
 */
public class GetInputFile {

    public static File getFile(String name) {
        String absolutePath = new File("").getAbsolutePath();
        return new File(absolutePath + "/src/inputFiles/" + name);
    }

}
