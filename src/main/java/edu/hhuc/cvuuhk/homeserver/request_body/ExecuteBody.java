package edu.hhuc.cvuuhk.homeserver.request_body;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ExecuteBody {
  @NotNull(message = "设备名不能为空")
  @NotBlank(message = "设备名不能为空")
  @Pattern(regexp = "\\w{4,32}", message = "设备名格式不正确，只能含有英文字母、数字、下划线（_）三种字符，长度限制为 4~32 个字符，不可为空")
  private String deviceName;

  @NotNull(message = "指令id不能为空")
  private Integer instructionId;

  @Pattern(regexp = "\\w{0,32}", message = "指令参数有误")
  private String arg;
}
