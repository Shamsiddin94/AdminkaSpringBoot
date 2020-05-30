package exam.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ResultModel extends Result {
    private  Object object;

    public ResultModel(boolean success,String message, Object object){
        super(success,message);
        this.object=object;
    }

}
