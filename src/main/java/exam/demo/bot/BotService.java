package exam.demo.bot;

import com.google.gson.Gson;
import exam.demo.entity.bot.Attachment;
import exam.demo.entity.bot.Client;
import exam.demo.entity.enums.FileType;
import exam.demo.payload.Result;
import exam.demo.payload.ResultModel;
import exam.demo.repository.bot.AttachmentRepository;
import exam.demo.repository.bot.ClientRepository;
import jdk.nashorn.internal.parser.JSONParser;
import net.bytebuddy.asm.Advice;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static exam.demo.bot.TelegramBot.botToken;
import static exam.demo.bot.TelegramBot.upPath;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
public class BotService {

     @Autowired
     private ClientRepository clientRepository;
     @Autowired
     private AttachmentRepository attachmentRepository;
     private Client client;
     @Transactional()
     public ResultModel existUser(Long chatId){
          ResultModel result=new ResultModel();
       Optional<Client>  clientOptional=clientRepository.findByChatId(chatId);
       if (!clientOptional.isPresent()){
            result.setMessage("User topilmadi");
            result.setSuccess(false);
            return result;
       }
          result.setMessage("User topildi");
          result.setSuccess(true);
          client=clientOptional.get();
          result.setObject(client);
          return result;
     }

     public Result pictureUpload(List<PhotoSize> photoSizes) {
          Result result = new Result();
          PhotoSize photoSize=photoSizes.get(2);
          System.out.println(photoSize);
          String name= UUID.randomUUID().toString();
          Attachment attachment=new Attachment();

          try {
              result= uploadFile( photoSize.getFileId(),name);
              attachment.setFileUrl(result.getMessage());
              attachment.setFileName(result.getMessage());
              attachment.setClient(client);
              attachment.setType(FileType.PICTURE);
              attachment.setSize(String.valueOf(photoSize.getFileSize()));
              attachment.setCreatedBy(client.getUser().getId());
              attachment.setUpdatedBy(client.getUser().getId());
              attachmentRepository.save(attachment);

          } catch (IOException e)
          {
               e.printStackTrace();
          }
          return result;
     }


     public Result documentUpload(Document document){
          Result result=new Result();
          try {
               String name=UUID.randomUUID().toString();
               uploadFile( document.getFileId(),name);
          } catch (IOException e)
          {
               e.printStackTrace();
          }
          return result;
     }


   public Result uploadFile(String file_id,String file_name) throws IOException {

        URL url = new URL("https://api.telegram.org/bot" + botToken + "/getFile?file_id=" + file_id);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String res = in.readLine();
        JSONObject jresult = new JSONObject(res);
        System.out.println(jresult);
        JSONObject key= (JSONObject) jresult.get("result");
        String name=key.getString("file_path");
        //System.out.println(name);
        List<String> dt= Arrays.asList(name.split("/"));
        file_name=file_name+dt.get(1);
        JSONObject path = jresult.getJSONObject("result");
        String file_path = path.getString("file_path");
        URL downoload = new URL("https://api.telegram.org/file/bot" + botToken + "/" + file_path);
        FileOutputStream fos = new FileOutputStream(upPath+"/" + file_name);
        System.out.println("Yuklash boshlandi");
        ReadableByteChannel rbc = Channels.newChannel(downoload.openStream());
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        System.out.println("Yuklash yakunlandi");
        return new Result(true,file_name);
    }

}
