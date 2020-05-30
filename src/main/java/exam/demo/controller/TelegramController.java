package exam.demo.controller;

import exam.demo.entity.User;
import exam.demo.payload.ResultModel;
import exam.demo.security.CurrentUser;
import exam.demo.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/telegram"})
public class TelegramController {

    @Autowired
    private TelegramService telegramService;
    @GetMapping(value = {"/picture"})
    public String indexPicture(@CurrentUser User user, Model model){

        model.addAttribute("pictures",telegramService.allPictures(user));
        System.out.println();
        return "telegram/picture";
    }

    @GetMapping(value = {"/document"})
    public  String indexDocument(@CurrentUser User user, Model model){

        return "telegram/document";
    }
    @GetMapping(value = {"/picture/get/{id}"})
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable("id") Long id,@CurrentUser User user){
        ResultModel resultModel=telegramService.getPicture(id,user);
        System.out.println(resultModel.toString());
        if (resultModel.getSuccess()){

            Resource file= (Resource) resultModel.getObject();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + resultModel.getMessage()+ "\"").body(file);
        }
        Resource file= (Resource) resultModel.getObject();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resultModel.getMessage() + "\"").body(file);
    }

}
