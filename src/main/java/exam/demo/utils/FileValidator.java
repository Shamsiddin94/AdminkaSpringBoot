package exam.demo.utils;

import exam.demo.anotation.ValidFile;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile[]> {

    @Override
    public void initialize(ValidFile constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile[] multipartFile, ConstraintValidatorContext context) {

        boolean result = true;

       /* String contentType = multipartFile.getContentType();
        if (!isSupportedContentType(contentType)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Only PNG or JPG images are allowed.")
                    .addConstraintViolation();

            result = false;
        }*/

       if (multipartFile.length==1 &&  multipartFile[0].isEmpty()){
           context.disableDefaultConstraintViolation();
           context.buildConstraintViolationWithTemplate("File kiritlmagan").addConstraintViolation();
           result = false;
       }
        return result;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }
}
