package edu.hhuc.cvuuhk.homeserver.entity;

import edu.hhuc.cvuuhk.homeserver.repository.ExecuteHistoryRepository;
import edu.hhuc.cvuuhk.homeserver.service.MqttPublishService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "device")
public class Device {
  @Id
  @Column(name = "name", nullable = false)
  @NotNull(message = "设备名不能为空")
  @NotBlank(message = "设备名不能为空")
  @Pattern(regexp = "\\w{4,32}", message = "设备名格式不正确，只能含有英文字母、数字、下划线（_）三种字符，长度限制为 4~32 个字符")
  private String name;

  @Column(name = "type", nullable = false)
  @NotNull(message = "设备类型名不能为空")
  @NotBlank(message = "设备类型名不能为空")
  @Pattern(regexp = "\\w{4,32}", message = "设备类型名格式不正确，只能含有英文字母、数字、下划线（_）三种字符，长度限制为 4~32 个字符")
  private String type;

  @Column(name = "comment")
  @Size(max = 128, message = "设备说明最多支持 128 个字符")
  private String comment;

  @Column(name = "create_by", nullable = false)
  @NotNull(message = "设备所属用户名不能为空")
  @NotBlank(message = "设备所属用户名不能为空")
  private String createBy;

  @CreationTimestamp
  @Column(name = "create_time", nullable = false)
  private Date createTime;
}
