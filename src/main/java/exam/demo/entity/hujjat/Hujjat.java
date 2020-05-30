package exam.demo.entity.hujjat;

import exam.demo.entity.User;
import exam.demo.entity.identity.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hujjat extends AbsEntity {

    private String  docNumber;
    private String  regNumber;
    @Column(columnDefinition = "TEXT")
    private String  mazmuni;
    private Date outDate;

    @ManyToOne
    private Tashkilot tashkilot;

    @ManyToOne
    private Tasnif tasnif;

    @ManyToOne
    private User bajaruvchi;


    public Hujjat(String docNumber, String regNumber, String mazmuni, Date outDate, Tashkilot tashkilot, Tasnif tasnif) {
        this.docNumber = docNumber;
        this.regNumber = regNumber;
        this.mazmuni = mazmuni;
        this.outDate = outDate;
        this.tashkilot = tashkilot;
        this.tasnif = tasnif;
    }
}
