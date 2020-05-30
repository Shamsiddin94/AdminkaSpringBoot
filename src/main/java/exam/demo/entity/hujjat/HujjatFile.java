package exam.demo.entity.hujjat;

import exam.demo.entity.identity.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HujjatFile extends AbsEntity {
    private String fileName;
    private String fileType;
    private String fileUrl;
    private String fileSize;
    @ManyToOne(fetch = FetchType.LAZY ,optional = false)
    private Hujjat hujjat;
}
