package edu.hhuc.cvuuhk.homeserver.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "instruction")
@Data
public class Instruction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false)
  @NotNull(message = "指令名称不能为空")
  @NotBlank(message = "指令名称不能为空")
  @Pattern(regexp = "\\w{1,32}", message = "指令名格式不正确，只能含有英文字母、数字、下划线（_）三种字符，长度限制为 4~32 个字符")
  private String name;

  @Column(name = "comment")
  @Size(max = 128, message = "指令说明最多支持 128 个字符")
  private String comment;

  @Column(name = "type", nullable = false)
  @NotNull(message = "所属设备类型名不能为空")
  @NotBlank(message = "所属设备类型名不能为空")
  @Pattern(regexp = "\\w{4,32}", message = "所属设备类型名格式不正确，只能含有英文字母、数字、下划线（_）三种字符，长度限制为 4~32 个字符")
  private String type;

  @Column(name = "create_by", nullable = false)
  @NotNull(message = "指令所属用户名不能为空")
  @NotBlank(message = "指令所属用户名不能为空")
  private String createBy;

  @CreationTimestamp
  @Column(name = "create_time", nullable = false)
  private Date createTime;
}
