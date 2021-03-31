package edu.hhuc.cvuuhk.homeserver.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "device_type")
@Data
public class DeviceType {
  @Id
  @Column(name = "name", nullable = false)
  @NotNull(message = "设备类型名不能为空")
  @NotBlank(message = "设备类型名不能为空")
  @Pattern(regexp = "\\w{4,32}", message = "设备类型名格式不正确，只能含有英文字母、数字、下划线（_）三种字符，长度限制为 4~32 个字符")
  private String name;

  @Column(name = "comment")
  @Size(max = 128, message = "注解最长支持 128 个字符")
  private String comment;

  @Column(name = "create_by", nullable = false)
  @NotNull(message = "设备类型所属用户名不能为空")
  @NotBlank(message = "设备类型所属用户名不能为空")
  private String createBy;

  @CreationTimestamp
  @Column(name = "create_time", nullable = false)
  private Date createTime;
}
