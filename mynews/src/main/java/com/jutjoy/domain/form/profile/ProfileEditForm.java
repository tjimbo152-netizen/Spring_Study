package com.jutjoy.domain.form.profile;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProfileEditForm {
    // 更新対象を特定するために必要
    private Integer id;

    @NotEmpty(message = "*名前は必ず入力してください。")
    private String name;

    @NotEmpty(message = "*性別は必ず選択してください。")
    private String gender;

    private String hobby;
    private String introduction;
}