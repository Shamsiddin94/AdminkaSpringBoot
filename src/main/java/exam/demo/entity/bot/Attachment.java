package exam.demo.entity.bot;

import exam.demo.entity.enums.FileType;
import exam.demo.entity.identity.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbsEntity {
    private  String fileUrl;
    private String fileName;
    @Enumerated(EnumType.STRING)
    private FileType type;
    private  String size;
    @ManyToOne(optional = false)
    private  Client client;
}
