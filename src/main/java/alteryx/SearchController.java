package alteryx;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class SearchController {
    @Autowired
    private Services services;

    @GetMapping("/")
    public String homeForm(Model model) {
//        model.addAttribute("nameFolder", services.getNameFolder());
        return "home";
    }

    @PostMapping("/")
    public String homeSubmit(@ModelAttribute("nameFolder") String name, Model model) {
//        services.setNameFolder(name);
        List<String> nameFile = new ArrayList<>();
        try {
            nameFile = services.searchFiles(name);
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }

        List<String[]> column = new ArrayList<>();
        List<Integer> amount = new ArrayList<>();

        if (services.isFindFolder()){
            for (File file : services.getAnswerList()) {
                try (CSVReader reader = new CSVReader(new FileReader(file), ',', '"')) {
                    List<String[]> allRows = reader.readAll();
                    column.add(allRows.get(0));
                    amount.add(allRows.size()-1);
                    for(String[] row : allRows){
                        System.out.println(Arrays.toString(row));
                    }
                }catch (IOException ex){
                    System.out.println(ex);
                }
            }
            model.addAttribute("nameFolder", services.getNameFolderForSearch());
            model.addAttribute("lists", nameFile);
            model.addAttribute("column", column);
            model.addAttribute("amount", amount);
        }else{
            model.addAttribute("nameFolder", services.getNameFolderForSearch());
            model.addAttribute("lists", nameFile);
            model.addAttribute("column", null);
            model.addAttribute("amount", null);
        }

        return "result";
    }
}
