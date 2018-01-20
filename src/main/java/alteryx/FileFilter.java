package alteryx;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter implements FilenameFilter {
    private String ext;

    public FileFilter(String ext) {
        this.ext = ext;
    }

    public boolean accept(File dir, String name) {
        int p = name.indexOf(ext);
        return ((p >= 0) && (p + ext.length() == name.length()));
    }
}
