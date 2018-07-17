package top.simba1949.common;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author simba@onlying.cn
 * @since 2018-07-11
 */
@TableName("t_bankmgt_operation_events")
public class TBankmgtOperationEvents extends Model<TBankmgtOperationEvents> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "pk_daily", type = IdType.AUTO)
    private Integer pkDaily;
    /**
     * 银行类型
     */
    @TableField("Fbank_type")
    private String fbankType;
    /**
     * 银行名称
     */
    @TableField("Fbank_name")
    private String fbankName;
    /**
     * 问题类型
     */
    @TableField("Fproblem_type")
    private Integer fproblemType;
    /**
     * 问题时间
     */
    @TableField("Fappear_time")
    private Date fappearTime;
    /**
     * 问题反馈时间
     */
    @TableField("Ffeedback_time")
    private Date ffeedbackTime;
    /**
     * 问题反馈人
     */
    @TableField("Ffeedback_person")
    private String ffeedbackPerson;
    /**
     * 问题处理人
     */
    @TableField("Fprocessing_person")
    private String fprocessingPerson;
    /**
     * 处理问题的时间
     */
    @TableField("Fprocessing_time")
    private Date fprocessingTime;
    /**
     * 问题状态
     */
    @TableField("Fproblem_state")
    private Integer fproblemState;
    /**
     * 说明
     */
    @TableField("Fremark_column")
    private String fremarkColumn;
    /**
     * 日志状态
     */
    @TableField("Fdaily_state")
    private Integer fdailyState;


    public Integer getPkDaily() {
        return pkDaily;
    }

    public void setPkDaily(Integer pkDaily) {
        this.pkDaily = pkDaily;
    }

    public String getFbankType() {
        return fbankType;
    }

    public void setFbankType(String fbankType) {
        this.fbankType = fbankType;
    }

    public String getFbankName() {
        return fbankName;
    }

    public void setFbankName(String fbankName) {
        this.fbankName = fbankName;
    }

    public Integer getFproblemType() {
        return fproblemType;
    }

    public void setFproblemType(Integer fproblemType) {
        this.fproblemType = fproblemType;
    }

    public Date getFappearTime() {
        return fappearTime;
    }

    public void setFappearTime(Date fappearTime) {
        this.fappearTime = fappearTime;
    }

    public Date getFfeedbackTime() {
        return ffeedbackTime;
    }

    public void setFfeedbackTime(Date ffeedbackTime) {
        this.ffeedbackTime = ffeedbackTime;
    }

    public String getFfeedbackPerson() {
        return ffeedbackPerson;
    }

    public void setFfeedbackPerson(String ffeedbackPerson) {
        this.ffeedbackPerson = ffeedbackPerson;
    }

    public String getFprocessingPerson() {
        return fprocessingPerson;
    }

    public void setFprocessingPerson(String fprocessingPerson) {
        this.fprocessingPerson = fprocessingPerson;
    }

    public Date getFprocessingTime() {
        return fprocessingTime;
    }

    public void setFprocessingTime(Date fprocessingTime) {
        this.fprocessingTime = fprocessingTime;
    }

    public Integer getFproblemState() {
        return fproblemState;
    }

    public void setFproblemState(Integer fproblemState) {
        this.fproblemState = fproblemState;
    }

    public String getFremarkColumn() {
        return fremarkColumn;
    }

    public void setFremarkColumn(String fremarkColumn) {
        this.fremarkColumn = fremarkColumn;
    }

    public Integer getFdailyState() {
        return fdailyState;
    }

    public void setFdailyState(Integer fdailyState) {
        this.fdailyState = fdailyState;
    }

    @Override
    protected Serializable pkVal() {
        return this.pkDaily;
    }

    @Override
    public String toString() {
        return "TBankmgtOperationEvents{" +
        ", pkDaily=" + pkDaily +
        ", fbankType=" + fbankType +
        ", fbankName=" + fbankName +
        ", fproblemType=" + fproblemType +
        ", fappearTime=" + fappearTime +
        ", ffeedbackTime=" + ffeedbackTime +
        ", ffeedbackPerson=" + ffeedbackPerson +
        ", fprocessingPerson=" + fprocessingPerson +
        ", fprocessingTime=" + fprocessingTime +
        ", fproblemState=" + fproblemState +
        ", fremarkColumn=" + fremarkColumn +
        ", fdailyState=" + fdailyState +
        "}";
    }
}
