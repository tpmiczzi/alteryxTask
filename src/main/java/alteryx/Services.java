package alteryx;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Services {
    private String nameFolderForSearch = "";
    private boolean isFindFolder = false;

    private File[] answerList;
    private String CSV_EXTENSION = ".csv";

    public List<String> searchFiles(String nameFolder) throws IOException {
        answerList = null;
        List<String> list = new ArrayList<>();
        nameFolderForSearch = nameFolder;
//        String testName = "folderWithFiles3";

        File rootDirectory = new File("C:\\Users\\tpmki\\Downloads\\test_task");
        recursSearsh(nameFolderForSearch, rootDirectory);
        if (answerList != null){
            for (File file : answerList) {
                list.add(file.getName());
            }
            isFindFolder = true;
        }else{
            list.add("folder don't find, repeat please");
            isFindFolder = false;
        }
        return list;
    }


    public void recursSearsh(String nameSearchFolder, File rootDirectory) {
        if (rootDirectory.isDirectory()) {
            for (File item : rootDirectory.listFiles()) {
                if (item.isDirectory()) {
                    if (item.getName().equals(nameSearchFolder)) {
                        File dir = new File(item.getPath());
                        answerList = dir.listFiles(new FileFilter(CSV_EXTENSION));
                        break;
                    } else {
                        recursSearsh(nameSearchFolder, item);
                    }
                }
            }
        }
    }

    public File[] getAnswerList() {
        return answerList;
    }

    public void setAnswerList(File[] answerList) {
        this.answerList = answerList;
    }

    public String getCSV_EXTENSION() {
        return CSV_EXTENSION;
    }

    public void setCSV_EXTENSION(String CSV_EXTENSION) {
        this.CSV_EXTENSION = CSV_EXTENSION;
    }

    public String getNameFolderForSearch() {
        return nameFolderForSearch;
    }

    public void setNameFolderForSearch(String nameFolderForSearch) {
        this.nameFolderForSearch = nameFolderForSearch;
    }

    public boolean isFindFolder() {
        return isFindFolder;
    }

    public void setFindFolder(boolean findFolder) {
        isFindFolder = findFolder;
    }
}
