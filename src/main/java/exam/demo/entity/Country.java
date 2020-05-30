package exam.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import exam.demo.entity.identity.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Country extends AbsEntity implements Serializable {


    private String name;
    @Column(nullable = true)
    private String description;

    public Country(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
