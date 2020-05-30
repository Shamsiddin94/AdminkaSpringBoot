package exam.demo.service;

import exam.demo.entity.User;
import exam.demo.entity.bot.Attachment;
import exam.demo.entity.bot.Client;
import exam.demo.entity.enums.FileType;
import exam.demo.exception.StorageFileNotFoundException;
import exam.demo.payload.ResultModel;
import exam.demo.repository.bot.AttachmentRepository;
import exam.demo.repository.bot.ClientRepository;
import exam.demo.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramService {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<Attachment> allPictures(User user){
        List<Client>  clients=clientRepository.findByUser(user);
        List<Attachment> attachments=new ArrayList<>();
        clients.forEach(client -> {
            attachments.addAll(attachmentRepository.findByClientAndType(client,FileType.PICTURE));
        });
        return attachments;
    }

    public ResultModel getPicture(Long id, User user){
        List<Attachment> attachments=allPictures(user);
        ResultModel resultModel=new ResultModel();
       Optional< Attachment>  attachmentOptional=attachmentRepository.findById(id);
       if (attachmentOptional.isPresent()){
       Attachment attachment=attachmentOptional.get();
           if (attachments.contains(attachment)){
               Path file= AppConstants.botFiles.resolve(attachment.getFileUrl());
               try {
                   Resource resource=new UrlResource(file.toUri());
                   if (resource.exists() || resource.isReadable()) {
                       resultModel.setSuccess(true);
                       resultModel.setMessage(attachment.getFileUrl());
                       resultModel.setObject(resource);
                       return resultModel;
                   }
                   else {
                       throw new StorageFileNotFoundException(
                               "Could not read file: " + attachment.getFileUrl());

                   }
               } catch (MalformedURLException e) {
                   e.printStackTrace();
               }



               resultModel.setSuccess(false);
               resultModel.setMessage(attachment.getFileUrl());
               return resultModel;
           }
       }
        resultModel.setSuccess(false);
        resultModel.setMessage("");
        return resultModel;
    }


}
