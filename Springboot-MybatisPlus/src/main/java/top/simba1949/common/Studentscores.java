package top.simba1949.common;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author simba@onlying.cn
 * @since 2018-07-11
 */
public class Studentscores extends Model<Studentscores> {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String subject;
    private Float score;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Studentscores{" +
        ", userName=" + userName +
        ", subject=" + subject +
        ", score=" + score +
        "}";
    }
}
