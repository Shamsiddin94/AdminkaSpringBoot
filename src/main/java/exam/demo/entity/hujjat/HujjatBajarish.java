package exam.demo.entity.hujjat;

import exam.demo.entity.User;
import exam.demo.entity.identity.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HujjatBajarish  extends AbsEntity {

    @ManyToOne
    private User kimdan;

    @ManyToOne
    private User kimga;

    @Column(columnDefinition = "TEXT")
    private String  comment;

    private Date muddat;

    private Boolean isView;

    @ManyToOne(fetch = FetchType.LAZY ,optional = false)
    private Hujjat hujjat;


    public HujjatBajarish(User kimdan, User kimga, String comment) {
        this.kimdan = kimdan;
        this.kimga = kimga;
        this.comment = comment;
    }
}
